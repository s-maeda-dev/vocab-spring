# Phase 7 実行計画書: コード品質改善・単語一覧カテゴリ別表示・統計グラフ表示

本書は、Phase6レビューの引き継ぎ事項の対応、単語一覧のカテゴリ別グループ表示、および過去の正答率や統計グラフの表示機能を実装する作業手順書です。

---

## 対応方針

| カテゴリ | 内容 |
|---|---|
| コード品質改善 | Phase6レビューの引き継ぎ事項（優先度：低 ×5件）を解消する |
| 単語一覧改善 | 単語一覧画面をカテゴリ別にグループ化した表示に変更する |
| 統計グラフ機能 | 学習統計ダッシュボード画面を新設し、正答率の推移や成績をグラフで表示する |

---

## Phase 7 タスク一覧

---

### ステップ1: コード品質改善（Phase6引き継ぎ事項）

#### 1-1. `GeminiService.java` の改善

> **対象引き継ぎ事項**
> - ⚠️ 優先度:低 — `e.printStackTrace()` を `log.error()` に変更

- [ ] `callGeminiApi()` の `catch` ブロック内の `e.printStackTrace()` を `log.error("[GeminiService] API呼び出し中にエラーが発生しました", e)` に変更

#### 1-2. `QuizController.java` の改善

> **対象引き継ぎ事項**
> - ⚠️ 優先度:低 — 出題ロジックの `excludedIds` 組み立てを private メソッドに共通化
> - ⚠️ 優先度:低 — `java.util.List` のインライン記述を import 文に統一

- [ ] `import java.util.List;` と `import java.util.ArrayList;` を追加
- [ ] `showQuiz()` 内の苦手モード・全単語モードで重複している `excludedIds` の組み立て処理を `private List<Long> buildExcludedIds(QuizSessionDto session)` メソッドに切り出す
- [ ] `java.util.List<Long>` → `List<Long>`、`java.util.ArrayList<>` → `ArrayList<>` に変更
- [ ] `showSummary()` 内の `java.util.List<String>` も同様に import 統一

#### 1-3. `QuizSessionDto.java` の改善

> **対象引き継ぎ事項**
> - ⚠️ 優先度:低 — `quizMode` を String → enum にリファクタリング

- [ ] `QuizMode` enum（列挙型：許可される値だけを定義する型）を作成
  - ファイル: `dto/QuizMode.java`（または `QuizSessionDto` 内のネストenum）
  - 値: `ALL`, `WEAK`
- [ ] `QuizSessionDto` の `quizMode` フィールドの型を `String` → `QuizMode` に変更
- [ ] `QuizController` 内の `"all"` / `"weak"` の文字列比較を `QuizMode.ALL` / `QuizMode.WEAK` に変更
- [ ] `quiz_settings.html` と `quiz_confirm.html` のフォーム値（`value="all"` / `value="weak"`）は小文字のまま維持し、Spring の自動変換（`@RequestParam`）で enum に変換されるよう対応
  - 方法: `QuizMode` enum に `@JsonCreator` 相当の `valueOf` をオーバーライドするか、カスタムコンバーターを追加、もしくはControllerで文字列を受けてenum変換

**⚠️ 注意**: enum化はController・DTO・テンプレートの3箇所にまたがる変更のため、コンパイルエラーに注意すること。

#### 1-4. `QuoteService.java` の改善（任意）

> **対象引き継ぎ事項**
> - ⚠️ 優先度:低 — 名言リストの外部ファイル化

- [ ] **（任意）** 名言リストを `src/main/resources/quotes.json` に外部化し、`@PostConstruct` で読み込むように変更
  - この対応は現時点でスキップしても動作に影響なし（名言の追加が頻繁でない限り）
  - 今回は **スキップ推奨**（コード変更量の割にメリットが小さい）

---

### ステップ2: 単語一覧画面のカテゴリ別表示

> **目的**: 現在はすべての単語がフラットなテーブルで表示されているが、カテゴリごとにまとめて表示することで見やすさを向上させる。

#### 2-1. バックエンド: データ構造の変更

- [ ] `WordController.listWords()` メソッドを修正
  - 現状: `List<Word>` を `words` としてModelに渡している
  - 変更後: `Map<String, List<Word>>` に変換してカテゴリ名をキーとした構造で渡す
    - `Map` は「キーと値のペア」を保持するデータ構造。ここでは「カテゴリ名」→「そのカテゴリに属する単語リスト」のペア
  - カテゴリが `null`（未分類）の単語は「未分類」というキーにまとめる
  - カテゴリ名のアルファベット順（日本語の場合はあいうえお順）でソート
  - `LinkedHashMap` を使用して挿入順を保持する（`HashMap` は順序が保証されない）

```java
// イメージ
Map<String, List<Word>> wordsByCategory = words.stream()
    .collect(Collectors.groupingBy(
        w -> w.getCategory() != null ? w.getCategory().getName() : "未分類",
        LinkedHashMap::new,
        Collectors.toList()
    ));
model.addAttribute("wordsByCategory", wordsByCategory);
```

#### 2-2. フロントエンド: `word_list.html` の改修

- [ ] テーブルの構造を変更し、カテゴリごとにグループヘッダー行を表示する
  - 各カテゴリ名を太字で表示するヘッダー行 + その下に該当する単語の行を表示
  - 折りたたみ機能（`<details>` / `<summary>` タグ）を使ってカテゴリを開閉できるようにする
  - 各カテゴリのヘッダーに含まれる単語数をバッジで表示（例: `日常 (5)`）
- [ ] CSSで見やすいスタイルを適用
  - カテゴリヘッダー行の背景色を薄くする
  - 開閉のアニメーションを追加

---

### ステップ3: 統計グラフ表示機能

> **目的**: ユーザーの学習履歴をグラフで可視化し、成長を実感できるようにする。
> **グラフライブラリ**: CDN経由の **Chart.js**（軽量・無料・学習コストが低い）を使用。

#### 3-1. バックエンド: 統計データ取得用の Service / Repository

**新規ファイル: `StatsService.java`**

- [ ] `StatsService` を作成し、以下のメソッドを実装

| メソッド名 | 戻り値 | 説明 |
|---|---|---|
| `getDailyStats(User user, int days)` | `List<DailyStatsDto>` | 直近N日間の日別正答数・不正解数を返す |
| `getCategoryStats(User user)` | `List<CategoryStatsDto>` | カテゴリ別の正答率を返す |
| `getTotalStats(User user)` | `TotalStatsDto` | 累計の正答数・不正解数・正答率・解答数を返す |

**新規ファイル: `dto/DailyStatsDto.java`**

- [ ] フィールド: `date`（日付）, `correctCount`（正答数）, `incorrectCount`（不正解数）

**新規ファイル: `dto/CategoryStatsDto.java`**

- [ ] フィールド: `categoryName`（カテゴリ名）, `totalCount`（解答数）, `correctCount`（正答数）, `correctRate`（正答率 %）

**新規ファイル: `dto/TotalStatsDto.java`**

- [ ] フィールド: `totalAnswered`（累計解答数）, `totalCorrect`（累計正答数）, `correctRate`（正答率 %）, `totalWords`（登録単語数）, `weakWordCount`（苦手単語数）

**`QuizHistoryRepository.java` にクエリを追加**

- [ ] 日別の正答数・不正解数を返すクエリ
  ```sql
  -- イメージ（JPQL）
  SELECT CAST(qh.answeredAt AS DATE) AS answerDate,
         SUM(CASE WHEN qh.isCorrect = true THEN 1 ELSE 0 END),
         SUM(CASE WHEN qh.isCorrect = false THEN 1 ELSE 0 END)
  FROM QuizHistory qh
  WHERE qh.user.id = :userId AND qh.answeredAt >= :sinceDate
  GROUP BY CAST(qh.answeredAt AS DATE)
  ORDER BY answerDate
  ```

- [ ] カテゴリ別の正答数・解答数を返すクエリ
  ```sql
  -- イメージ（JPQL）
  SELECT qh.word.category.name,
         COUNT(qh.id),
         SUM(CASE WHEN qh.isCorrect = true THEN 1 ELSE 0 END)
  FROM QuizHistory qh
  WHERE qh.user.id = :userId
  GROUP BY qh.word.category.name
  ```

- [ ] 累計の正答数・総解答数を返すクエリ
  ```sql
  -- イメージ（JPQL）
  SELECT COUNT(qh.id),
         SUM(CASE WHEN qh.isCorrect = true THEN 1 ELSE 0 END)
  FROM QuizHistory qh
  WHERE qh.user.id = :userId
  ```

#### 3-2. バックエンド: StatsController の作成

**新規ファイル: `StatsController.java`**

- [ ] `GET /stats` → 統計画面を表示する `showStats()` メソッド
  - `StatsService` を使って各種統計データを取得
  - Modelに `totalStats`, `dailyStats`, `categoryStats` を追加
  - テンプレート `stats.html` を返す

#### 3-3. フロントエンド: 統計画面 (`stats.html`) の作成

**新規ファイル: `stats.html`**

- [ ] **数値カード（上部）**: 累計解答数・正答率・登録単語数・苦手単語数をカードで表示
  - 各数値は大きなフォントで目立たせる
  - アイコン（絵文字）を添えて視覚的にわかりやすくする

- [ ] **正答率の推移グラフ（折れ線グラフ）**
  - Chart.js の `Line Chart` で直近14日間の日別正答数・不正解数を表示
  - X軸: 日付、Y軸: 回答数
  - 正答は緑色、不正解は赤色の線で表示

- [ ] **カテゴリ別正答率グラフ（棒グラフまたはドーナツチャート）**
  - Chart.js の `Doughnut Chart` または `Bar Chart` でカテゴリ別の正答率を表示
  - 各カテゴリの正答率を色分けして表示

- [ ] **Chart.js の読み込み**
  - CDN経由で `<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>` を追加
  - CDN（Content Delivery Network）: ライブラリをインターネット経由で読み込む仕組み。自分でファイルをダウンロードしなくてよい

#### 3-4. ホーム画面にリンク追加

- [ ] `home.html` のナビゲーションに「📊 学習統計」リンクを追加
  - リンク先: `/stats`

---

### ステップ4: 動作確認

- [ ] **コード品質改善の確認**
  - `e.printStackTrace()` が `log.error()` に置き換わっていること
  - `excludedIds` の共通メソッドが正しく動作すること
  - enum化後もクイズの全モード（全単語・苦手）が正常に動作すること

- [ ] **単語一覧カテゴリ別表示の確認**
  - カテゴリごとにグループ化されて表示されること
  - 「未分類」の単語が正しくまとめて表示されること
  - カテゴリの折りたたみ/展開が動作すること
  - 単語数バッジが正しい数値であること
  - 単語の編集・削除リンクが従来通り動作すること

- [ ] **統計グラフの確認**
  - 数値カードが正しい値を表示すること
  - 正答率の推移グラフ（折れ線）が正しく描画されること
  - カテゴリ別正答率グラフが正しく描画されること
  - クイズ履歴が0件の場合に「まだデータがありません」等のメッセージが表示されること
  - ホーム画面から統計画面への遷移が動作すること

---

## ファイル変更一覧（まとめ）

| ファイル | 変更種別 | 内容 |
|---|---|---|
| `GeminiService.java` | 修正 | `e.printStackTrace()` → `log.error()` |
| `QuizController.java` | 修正 | `buildExcludedIds()` 共通化、import統一、enum対応 |
| `QuizSessionDto.java` | 修正 | `quizMode` を String → `QuizMode` enum に変更 |
| `QuizMode.java` | **新規作成** | 出題モードの列挙型定義 |
| `WordController.java` | 修正 | 単語リストをカテゴリ別 Map に変換して渡す |
| `word_list.html` | 修正 | カテゴリ別グループ表示・折りたたみUI |
| `StatsService.java` | **新規作成** | 統計データ取得ロジック |
| `StatsController.java` | **新規作成** | 統計画面のController |
| `DailyStatsDto.java` | **新規作成** | 日別統計のデータ転送オブジェクト |
| `CategoryStatsDto.java` | **新規作成** | カテゴリ別統計のデータ転送オブジェクト |
| `TotalStatsDto.java` | **新規作成** | 累計統計のデータ転送オブジェクト |
| `QuizHistoryRepository.java` | 修正 | 統計用クエリの追加 |
| `stats.html` | **新規作成** | 統計グラフ表示画面（Chart.js使用） |
| `home.html` | 修正 | 統計画面へのリンク追加 |

---

## ブランチ

- ブランチ名: `issues/phase7`
- ベースブランチ: `issues/phase6`（Phase6マージ後に `main` へ統合予定）

# Phase 6 実行計画書: コード品質改善 & 苦手問題出題機能の実装

本書は、Phase5レビューで挙げられた引き継ぎ事項の対応と、要件定義書に定義されている「苦手単語（誤答率の高いもの）のみを指定しての出題」機能を実装する作業手順書です。

---

## 対応方針

| カテゴリ | 内容 |
|---|---|
| コード品質改善 | Phase5レビューの引き継ぎ事項（優先度：中・低）を解消する |
| 新機能追加 | 苦手問題出題モード（クイズ設定画面から選択可能）を実装する |

---

## Phase 6 タスク一覧

---

### ステップ1: コード品質改善（Phase5引き継ぎ事項）

#### 1-1. `GeminiService.java` の改善

> **対象引き継ぎ事項**
> - ⚠️ 優先度:中 — `System.out.println` を `Logger` に置き換え
> - ⚠️ 優先度:中 — コメント「Gemini 1.5 Flash 形式」を修正

- [ ] `private static final Logger log = LoggerFactory.getLogger(GeminiService.class);` を追加
- [ ] `System.out.println(...)` を `log.info(...)` または `log.warn(...)` に置き換え
  - `"[GeminiService] GEMINI_API_KEY が未設定..."` → `log.warn`
  - `"[GeminiService] APIキー設定済み..."` → `log.info`
- [ ] コメント `// リクエストボディの組み立て (Gemini 1.5 Flash 形式)` を `(Gemini 2.0 Flash 形式)` に修正

#### 1-2. `QuizService.java` の改善

> **対象引き継ぎ事項**
> - ⚠️ 優先度:低 — `@Transactional` の付与

- [ ] `saveQuizHistory()` メソッドに `@Transactional` アノテーション（トランザクション：処理の一塊を確実にDBに保存するための仕組み）を付与
- [ ] `import org.springframework.transaction.annotation.Transactional;` を追加

#### 1-3. `QuizController.java` の改善

> **対象引き継ぎ事項**
> - ⚠️ 優先度:低 — 名言を `QuoteService` に切り出す
> - ⚠️ 優先度:低 — `new java.util.Random()` を毎回 `new` しない

- [ ] 新規ファイル `QuoteService.java` を `service` パッケージに作成
  - 名言のリスト（配列）をフィールドとして保持
  - `getRandomQuote()` メソッドで `ThreadLocalRandom.current()` を使ってランダムな名言を返す
- [ ] `QuizController` の `showSummary()` メソッド内の名言ロジックを削除し、`QuoteService.getRandomQuote()` を呼び出すように変更
- [ ] `QuizController` のコンストラクタに `QuoteService` を DI（依存性注入：必要なオブジェクトを外から自動で渡してもらう仕組み）する

#### 1-4. `QuizHistoryRepository.java` の改善

> **対象引き継ぎ事項**
> - ⚠️ 優先度:低 — `LIMIT` を `Pageable` に変更（将来の苦手問題出題のために `word.id` を返すメソッドも追加）

- [ ] `findRecentMistakesByUserId()` の `LIMIT 10` を `Pageable` 形式に変更
  - シグネチャ変更: `List<String> findRecentMistakesByUserId(Long userId, Pageable pageable);`
  - 呼び出し側 (`QuizController`) で `PageRequest.of(0, 10)` を渡すように修正
- [ ] 苦手問題出題用の新メソッドを追加
  - 苦手な `Word` エンティティのリストを返すメソッド `findWeakWordsByUserId(Long userId, Pageable pageable)`
  - 「ユーザーの不正解数が多い順」に `Word` を取得するJPQLクエリを定義

---

### ステップ2: 苦手問題出題機能（新機能）

> **要件定義書より:**
> Phase3: 「苦手単語（誤答率の高いもの）」のみを指定しての一問一答形式の出題

#### 2-1. バックエンド: 苦手単語取得ロジックの実装

- [ ] `QuizHistoryRepository` に苦手単語取得クエリを追加（ステップ1-4で実施）
  - 「不正解回数が1回以上の `Word` を、不正解回数が多い順 & 最近間違えた順に取得する」クエリ
  ```sql
  -- 対応するJPQL（イメージ）
  SELECT qh.word FROM QuizHistory qh
  WHERE qh.user.id = :userId AND qh.isCorrect = false
  GROUP BY qh.word.id
  ORDER BY COUNT(qh.id) DESC, MAX(qh.answeredAt) DESC
  ```

- [ ] `QuizService` に苦手単語取得・出題メソッドを追加
  - `getWeakWords(User user)`: 苦手単語リストを返すメソッド
  - `getRandomWeakWord(User user, Collection<Long> excludedIds)`: 苦手単語リストの中からランダムに1件取得するメソッド
  - `countWeakWords(User user)`: 苦手単語の件数を返すメソッド（出題可能か判定用）

#### 2-2. バックエンド: QuizController の拡張

- [ ] `QuizSessionDto` に出題モードを管理するフィールドを追加
  - フィールド名: `quizMode`（例: `"all"` または `"weak"`）

- [ ] `POST /quiz/start` の処理を拡張
  - リクエストパラメータに `quizMode`（`"all"` or `"weak"`）を追加
  - `quizMode = "weak"` の場合:
    - 苦手単語の件数が 0 件であれば「苦手単語がまだありません」のエラーメッセージを表示して設定画面に返す
    - 件数が出題数より少なければ確認画面に遷移
    - `quizSession.setQuizMode("weak")` を設定して通常フローへ

- [ ] `GET /quiz` の `showQuiz()` メソッドを拡張
  - `quizSession.getQuizMode()` が `"weak"` の場合は `quizService.getRandomWeakWord()` を呼び出す

#### 2-3. フロントエンド: クイズ設定画面 (`quiz_settings.html`) の修正

- [ ] 出題モード選択UIを追加
  - 「すべての単語から出題」と「苦手な単語から出題」の2択ラジオボタン
  - デフォルトは「すべての単語から出題」
  - POSTフォームの送信パラメータに `quizMode` を追加

- [ ] 「苦手な単語から出題」選択時はカテゴリ選択を非活性にする（JavaScriptで制御）

#### 2-4. フロントエンド: 確認画面 (`quiz_confirm.html`) の修正（軽微）

- [ ] `quizMode` の値もフォームの隠しパラメータ（`hidden input`）として保持するよう追記
  - これにより確認後に「苦手モード」の情報が引き継がれる

---

### ステップ3: 動作確認

- [ ] **コード品質改善の確認**
  - アプリ起動時にログがコンソールに正しく出力されることを確認（`INFO` / `WARN`）
  - クイズ完了後、AIフィードバックが問題なく取得できることを確認

- [ ] **苦手問題出題モードの確認**
  - クイズ設定画面で「苦手な単語から出題」を選択できることを確認
  - 苦手単語が0件の場合にエラーメッセージが表示されることを確認
  - 苦手単語が存在する場合に、過去に間違えた単語のみが出題されることを確認
  - クイズ終了後の結果まとめ・AIフィードバックが正常に表示されることを確認

---

## ファイル変更一覧（まとめ）

| ファイル | 変更種別 | 内容 |
|---|---|---|
| `GeminiService.java` | 修正 | System.out.println → Logger, コメント修正 |
| `QuizService.java` | 修正 | @Transactional追加、苦手単語取得メソッド追加 |
| `QuizController.java` | 修正 | QuoteService使用、quizMode対応 |
| `QuoteService.java` | **新規作成** | 名言リストの管理とランダム取得 |
| `QuizHistoryRepository.java` | 修正 | Pageable対応、苦手Wordクエリ追加 |
| `QuizSessionDto.java` | 修正 | quizModeフィールド追加 |
| `quiz_settings.html` | 修正 | 出題モード選択UI追加 |
| `quiz_confirm.html` | 修正 | quizModeの引き継ぎ追加 |

---

## ブランチ

- ブランチ名: `issues/phase6`
- ベースブランチ: `main`（Phase5マージ後）

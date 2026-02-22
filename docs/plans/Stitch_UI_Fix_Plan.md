# UI/UX移行計画 (Stitchデザインの適用)

現行の機能（Thymeleafを利用したSpring Bootアプリケーション）に対して、`/docs/design` にある `Stitch1.md` 〜 `Stitch8.md` のデザインを適用するための実行計画です。

## 概要
各Stitchファイルのデザイン要素（Tailwind CSSクラス、Google Fonts、マテリアルアイコン、Neumorphismデザイン）を、既存の機能要件（`th:each`、`sec:authorize`、フォーム送信など）を維持したまま各HTMLテンプレートに統合します。

## 対象ファイルと対応関係
| デザイン指定 (Stitch) | 適用先テンプレート (`src/main/resources/templates/`) | 備考 |
| :--- | :--- | :--- |
| **Stitch1.md** | `login.html`, `register.html` | ログイン／新規登録画面。フォームやエラーメッセージの再現。 |
| **Stitch2.md** | `home.html` | ホームダッシュボード。ユーザー情報の表示や全体のナビゲーションリンクを統合。 |
| **Stitch3.md** | `word_list.html` | 単語帳一覧。カテゴリごとの単語一覧表示機能（`th:each`の構成）のUIを大幅刷新。 |
| **Stitch4.md** | `word_form.html` | 単語の新規登録・編集フォーム。入力項目のUIを合わせる。 |
| **Stitch5.md** | `quiz_settings.html` | クイズ設定画面。出題範囲・カテゴリ・問題数選択のラジオボタン等をデザインに寄せる。 |
| **Stitch6.md** | `quiz.html` | クイズ実行画面。プログレスバー表示や回答入力欄のUIを反映。 |
| **Stitch7.md** | `quiz_summary.html` (及び `quiz_result.html`) | クイズ結果画面・サマリー画面。正解率の円形グラフやAIコメント欄の反映。 |
| **Stitch8.md** | `stats.html` | 学習統計画面。カテゴリ別のバッジやグラフ表示のデザインを適用。 |

## 各ステップの詳細

### Step 1: 共通要素（ヘッダー・認証・ルーティング）の整理
各ページが独立したデザインになっているため、個別に機能（Thymeleafの変数など）を埋めんでいきます。
特に `<script id="tailwind-config">` とTailwindのCDN読み込みは全ページで共通化する必要があるため、正しく配置します。

### Step 2: 認証系画面の移行 (Stitch1)
- `login.html` に機能（Spring Securityの `param.error` , `param.logout` などの表示）を埋め込む。
- 同一デザインで `register.html` （新規登録画面）も実装する。

### Step 3: ホーム画面と一覧画面の移行 (Stitch2, Stitch3)
- `home.html` に対して、Spring Securityによる `sec:authentication="name"` のユーザー名表示、各ページへのリンクを反映させる。
- `word_list.html` に対して、Thymeleafのループ処理を組み込み、登録された単語データをUI上で展開する。

### Step 4: 単語登録・クイズ関連・統計の順次移行 (Stitch4〜8)
- 各画面において、フォームの `action` や `name` 属性などをバックエンドの現行仕様に合わせて紐づけを完了させる。
- クイズ結果の円グラフパーセンテージ等は、動的に計算して `th:style` などで反映させる。

## 進め方についてのご提案
1段階ごとに数ファイルずつデザインを当て込んでは動作確認していく（画面が崩れないか、ボタンが効くかをテストする）というアプローチで進めます。よろしければ、**Step 2 (ログイン/新規登録画面)** から実装を開始してもよろしいでしょうか？

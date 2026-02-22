# UI/UX調整 実行計画

## 概要
`Stitch1.md`から`Stitch8.md`までのデザイン指示に基づき、アプリケーション全体のUI/UXを大幅に調整します。
ソフトUI（ニューモーフィズム）を採用し、統一感のあるプレミアムなデザインを実現します。

## 技術スタック
- **構造**: HTML (Thymeleaf templates)
- **スタイル**: Tailwind CSS (CDN), Vanilla CSS
- **アイコン**: Material Symbols Outlined
- **フォント**: Lexend, Noto Sans JP, Zen Kaku Gothic New

## 専門用語の解説
- **ニューモーフィズム (Neumorphism)**: UIデザインの手法の一つで、オブジェクトが背景から浮き出ているような、あるいは沈んでいるようなソフトな立体感を持たせるスタイルです。
- **レイアウト・フラグメント (Layout/Fragment)**: 複数のページで共通して使う部分（ヘッダーやメニューなど）を一つの部品として共通化する技術です。これにより、デザインの変更を一箇所で管理できます。

## 実施ステップ

### 1. 共通部品（フラグメント）の作成
各ページで共通して使用するスタイルやレイアウトを共通化します。
- `src/main/resources/templates/fragments/layout.html` の作成
- Tailwind CSSの設定（カラーパレット、影の設定）の共通化
- Google Fonts、Material Symbolsの読み込み設定

### 2. 各画面の調整（優先順序）

#### フェーズ1: 基本・ログイン・ホーム
- ログイン画面 (`login.html`)：`Stitch1.md`を適用
- 新規登録画面 (`register.html`)
- ホーム画面 (`home.html`)：`Stitch2.md`を適用。ナビゲーションバーの追加

#### フェーズ2: 単語管理
- 単語一覧 (`word_list.html`)：`Stitch3.md`のカード形式を採用。検索とフィルタリングのスタイル調整
- 単語登録・編集 (`word_form.html`)：`Stitch4.md`のフォームスタイルを適用

#### フェーズ3: クイズ機能
- クイズ設定 (`quiz_settings.html`)：`Stitch5.md`のカテゴリ・問題数選択スタイルを適用
- クイズ実行中 (`quiz.html`)：`Stitch6.md`のプログレスバーと回答入力スタイルを適用
- クイズ結果・サマリー (`quiz_result.html`, `quiz_summary.html`)：`Stitch7.md`の円形プログレスとAIコメント欄を適用

#### フェーズ4: 統計状況
- 学習統計 (`stats.html`)：`Stitch8.md`のカテゴリー別データ可視化スタイルを適用

### 3. ブラッシュアップ
- 全画面のレスポンシブ対応（PC・モバイル両方で美しく表示）
- 微細なアニメーション（ボタンのホバー・クリック効果）の追加

## 完了条件
- 全ての指示された画面が`Stitch`ドキュメントのデザイン基準を満たしていること
- ページ間の遷移がスムーズで、デザインに一貫性があること
- Tailwind CSSの設定が共通化され、保守性が向上していること

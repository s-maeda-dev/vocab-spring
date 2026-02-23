# UI/UX移行計画 フェーズ2 (UI崩れの解消・共通化対応)

現状のアプリケーションにて全体的な表示不具合（レイアウト崩れやアイコン落ち、配色の不一致など）が発生していることが確認されました。以下の原因に基づき、改修を進めるための実行計画です。

## 1. 発生している不具合の原因

*   **Tailwind設定とCSSの重複による競合**:
    各HTMLファイル (`home.html`, `login.html`, `words.html` など) の `<head>` 内で、それぞれ個別に `tailwind.config` や カスタムCSS (`<style>`) が直書きされています。
    これにより、ページごとにテーマカラー（`primary`の色コードなど）や、独自のボックスシャドウ設定（`neu-flat`、`neu-pressed`等）の定義がバラバラになり、画面遷移時にデザインが変わったり、一部のクラスが適用されないという事象が発生しています。
*   **共通部品 (Bottom Navigation等) のデザイン崩れ**:
    各ページでCSS設定が異なるため、`fragments/bottom_nav.html` のような共通コンポーネントが正しくレンダリングされず、レイアウト崩れの一因となっている可能性があります。

## 2. 修正ステップ

### Step 1: 共通ヘッダー（head）のフラグメント化
*   `src/main/resources/templates/fragments/head.html` を新規作成します。
*   このファイルに、全ページ共通の以下要素を集約します：
    *   Google Fonts の読み込み (`Lexend`, `Noto Sans JP`)
    *   Material Symbols の読み込み
    *   Tailwind CSS (CDN) とプラグインの読み込み
    *   `<script id="tailwind-config">` による、統一されたテーマカラーと CSS カスタム設定（Neumorphismデザイン用のシャドウやユーティリティクラス等）

### Step 2: 各HTMLテンプレートからの重複コード削除
*   対象ファイル: `login.html`, `register.html`, `home.html`, `word_list.html`, `word_form.html`, `quiz_settings.html`, `quiz.html`, `quiz_summary.html`, `stats.html` など。
*   各ファイルに散在している直接の `<head>` 設定を削除し、Thymeleaf の `th:replace="~{fragments/head :: head}"` を用いて一元化します。

### Step 3: カラーパレットとデザインの統一確認
*   全体で統一する `primary` カラーや、Neumorphism の背景色 (`bg-base` または `background-light`) を一つに固定します。
*   画面ごとの固有の微調整が必要な箇所のみを各ファイルに残し、それ以外は共通クラスを使う状態にします。

## 3. 進め方

この計画を実行するにあたり、まずは新しいブランチ（例: `/issues/fix-ui-inconsistency`）を作成し、Step 1 と Step 2 の「共通化」を適用します。その後、ブラウザでの動作確認を行い、表示崩れが完全に解消されたかを確認します。

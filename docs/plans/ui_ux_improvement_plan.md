# UI/UX改善 実行計画書 (Neumorphism Design Implementation)

## 1. 概要 (Overview)
本計画は、`vocab-spring` アプリケーションのUI/UXを、提供された「Stitch」デザイン（ニューモーフィズム・スタイル）に基づいて全面的に刷新するものです。
**ニューモーフィズム (Neumorphism)** とは、ソフトな影（ドロップシャドウとインナーシャドウ）を組み合わせて、要素が背景から浮き出ている、あるいは凹んでいるように見せるモダンなデザイン手法です。

## 2. 実装の基本方針 (Design Principles)
- **技術スタック**: Tailwind CSS v3, Google Fonts (Inter), Material Symbols.
- **共通スタイル**: 全てのページで一貫したニューモーフィズム・スタイルを適用します。
- **モバイルファースト**: デザインに基づき、スマートフォンでの利用を意識したレイアウトを採用します。
- **再利用性**: Thymeleafのフラグメント機能を活用し、ヘッダーやナビゲーションを共通化します。

## 3. 実装フェーズ (Implementation Phases)

### フェーズ1: デザイン基盤の構築 (Design Foundation)
- [ ] **共通レイアウトの作成**: `layout.html` を作成し、Tailwind CSS、Google Fonts、共通CSSのインポートをまとめます。
- [ ] **共通CSS (`neumorphism.css`) の作成**: デザインファイルから抽出したカラーパレット、影の設定（neu-raised, neu-pressed等）をユーティリティクラスとして定義します。
- [ ] **ベースカラーの設定**:
  - Primary: `#a49279`
  - Background Light: `#f5f4f2` / `#f0f0f0`
  - Text: Slate 900 / Slate 500

### フェーズ2: 共通コンポーネントの実装 (Common Components)
- [ ] **ボトムナビゲーション**: `Stitch1` にあるような、Home, Library, Search, Profile 等のアイコンが並ぶ共通ナビゲーションを作成します。
- [ ] **ヘッダー**: ユーザー情報や通知アイコンを含む共通ヘッダーを作成します。

### フェーズ3: 各画面の刷新 (Page Redesign)

#### タスク1: ホーム画面 (Dashboard) - `Stitch1` 参考
- [ ] `home.html` のリニューアル。
- [ ] 学習状況の円形ゲージ（Mastery Gauge）の実装。
- [ ] 学習統計カード（Stats Grid）の実装。
- [ ] 最近の学習セット（Recent Decks）のリスト表示。

#### タスク2: 単語管理画面 (Words & Categories) - `Stitch3` 参考
- [ ] `word_form.html`: 洗練された入力フィールド（neu-recessed）を持つ登録フォームへ刷新。
- [ ] `word_list.html`: カテゴリ別の単語リストをカード形式またはクリーンなテーブル形式で再構築。
- [ ] `categories.html`: カテゴリ管理画面のスタイル適用。

#### タスク3: クイズ画面 (Quiz Experience) - `Stitch2` 参考
- [ ] `quiz_settings.html`: 出題設定（モード選択、問題数選択）をニューモーフィズムのカード形式へ刷新。
- [ ] `quiz.html`: フラッシュカード形式のクイズ画面。
- [ ] `quiz_result.html` / `quiz_summary.html`: 結果表示とAIフィードバック画面の改善。

### フェーズ4: 仕上げと認証画面 (Final Polish & Auth)
- [ ] **ログイン・登録画面**: `login.html`, `register.html` へのスタイル適用。
- [ ] **レスポンシブ調整**: デスクトップ表示での崩れがないか確認・修正。
- [ ] **マイクロインタラクション**: ボタン押下時の凹む効果（neu-pressed）などのアニメーション追加。

## 4. 専門用語の解説 (Technical Terms)
- **Tailwind CSS**: クラスを指定するだけでデザインを当てられる便利なCSSフレームワークです。
- **ニューモーフィズム (Neumorphism)**: UI要素が背景と同じ色で、影によって凹凸を表現するデザインスタイルです。
- **フラグメント (Thymeleaf Fragments)**: 画面の共通パーツ（ヘッダーなど）を部品化して、複数のページで使い回す仕組みです。
- **レスポンシブ (Responsive)**: スマホやPCなど、画面サイズに合わせて表示を最適化することです。

## 5. 次のステップ (Next Steps)
本計画書が承認され次第、新しいブランチ `/issues/ui-ux-refactor` を作成し、フェーズ1から実装を開始します。

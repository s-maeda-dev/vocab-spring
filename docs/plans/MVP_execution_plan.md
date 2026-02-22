# MVP実行計画書 (Execution Plan)

本書は、`docs/MVP要件定義書.md` に基づき、VocabSpring（My単語帳）のMVP開発を進めるための実行計画です。

## Phase1: 環境構築とプロジェクト基盤
開発環境の整備と、アプリケーションの骨格を作成します。

### 1-1. プロジェクト初期化
- [ ] Spring Initializrを用いたプロジェクト作成 (Maven, Java 21, Spring Boot 3.x)
- [ ] 依存関係の定義 (`pom.xml`):
    - Spring Boot Starter Web
    - Spring Boot Starter Security
    - Spring Boot Starter Data JPA
    - Spring Boot Starter Thymeleaf
    - MySQL Driver
    - Lombok (任意)
    - Spring Boot DevTools
- [ ] `.gitignore` の設定

### 1-2. データベース環境構築
- [ ] MySQLデータベース `vocab_spring_db` の作成
- [ ] `application.properties` (または `.yml`) のDB接続設定
- [ ] ユーザー (`users`)、単語 (`words`)、カテゴリ (`categories`)、学習履歴 (`study_logs`) のER図作成（簡易版）とEntityクラス作成

## Phase2: ユーザー認証機能の実装
ユーザーごとのデータ管理を可能にするための認証基盤を構築します。

### 2-1. Spring Security設定
- [ ] `SecurityConfig` クラスの作成
- [ ] パスワードエンコーダー (`BCryptPasswordEncoder`) の設定
- [ ] ログイン・ログアウト処理の定義
- [ ] 未認証ユーザーのアクセス制限設定

### 2-2. ユーザー登録・ログイン画面
- [ ] Login Controller & Serviceの実装 (`CustomUserDetailsService`)
- [ ] ログイン画面 (`login.html`) の作成 (Thymeleaf + CSS)
- [ ] ユーザー登録画面 (`register.html`) の作成
- [ ] 登録処理のControllerとService実装

## Phase3: 単語管理機能の実装
ユーザーが自分の単語帳を作成・管理する機能です。

### 3-1. カテゴリ管理
- [ ] カテゴリEntityの作成
- [ ] カテゴリ登録・一覧表示機能の実装 (ホーム画面の作成含む)
- [ ] ログアウト機能の動作確認 (Phase 2より移動)

### 3-2. 単語CRUD機能
- [ ] 単語Entity (`Word`) の作成（User, Categoryと関連付け）
- [ ] 単語登録画面 (`word_form.html`) の作成
- [ ] 単語一覧画面 (`word_list.html`) の作成（ページネーション検討）
- [ ] 編集・削除機能の実装

## Phase4: クイズ機能の実装
学習のコアとなる一問一答機能です。

### 4-1. 出題ロジック
- [ ] ランダム出題機能の実装（Service層）
- [ ] カテゴリ指定出題機能の実装

### 4-2. クイズ画面・正誤判定
- [ ] クイズ出題画面 (`quiz.html`) の作成
- [ ] 解答送信と正誤判定ロジックの実装（完全一致）
- [ ] クイズ結果画面 (`quiz_result.html`) の作成

## Phase5: 学習履歴とAI連携
学習効果を高めるためのフィードバック機能です。

### 5-1. 履歴保存
- [ ] `StudyLog` Entityの作成
- [ ] クイズ結果の保存処理実装
- [ ] 苦手単語（正答率などの算出ロジック）の実装

### 5-2. Google Gemini API連携
- [ ] Google Cloud Project設定とAPIキー取得
- [ ] Gemini APIクライアントの実装 (Java Client または REST Template)
- [ ] プロンプト設計（正答率、苦手単語を含めた励ましメッセージ生成）
- [ ] クイズ結果画面へのAIフィードバック表示

## Phase6: テストとデプロイ準備
- [ ] 単体テスト (JUnit)
- [ ] E2E動作確認
- [ ] CSRF対策の有効化 (`SecurityConfig` の設定戻し)
- [ ] UI/UXの微調整
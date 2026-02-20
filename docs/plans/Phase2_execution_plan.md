# Phase 2 実行計画書: ユーザー認証機能の実装

本ドキュメントは、MVP実行計画書の「Phase2: ユーザー認証機能の実装」を詳細化した作業手順書です。

## 1. ユーザーリポジトリとサービス層の準備

### 1-1. UserRepository の作成
- [x] `com.vocabulary.vocab_spring.repository.UserRepository` インターフェースを作成
  - `JpaRepository<User, Long>` を継承
  - `Optional<User> findByUsername(String username);` メソッドを定義

### 1-2. CustomUserDetailsService の実装
このクラスはSpring Securityがユーザー情報をDBからロードするために使用します。
- [x] `com.vocabulary.vocab_spring.service.CustomUserDetailsService` クラスを作成
  - `UserDetailsService` インターフェースを実装
  - `loadUserByUsername` メソッドをオーバーライドし、`UserRepository` からユーザーを検索
  - 検索結果を `org.springframework.security.core.userdetails.User` オブジェクトに変換して返す

## 2. Spring Security 設定

### 2-1. SecurityConfig クラスの作成
- [x] `com.vocabulary.vocab_spring.config.SecurityConfig` クラスを作成
  - `@Configuration`, `@EnableWebSecurity` アノテーションを付与
  - `SecurityFilterChain` Beanを定義
    - パスごとのアクセス許可設定 (`/login`, `/register`, `/css/**`, `/js/**` は許可、それ以外は認証必須)
    - フォームログイン設定
      - ログインページ: `/login` (GET)
      - ログイン処理URL: `/login` (POST - Spring Securityデフォルト)
      - 成功時: `/` (ホーム) または `/dashboard` へリダイレクト
      - 失敗時: `/login?error`
    - ログアウト設定
      - ログアウトURL: `/logout`
      - ログアウト成功時: `/login?logout`

### 2-2. PasswordEncoder のBean定義
- [x] `SecurityConfig` 内、または別の設定クラスで `PasswordEncoder` (BCryptPasswordEncoder) のBeanを定義

## 3. コントローラーとビューの実装 (Thymeleaf)

### 3-1. AuthController の作成
- [x] `com.vocabulary.vocab_spring.controller.AuthController` クラスを作成
  - `@Controller` アノテーション
  - GET `/login`: `login.html` を返す
  - GET `/register`: `register.html` を返す
  - POST `/register`: ユーザー登録処理を行うメソッド
    - フォーム入力値を受け取り、Service層経由でDBに保存 (パスワードはハッシュ化)
    - 成功時は `/login` へリダイレクト

### 3-2. UserService (登録ロジック) の追加
- [x] `com.vocabulary.vocab_spring.service.UserService` クラスを作成 (または既存Service拡張)
  - `registerUser(String username, String rawPassword, String email)` メソッドを実装
  - 重複チェックを行い、パスワードをハッシュ化して保存

### 3-3. ログイン画面 (login.html)
- [x] `src/main/resources/templates/login.html` を作成
  - Thymeleafを使用
  - エラーメッセージ (`param.error`) とログアウトメッセージ (`param.logout`) の表示領域を用意
  - ユーザー名、パスワードの入力フォーム
  - CSRFトークンを含める (Thymeleafの `th:action` を使えば自動挿入される)

### 3-4. ユーザー登録画面 (register.html)
- [x] `src/main/resources/templates/register.html` を作成
  - ユーザー名、パスワード、メールアドレスの入力フォーム
  - バリデーションエラー表示領域 (簡易版)

## 4. 動作確認

### 4-1. 登録機能の確認
- [x] `/register` にアクセスし、新規ユーザーを登録できることを確認
- [x] DB (`users` テーブル) にユーザーが保存され、パスワードがハッシュ化されていることを確認

### 4-2. ログイン機能の確認
- [x] `/login` から登録済みユーザーでログインできることを確認
- [x] ログイン後、トップページ (または保護されたページ) に遷移することを確認
- [x] 未登録ユーザーや間違ったパスワードでログインできないことを確認

### 4-3. ログアウト機能の確認
- [ ] ログアウトボタンを押下し、ログアウトできることを確認 (Phase 3へ移動: ウェルカムページ実装後に確認)

## 備考
- **CSRF対策について**: テスティングのために一時的にCSRF対策を無効化しています(`SecurityConfig`)。これは開発中の利便性を優先した措置ですが、本番運用前には有効化し、フォームにCSRFトークン(`th:action`で自動付与されるもの)を含める必要があります。Phase 6のデプロイ準備にて対応します。

# Phase 3 実行計画書: 単語管理機能の実装

本ドキュメントは、MVP実行計画書の「Phase3: 単語管理機能の実装」を詳細化した作業手順書です。

## 1. ホーム画面とログアウト機能の確認

### 1-1. HomeController の作成
- [x] `com.vocabulary.vocab_spring.controller.HomeController` クラスを作成
  - `@Controller` アノテーション
  - GET `/`: `home.html` (ダッシュボード) を返す
  - 認証済みのユーザー情報 (`@AuthenticationPrincipal` または `Principal`) を受け取り、画面に表示する

### 1-2. ホーム画面 (home.html) の作成
- [x] `src/main/resources/templates/home.html` を作成
  - ログインユーザー名を表示
  - 「ログアウト」ボタンを配置 (`/logout` へのPOSTフォーム)
  - 「カテゴリ一覧」「単語登録」などのナビゲーションリンクを配置

### 1-3. ログアウト機能の動作確認 (Phase 2 残作業)
- [x] アプリケーションを起動し、ログイン後にホーム画面が表示されることを確認
- [x] ホーム画面のログアウトボタンを押下し、ログイン画面へリダイレクトされることを確認
- [x] ログアウト後、ホーム画面 (`/`) にアクセスしようとするとログイン画面へリダイレクトされることを確認

## 2. カテゴリ管理機能の実装

### 2-1. CategoryRepository の作成
- [x] `com.vocabulary.vocab_spring.repository.CategoryRepository` インターフェースを作成
  - `JpaRepository<Category, Long>` を継承
  - `List<Category> findByUser(User user);` メソッドを定義

### 2-2. CategoryService の実装
- [x] `com.vocabulary.vocab_spring.service.CategoryService` クラスを作成
  - ログインユーザーに紐づくカテゴリ一覧の取得
  - 新規カテゴリの登録 (ユーザー紐づけ含む)

### 2-3. カテゴリコントローラーとビュー
- [x] `com.vocabulary.vocab_spring.controller.CategoryController` クラスを作成
  - GET `/categories`: カテゴリ一覧ページ (`categories.html`) を表示
  - POST `/categories/add`: 新規カテゴリ登録処理
- [x] `src/main/resources/templates/categories.html` を作成
  - 既存カテゴリの一覧表示
  - 新規カテゴリ追加フォーム (名前入力)

## 3. 単語管理機能 (CRUD) の実装

### 3-1. WordRepository の作成
- [x] `com.vocabulary.vocab_spring.repository.WordRepository` インターフェースを作成
  - `JpaRepository<Word, Long>` を継承
  - `List<Word> findByUser(User user);`
  - `List<Word> findByUserAndCategory(User user, Category category);`

### 3-2. WordService の実装
- [x] `com.vocabulary.vocab_spring.service.WordService` クラスを作成
  - 単語の登録・編集・削除機能
  - カテゴリID指定での単語検索機能
  - ユーザー権限チェック (自分の単語のみ操作可能にする)

### 3-3. 単語コントローラーの実装
- [x] `com.vocabulary.vocab_spring.controller.WordController` クラスを作成
  - GET `/words`: 全単語一覧表示 (`word_list.html`)
  - GET `/words/new`: 新規登録フォーム (`word_form.html`)
  - POST `/words`: 保存処理
  - GET `/words/edit/{id}`: 編集フォーム
  - POST `/words/update/{id}`: 更新処理
  - POST `/words/delete/{id}`: 削除処理

### 3-4. 単語管理ビューの作成
- [x] `src/main/resources/templates/word_list.html`
  - 登録単語の一覧表示 (カテゴリ名含む)
  - 編集・削除ボタン
- [x] `src/main/resources/templates/word_form.html`
  - 単語、意味、例文、カテゴリ選択 (ドロップダウン) の入力フォーム

## 4. 動作確認

### 4-1. カテゴリ機能の確認
- [x] カテゴリを数件登録し、一覧に表示されることを確認
- [x] 別のユーザーでログインした際、他人のカテゴリが表示されないことを確認

### 4-2. 単語CRUD機能の確認
- [x] 新規単語をカテゴリを選択して登録できることを確認
- [x] 一覧画面で登録内容が正しく表示されることを確認
- [x] 編集機能で内容を変更し、保存されることを確認
- [x] 削除機能で単語が消去されることを確認

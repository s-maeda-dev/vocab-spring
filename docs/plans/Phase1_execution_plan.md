# Phase 1 実行計画書: 環境構築とプロジェクト基盤

本ドキュメントは、MVP実行計画書の「Phase1: 環境構築とプロジェクト基盤」を詳細化した作業手順書です。

## 1. プロジェクト初期設定

### 1-1. 依存関係の確認と整理
- [x] `pom.xml` を開き、以下の依存関係が含まれているか確認する
    - `spring-boot-starter-web` (注意: `webmvc` ではなく `web` 推奨)
    - `spring-boot-starter-security`
    - `spring-boot-starter-data-jpa`
    - `spring-boot-starter-thymeleaf`
    - `mysql-connector-j`
    - `lombok` (Optional)
    - `spring-boot-devtools`
- [x] 足りないものがあれば追加・修正する

### 1-2. .gitignore の設定
- [x] `.gitignore` に `.env` や IDE固有の設定ファイルが含まれているか確認する
- [x] 足りなければ追加する

## 2. データベース環境構築

### 2-1. データベース作成
- [x] MySQLコマンドラインまたはGUIツールで `vocab_spring_db` データベースを作成する
  ```sql
  CREATE DATABASE vocab_spring_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
  ```

### 2-2. 接続設定 (application.properties)
- [x] `src/main/resources/application.properties` を編集する
- [x] `spring.datasource.url`, `username`, `password` を設定する
- [x] JPAのDDL設定 (`hibernate.ddl-auto=update`) を追加し、Entityから自動的にテーブルが生成されるようにする

## 3. エンティティの作成 (ER図の具現化)

### 3-1. User Entity (Usersテーブル)
- [x] `com.vocabulary.vocab_spring.entity.User` クラスを作成
  - `id` (Long, PK, Auto Increment)
  - `username` (String, Unique)
  - `password` (String)
  - `role` (String)
  - `email` (String, 任意)

### 3-2. Category Entity (Categoriesテーブル)
- [x] `com.vocabulary.vocab_spring.entity.Category` クラスを作成
  - `id` (Long, PK, Auto Increment)
  - `name` (String)
  - `user` (ManyToOne -> User)

### 3-3. Word Entity (Wordsテーブル)
- [x] `com.vocabulary.vocab_spring.entity.Word` クラスを作成
  - `id` (Long, PK, Auto Increment)
  - `term` (String, 単語)
  - `definition` (String, 意味)
  - `exampleSentence` (String, 例文 - 任意)
  - `user` (ManyToOne -> User)
  - `category` (ManyToOne -> Category)

### 3-4. StudyLog Entity (StudyLogsテーブル)
- [x] `com.vocabulary.vocab_spring.entity.StudyLog` クラスを作成
  - `id` (Long, PK, Auto Increment)
  - `correctRate` (Double, 正答率)
  - `studiedAt` (LocalDateTime)
  - `user` (ManyToOne -> User)

## 4. 動作確認

- [x] `mvn spring-boot:run` を実行し、エラーなく起動することを確認する
- [x] データベースを確認し、テーブルが自動生成されていることを確認する

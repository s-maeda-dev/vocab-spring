# 🚀 Vocab-Spring システム設計図 (System Blueprint)

このドキュメントでは、汎用単語・用語学習アプリ `わたしの単語帳 VocabularySpring` の全体像を、エンジニアの視点とプログラミング初学者のわかりやすさを両立して解説します。

---

## 1. 🌟 システム概要
`わたしの単語帳 VocabularySpring` は、英単語に限らず、専門用語、IT用語、資格試験の対策など、あらゆる「暗記と理解」をサポートする自分専用のデジタル単語帳アプリです。

**主な特徴:**
- **カテゴリ別管理**: 用語をカテゴリごとに分けて整理・学習。
- **AI（Gemini）チューター**: 学習結果に対して、AIが専用のフィードバックや励ましのコメントを提供。
- **データ分析（統計）**: 正答率や「苦手な単語」をカテゴリ別に自動で集計し、弱点克服をサポート。
- **洗練されたデザイン**: 最新の Neumorphism（ニューモーフィズム）デザインを採用し、直感的で美しいUIを提供。

---

## 2. 🏗️ システムアーキテクチャ（全体構造）
アプリ全体は、画面（フロントエンド）と裏側の処理（バックエンド）が分担して働く「3層アーキテクチャ」で構築されています。

```mermaid
graph TD
    User[ユーザー (ブラウザ)] <--> UI[フロントエンド (Thymeleaf / Tailwind CSS)]
    
    subgraph Local Development (自分のPC)
        subgraph Spring Boot Application
            UI <--> Controller[Controller層]
            Controller <--> Service[Service層]
            Service <--> Repository[Repository層]
        end
        IDE[Windsurf / VS Code] --> |開発・デバッグ| SpringBoot
        Env[".env (API Key管理)"] -.-> Service
    end

    Repository <--> DB[(MySQL)]
    Service <--> GeminiAPI[Google Gemini AI]

    IDE --> |Push: バージョン管理| GitHub[(GitHub Repository)]
    GitHub --> |管理| SourceCode[ソースコード / README]
```

---

## 3. 🛠️ 技術スタック

| カテゴリ | 使用技術 | ポイント解説 |
| :--- | :--- | :--- |
| **言語** | Java 17 | 厳密な型定義を持つJavaを採用し、バグの少ない堅牢なバックエンドを構築。 |
| **フレームワーク** | Spring Boot 3.4.x | 実務のデファクトスタンダード。DIやAOPといった概念を学びながら実装。 |
| **データベース** | MySQL | 業界標準のRDBMS。データの永続化と、履歴分析のための複雑なクエリ（SQL）を処理。 |
| **AI 連携** | Google Gemini API | 最新の生成AIを活用し、ユーザー体験（UX）を向上させる独自のロジックを実装。 |
| **コード管理** | GitHub | Gitを用いたバージョン管理を徹底。READMEの整備を含め、共同開発を意識した運用。 |
| **セキュリティ** | Spring Security / .env | 認証・認可の仕組みを理解し、環境変数によるAPIキーの隠蔽など安全な開発を実践。 |
| **デザイン** | Neumorphism / Tailwind | Tailwind CSSによる効率的なスタイリングと、直感的なニューモーフィズムUIの両立。 |

---

## 4. 🗄️ データベース設計（データの保存ルール）
システムは主に4つのテーブル（データの入れ物）で構成されています。

### ① `users` (ユーザー)
- **役割**: ログイン情報とユーザーごとの設定を管理。
- **主な項目**: `id`, `username`, `password`

### ② `categories` (カテゴリ)
- **役割**: 単語をグループ分けする箱（例：「基本情報技術者」「TOEIC」など）。
- **主な項目**: `id`, `name`, `user_id`

### ③ `words` (単語・用語)
- **役割**: 実際に学習する用語のデータ。
- **主な項目**: `id`, `term` (単語名/正解), `definition` (意味/問題), `category_id`

### ④ `quiz_histories` (クイズ履歴)
- **役割**: クイズ1問ごとの「いつ」「どの単語を」「正解したか」の記録。「苦手単語」の抽出に使われます。
- **主な項目**: `id`, `user_id`, `word_id`, `is_correct` (正解したか)

---

## 5. 🗺️ 画面一覧とURL（ルーティング）

| 画面名 | URLパス | 役割・機能 |
| :--- | :--- | :--- |
| **ホーム** | `/` | ログイン後のトップページ。最新の学習サマリーを表示。 |
| **ログイン/登録** | `/login`, `/register` | 認証画面。水滴のアイコンとおしゃれな凹凸デザイン。 |
| **単語帳** | `/words` | 登録した単語をカテゴリごとに一覧表示。 |
| **単語追加/編集** | `/words/new`, `/words/edit/{id}` | 新しい単語の登録、既存単語の修正。 |
| **クイズ設定** | `/quiz/settings` | 出題カテゴリ、出題範囲（全単語/苦手のみ）、問題数（5/10問）を選択。 |
| **クイズ実行** | `/quiz`, `/quiz/answer` | 1問ずつ問題を表示し、解答を判定して正誤を表示。 |
| **クイズ完了** | `/quiz/summary` | 全問終了後の結果発表。AIからのフィードバックと偉人の名言を表示。 |
| **学習統計** | `/stats` | カテゴリごとの正答率グラフ、累計解答数、登録単語数、そして「苦手単語一覧」を視覚的に表示。 |

---

## 6. 🤖 独自の魅力機能（AI & インスピレーション）

### 1. AI 専属チューター (`GeminiService`)
- クイズが終わると、その成績（どの単語を間違えたか等）を裏側でGoogle Gemini AIに送信します。
- AIが「優しいチューター」として、間違えた用語の補足説明や、次へ向けての励ましのコメントを自動生成します✨

### 2. 偉人の名言システム (`QuoteService`)
- プログラム内に厳選された名言リスト（エジソン、アインシュタイン等）を保持。
- 学習の終わりにランダムで表示し、ユーザーの「あと一歩」のモチベーションを支えます。

### 3. 動的「苦手単語」抽出システム (`StatsService`)
- 過去のクイズ履歴（`quiz_histories`）を瞬時に分析し、一度でも間違えた単語を「苦手単語」としてピックアップします。
- クイズ設定で「苦手な単語のみ」を選んで集中的に弱点を克服できます。

---

## 7. 🧩 プログラムの繋がり図（ファイル相関図）
実際にコードを書く際に、どのファイルが連携しているかを表した図です。

```mermaid
graph LR
    subgraph "画面 (UI View)"
        HTML["*.html (各画面)<br/>+<br/>fragments (共通部品)"]
    end

    subgraph "コントローラー (窓口)"
        QC["Controller群<br/>(Quiz, Stats, Word等)"]
    end

    subgraph "サービス (ロジック)"
        QS["QuizService (出題・履歴)"]
        SS["StatsService (集計・分析)"]
        GS["GeminiService (AI連携)"]
    end

    subgraph "データ管理 (DB操作)"
        R["Repository群<br/>(Word, History等)"]
        E["Entity<br/>(DBの形を表すJava)"]
    end

    HTML <--> QC
    QC --> QS
    QC --> SS
    QC --> GS
    QS --> R
    SS --> R
    R ..> E
```

---

## 8. 📂 ファイルの場所ガイド (File Location Map)
VS Codeのエクスプローラーで特定のファイルを探す際の地図です。

```text
vocab-spring (プロジェクトのルート)
 ├── .env                         ( APIキー(Gemini)を安全に隠しておく場所 )
 ├── src/main/java/com/vocabulary/vocab_spring
 │    ├── config/                 # 認証・セキュリティ設定 (SecurityConfig)
 │    ├── controller/             # ② 各機能の「窓口係」(ブラウザからのURLを受け取る)
 │    ├── entity/                 # ③ データの「設計図」(Word, User, Category)
 │    ├── repository/             # ④ DBの「出し入れ係」(SQLを自動で作ってくれる)
 │    ├── service/                # ⑤ みんなに指示を出す「司令塔」(高度な計算やAI通信)
 │    │    ├── QuizService.java      (クイズ出題・苦手単語抽出)
 │    │    ├── StatsService.java     (カテゴリ別統計データの集計)
 │    │    └── GeminiService.java    (AI(Gemini)との双方向通信)
 │    └── dto/                    # DBの生データではなく画面に渡すためだけの「まとめ箱」
 └── src/main/resources
      ├── application.properties   (システム全体の基本設定、DB接続先など)
      └── templates/              # ① HTML（ユーザーが見る画面そのもの）
           ├── fragments/            (★便利！ 全画面で使い回す共通パーツ)
           │    ├── head.html          (フォントやTailwind設定の共通化)
           │    └── bottom_nav.html    (画面下のアイコン付きナビゲーション)
           ├── login.html            (ログイン画面)
           ├── stats.html            (統計画面)
           └── ...その他各画面のHTML
```

---

## 9. 🎯 アプリ開発の基本ルール（まとめ）
このアプリは、プロの開発現場で使われる**「3層アーキテクチャ」**という整理整頓のルールに基づいて作られています。新しい機能を追加するときは、以下の順番で作業するとスムーズです！

1. **Entity (エンティティ)**: 保存したいデータの「項目」を決める（単語名、意味など）
2. **Repository (リポジトリ)**: データを保存・検索する「命令」を作る（例：「苦手な単語を探す」）
3. **Service (サービス)**: 具体的な「計算・判定ルール」を作る（例：「正答率をパーセントで計算する」）
4. **Controller (コントローラー)**: ブラウザからリクエストを受け取り、Serviceに指示を出し、結果をHTMLに渡す
5. **HTML / Fragments**: Thymeleafを使って結果をきれいに画面に表示する（部品化して使い回す！）



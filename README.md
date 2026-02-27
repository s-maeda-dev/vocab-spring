# 📖 わたしの単語帳 VocabularySpring

『単語のCRUD管理』『 弱点克服のクイズ機能』『Gemini APIと連携したAIチャーター』などを備えた、大人のための学習支援・単語帳Webアプリケーション

<img width="506" height="362" alt="image" src="https://github.com/user-attachments/assets/b051b718-4567-4e89-8d83-b36b1f556fe6" />

## 💡 開発の背景とエピソード（Why I built this）

**開発の背景（Background）**

本アプリの開発の原点は、私自身の学習体験にあります。新しい分野の学習や資格試験に挑戦する際、日々増え続ける専門用語を単にストックするだけでなく、「自分専用の辞書として一元管理したい」、そして **「試験直前に特定の要点だけを効率よく反復したい」** という切実な課題に直面しました。

既存のツールでは手が届きにくい「自分だけの重要ポイント」に特化した学習環境を構築したいと考えたことが、開発のきっかけです。

本プロジェクトでは、次世代IDE「Windsurf」のAIエージェントをペアプログラミングのパートナーとして採用しています。単なるコード生成に頼るのではなく、AIと対話しながら **「なぜこの設計にするのか（DIや疎結合の利点）」** を深く理解し、実務に近いモダンな開発手法を学ぶことを目的としました。

## ✨ 主な機能 (Features)
* **単語のCRUD管理**: カテゴリ別の単語登録・編集・削除機能。
* **弱点克服クイズ機能**: 過去の解答履歴（MySQL）を分析し、一度でも間違えた「苦手単語」のみを抽出して出題。
* **AIチューター（Gemini API連携）**: クイズ終了後、ユーザーの成績に応じた励ましのメッセージや補足解説を自動生成。
* **名言表示システム**: 学習後の燃え尽きを防ぐため、偉人の名言をランダム表示。
* **Neumorphism UI**: 毎日の学習が心地よくなる、立体的で直感的なデザイン（Tailwind CSS）。

## 🛠 技術スタック (Tech Stack)

| カテゴリ | 使用技術 | 選定理由・学習のポイント |
| :--- | :--- | :--- |
| **Backend** | Java 17, Spring Boot 3.4.x | 堅牢な型付けと、実務のデファクトスタンダードを学ぶため。 |
| **Database** | MySQL | 履歴分析のための複雑なクエリ処理と、データの永続化。 |
| **Frontend** | Thymeleaf, Tailwind CSS | コンポーネント（fragments）化による保守性の高いUI実装。 |
| **AI / API** | Google Gemini API | 最新の生成AIを活用した動的なUXの向上。 |
| **Version Control** | GitHub | Gitによる細かなコミット履歴の管理と、開発プロセスの可視化。 |
| **Security** | Spring Security, .env | 認証基盤の構築と、APIキー等機密情報の安全な管理（環境変数化）。 |

## 🏗️ アーキテクチャと設計のこだわり (Architecture)
本アプリは実務を意識し、 **3層アーキテクチャ（Controller / Service / Repository）** を採用しています。
※詳細なシステム設計図やクラス図は、以下のディレクトリに格納しています。
docs/design/system_blueprint.md

**【設計のポイント】**
* **疎結合な設計**: Spring Bootの **DI（依存性の注入）** を活用し、コンポーネント間の依存関係を整理。インターフェースを意識し、将来的なAIモデルの変更やデータベース移行にも強い設計を目指しました。
* **関心の分離**: **AOP（アスペクト指向）** の考え方を取り入れ、Spring Securityによる認証処理など、横断的な処理をビジネスロジックから切り離しています。

## 🚀 ローカルでの動かし方 (Getting Started)
手元の環境で動かすための手順です。

1. **リポジトリのクローン**
   ```bash
   git clone [https://github.com/あなたのユーザー名/vocab-spring.git](https://github.com/あなたのユーザー名/vocab-spring.git)
   cd vocab-spring
   
2. **環境変数（.env）の設定**
プロジェクトのルートディレクトリに `.env` ファイルを作成し、Gemini APIキーを設定してください。（※`.gitignore`により除外設定済みです）コード スニペット
    
    `GEMINI_API_KEY=あなたの_API_KEY_をここに入力`
    
3. **データベース（MySQL）の準備**
事前にMySQLを起動し、専用のデータベースを作成してください。SQL
    
    `CREATE DATABASE vocab_spring_db;`
    
    `src/main/resources/application.properties` の接続情報をローカル環境に合わせて修正します。
    
4. **アプリケーションの起動**
IDEから `VocabSpringApplication.java` を実行するか、以下のコマンドを実行します。Bash
    
    `./mvnw spring-boot:run`
    
    起動後、ブラウザで `http://localhost:8080` にアクセスしてください。
    

## 展望 (Future Prospects)

- CSV一括登録による、単語登録の効率化
- 音声認識APIを活用した、ハンズフリーでのクイズ解答機能
- ドラッグ＆ドロップによる直感的なカテゴリ並び替え機能の実装

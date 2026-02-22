package com.vocabulary.vocab_spring.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 偉人の名言を管理し、ランダムに1件返すサービス。
 * 名言を追加・変更したい場合はこのクラスの QUOTES リストを編集してください。
 */
@Service
public class QuoteService {

    // ThreadLocalRandom: スレッドセーフ（複数のユーザーが同時アクセスしても安全）なランダム生成
    private static final List<String> QUOTES = List.of(
            "「天才とは、1％のひらめきと99％の努力である。」 - トーマス・エジソン",
            "「失敗したわけではない。それを誤りだと言ってはいけない。勉強したのだと言いたまえ。」 - トーマス・エジソン",
            "「ステップ・バイ・ステップ。どんなことでも、何かを達成する場合にとるべき方法はただひとつ、一歩ずつ着実に立ち向かうことだ。」 - マイケル・ジョーダン",
            "「人生最大の栄光は、決して倒れないことではない。倒れるたびに起き上がることである。」 - ネルソン・マンデラ",
            "「学ぶことをやめたら、教えることをやめなければならない。」 - アルベルト・アインシュタイン",
            "「今日の努力は、明日の自分への贈り物だ。」 - とある哲学者");

    /**
     * 名言リストからランダムに1件取得して返します。
     *
     * @return ランダムな名言の文字列
     */
    public String getRandomQuote() {
        int index = ThreadLocalRandom.current().nextInt(QUOTES.size());
        return QUOTES.get(index);
    }
}

package com.vocabulary.vocab_spring.service;

import com.vocabulary.vocab_spring.entity.QuizHistory;
import com.vocabulary.vocab_spring.repository.QuizHistoryRepository;
import com.vocabulary.vocab_spring.entity.User;
import com.vocabulary.vocab_spring.entity.Word;
import com.vocabulary.vocab_spring.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class QuizService {

    private final WordRepository wordRepository;
    private final QuizHistoryRepository quizHistoryRepository;

    @Autowired
    public QuizService(WordRepository wordRepository, QuizHistoryRepository quizHistoryRepository) {
        this.wordRepository = wordRepository;
        this.quizHistoryRepository = quizHistoryRepository;
    }

    // ─────────────────────────────────────────────
    // 全単語モード: ランダム出題
    // ─────────────────────────────────────────────

    public Word getRandomWord(User user, Collection<Long> excludedIds) {
        if (excludedIds == null || excludedIds.isEmpty()) {
            return wordRepository.findRandomWordByUserId(user.getId());
        }
        return wordRepository.findRandomWordByUserIdWithExclusion(user.getId(), excludedIds);
    }

    public Word getRandomWordByCategory(User user, Long categoryId, Collection<Long> excludedIds) {
        if (excludedIds == null || excludedIds.isEmpty()) {
            return wordRepository.findRandomWordByUserIdAndCategoryId(user.getId(), categoryId);
        }
        return wordRepository.findRandomWordByUserIdAndCategoryIdWithExclusion(user.getId(), categoryId, excludedIds);
    }

    public long getWordCount(User user) {
        return wordRepository.countByUserId(user.getId());
    }

    public long getWordCountByCategory(User user, Long categoryId) {
        return wordRepository.countByUserIdAndCategoryId(user.getId(), categoryId);
    }

    // ─────────────────────────────────────────────
    // 苦手単語モード: 不正解履歴から出題
    // ─────────────────────────────────────────────

    /**
     * ユーザーの苦手単語リストを返す（全カテゴリ・不正解回数が多い順・最大50件）。
     */
    public List<Word> getWeakWords(User user) {
        return quizHistoryRepository.findWeakWordsByUserId(user.getId(), PageRequest.of(0, 50));
    }

    /**
     * ユーザーの苦手単語リストをカテゴリ絞り込みで返す（不正解回数が多い順・最大50件）。
     */
    public List<Word> getWeakWordsByCategory(User user, Long categoryId) {
        return quizHistoryRepository.findWeakWordsByUserIdAndCategoryId(user.getId(), categoryId,
                PageRequest.of(0, 50));
    }

    /**
     * 苦手単語リストの中から、除外ID以外のものをランダムに1件返す。
     * categoryId が null の場合は全カテゴリ対象。
     *
     * @param user        ログインユーザー
     * @param categoryId  絞り込むカテゴリID（null=全カテゴリ）
     * @param excludedIds すでに出題済みのWordのIDリスト
     * @return ランダムに選ばれた苦手単語（見つからなければ null）
     */
    public Word getRandomWeakWord(User user, Long categoryId, Collection<Long> excludedIds) {
        List<Word> weakWords = (categoryId != null)
                ? getWeakWordsByCategory(user, categoryId)
                : getWeakWords(user);

        if (weakWords.isEmpty()) {
            return null;
        }
        // 出題済みIDを除外してフィルタリング
        List<Word> candidates;
        if (excludedIds == null || excludedIds.isEmpty()) {
            candidates = weakWords;
        } else {
            candidates = weakWords.stream()
                    .filter(w -> !excludedIds.contains(w.getId()))
                    .toList();
        }
        if (candidates.isEmpty()) {
            return null;
        }
        return candidates.get(ThreadLocalRandom.current().nextInt(candidates.size()));
    }

    /**
     * ユーザーの苦手単語の件数を返す（全カテゴリ）。
     */
    public long countWeakWords(User user) {
        return quizHistoryRepository.countWeakWordsByUserId(user.getId());
    }

    /**
     * ユーザーの苦手単語の件数を返す（カテゴリ絞り込みあり）。
     */
    public long countWeakWordsByCategory(User user, Long categoryId) {
        return quizHistoryRepository.countWeakWordsByUserIdAndCategoryId(user.getId(), categoryId);
    }

    // ─────────────────────────────────────────────
    // 学習履歴の保存
    // ─────────────────────────────────────────────

    @Transactional
    public void saveQuizHistory(User user, Word word, boolean isCorrect) {
        QuizHistory history = new QuizHistory();
        history.setUser(user);
        history.setWord(word);
        history.setCorrect(isCorrect);
        quizHistoryRepository.save(history);
    }
}

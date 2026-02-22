package com.vocabulary.vocab_spring.util;

import com.vocabulary.vocab_spring.entity.User;
import com.vocabulary.vocab_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Spring Security の認証情報から現在ログイン中のユーザーを取得するユーティリティクラス。
 * 複数の Controller で同じ処理が重複しないよう、このクラスに共通化している。
 *
 * 使い方:
 * 
 * @Autowired
 *            private SecurityUtils securityUtils;
 *            ...
 *            User user = securityUtils.getCurrentUser();
 */
@Component
public class SecurityUtils {

    private final UserRepository userRepository;

    @Autowired
    public SecurityUtils(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 現在ログイン中のユーザーを返す。
     * ログインしていない場合は RuntimeException をスローする。
     *
     * @return ログイン中の User エンティティ
     * @throws RuntimeException 認証情報からユーザーが見つからない場合
     */
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new RuntimeException("User not found: " + auth.getName()));
    }
}

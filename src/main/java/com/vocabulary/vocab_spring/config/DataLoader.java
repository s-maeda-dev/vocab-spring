package com.vocabulary.vocab_spring.config;

import com.vocabulary.vocab_spring.entity.User;
import com.vocabulary.vocab_spring.entity.Word;
import com.vocabulary.vocab_spring.repository.UserRepository;
import com.vocabulary.vocab_spring.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final WordRepository wordRepository;

    @Autowired
    public DataLoader(UserRepository userRepository, WordRepository wordRepository) {
        this.userRepository = userRepository;
        this.wordRepository = wordRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Optional<User> userOptional = userRepository.findByUsername("saya");
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Check if user has less than 10 words to ensure dummy data is added
            if (wordRepository.findByUser(user).size() < 10) {
                String[][] wordData = {
                        { "Apple", "りんご", "I ate an apple yesterday." },
                        { "Book", "本", "Reading a book is my hobby." },
                        { "Cat", "猫", "The cat is sleeping on the sofa." },
                        { "Dog", "犬", "My dog loves to run in the park." },
                        { "Elephant", "象", "Elephants have long trunks." },
                        { "Flower", "花", "She bought a beautiful flower." },
                        { "Guitar", "ギター", "He plays the guitar well." },
                        { "House", "家", "They live in a big house." },
                        { "Island", "島", "We traveled to a remote island." },
                        { "Juice", "ジュース", "I like orange juice." }
                };

                for (String[] data : wordData) {
                    Word word = new Word();
                    word.setUser(user);
                    word.setTerm(data[0]);
                    word.setDefinition(data[1]);
                    word.setExampleSentence(data[2]);
                    wordRepository.save(word);
                }
                System.out.println("10 temporary words created for 'saya'.");
            }
        }
    }
}

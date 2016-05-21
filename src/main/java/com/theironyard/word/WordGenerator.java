package com.theironyard.word;

import com.theironyard.bean.Pattern;
import com.theironyard.exception.NoMatchingWordsFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@Component
public class WordGenerator {

    private final Dictionary dictionary;
    private final Random random = new Random();

    @Autowired
    public WordGenerator(Dictionary dictionary) {
        this.dictionary = dictionary;

    }

    public String findWord(int length, ArrayList<Pattern> patterns) throws NoMatchingWordsFoundException {
        List<String> words = dictionary.getDictionary();

        List<String> matchingWords = words.stream()
                .filter(word -> word.length() == length)
                .filter(word -> {
                    for(Pattern pattern : patterns){
                        if(!word.substring(pattern.getPosition(), pattern.getPosition() + 1).equals(pattern.getLetter())) return false;
                    }

                    return true;
                })
                .collect(Collectors.toList());

        if(matchingWords.size() > 0) {
            //System.out.println("matchingWords.size() - 1 : " + (matchingWords.size() - 1));
            int rand = random.nextInt(matchingWords.size() - 1);
            return matchingWords.get(rand);
        } else {
            throw new NoMatchingWordsFoundException("No words could be found that matched the specified pattern");
        }
    }

}

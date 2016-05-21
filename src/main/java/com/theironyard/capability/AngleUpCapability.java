package com.theironyard.capability;

import com.theironyard.Puzzle;
import com.theironyard.bean.Configuration;
import com.theironyard.bean.Pattern;
import com.theironyard.bean.Word;
import com.theironyard.exception.NoMatchingWordsFoundException;
import com.theironyard.word.WordGenerator;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by doug on 5/19/16.
 */
public class AngleUpCapability implements Capability {

    private WordGenerator wordGenerator;
    private Random random = new Random();

    public AngleUpCapability(WordGenerator wordGenerator) {
        this.wordGenerator = wordGenerator;
    }

    public String getName() {
        return "Angled Up";
    }

    public String getDescription() {
        return "Adds words angled up in the puzzle";
    }

    public String getKeyword(){
        return getClass().getName().replaceAll("\\.", "");
    }

    public void addWord(Puzzle puzzle, Configuration configuration) throws NoMatchingWordsFoundException {
        // decided on a word length
        int wordLength = random.nextInt( (configuration.getMaxLength() - configuration.getMinLength())+1 ) + configuration.getMinLength();

        // find the starting point
        int x1 = random.nextInt(configuration.getWidth() - wordLength);
        int y1 = random.nextInt(configuration.getHeight() - wordLength) + wordLength;

        // create a pattern for this word
        ArrayList<Pattern> patterns = new ArrayList<>();
        int x2 = x1;
        int y2 = y1;
        for(int i = 0 ; i < wordLength ; i++) {
            String letter = puzzle.getPuzzle()[y2][x2];

            if (letter != null) {
                patterns.add(new Pattern(letter, i));
            }

            x2++;
            y2--;
        }

        // find a word matching this pattern
        String textWord = wordGenerator.findWord(wordLength, patterns);

        // create a Word object
        Word word = new Word(textWord, x1, y1, x2-1, y2-1);

        // add the word's letters into the puzzle
        x2 = x1;
        y2 = y1;
        for(int i = 0 ; i < wordLength ; i++) {
            String letter = textWord.substring(i, i+1);
            puzzle.getPuzzle()[y2][x2] = letter;

            x2++;
            y2--;
        }

        // record the word into the puzzle
        puzzle.getWords().add(word);

    }

    public String test(){
        return getKeyword();
    }

}

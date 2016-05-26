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
public class AngleBackwardsUpCapability implements Capability {

    private WordGenerator wordGenerator;

    public AngleBackwardsUpCapability(WordGenerator wordGenerator) {
        this.wordGenerator = wordGenerator;
    }

    public String getName() {
        return "Angled Up and Backwards";
    }

    public String getDescription() {
        return "Adds words angled up and backwards in the puzzle";
    }

    public String getKeyword(){
        return getClass().getName().replaceAll("\\.", "");
    }

    public void addWord(Puzzle puzzle, Configuration configuration, Random random) throws NoMatchingWordsFoundException {
        // decided on a word length
        int wordLength = random.nextInt( (configuration.getMaxLength() - configuration.getMinLength())+1 ) + configuration.getMinLength();

        // find the starting point
        int x1 = random.nextInt(configuration.getWidth() - (wordLength - 1)) + (wordLength - 1);
        int y1 = random.nextInt(configuration.getHeight() - (wordLength - 1)) + (wordLength - 1);

        // create a pattern for this word
        ArrayList<Pattern> patterns = new ArrayList<>();
        int x2 = x1;
        int y2 = y1;
        for(int i = 0 ; i < wordLength ; i++) {
            String letter = puzzle.getPuzzle()[y2][x2];

            if (letter != null) {
                patterns.add(new Pattern(letter, i));
            }

            x2--;
            y2--;
        }

        // find a word matching this pattern
        String textWord = null;
        //while(textWord == null) {
        textWord = wordGenerator.findWord(wordLength, patterns, random);
        //  if(puzzle.containsWord(textWord)) textWord = null;
        //}

        // create a Word object
        Word word = new Word(textWord, x1, y1, x2-1, y2-1);

        // add the word's letters into the puzzle
        x2 = x1;
        y2 = y1;
        for(int i = 0 ; i < wordLength ; i++) {
            String letter = textWord.substring(i, i+1);
            puzzle.getPuzzle()[y2][x2] = letter;

            x2--;
            y2--;
        }

        // record the word into the puzzle
        puzzle.getWords().add(word);

    }

    public String test(){
        return getKeyword();
    }

}

package com.theironyard.capability;

import com.theironyard.*;
import com.theironyard.bean.Configuration;
import com.theironyard.exception.NoMatchingWordsFoundException;

import java.util.Random;

/**
 * Created by doug on 5/19/16.
 */
public interface Capability {
    String getName();
    String getDescription();
    String getKeyword();

    void addWord(Puzzle puzzle, Configuration configuration, Random random) throws NoMatchingWordsFoundException;

    //Location getRandomLocation(int wordLength, int puzzleWidth, int puzzleHeight);
    //ArrayList<Pattern> getPatterns(Point start, int length, Puzzle puzzle);
}

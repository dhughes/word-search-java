package com.theironyard.word;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by doug on 5/19/16.
 */
@Component
public class Dictionary {

    private List<String> allWords;

    @Autowired
    public Dictionary(@Value("${app.wordFile}") String wordFile) throws FileNotFoundException {
        File file = new File(wordFile);
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter("\\Z");

        String[] allWords = scanner.next().toLowerCase().split("\n");
        this.allWords = Arrays.asList(allWords);
    }

    public List<String> getDictionary() {
        return this.allWords;
    }

}

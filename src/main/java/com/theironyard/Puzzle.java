package com.theironyard;

import com.theironyard.bean.Configuration;
import com.theironyard.bean.Word;
import com.theironyard.capability.Capability;
import com.theironyard.capability.CapabilityService;
import com.theironyard.word.WordGenerator;
import com.theironyard.exception.CouldNotGeneratePuzzleException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Puzzle {

    private String[][] puzzle;
    private ArrayList<Word> words = new ArrayList<>();
    private Configuration configuration;
    private List<Capability> capabilities;
    private Random random;

    public Puzzle(Configuration configuration, WordGenerator wordGenerator) throws CouldNotGeneratePuzzleException {
        this.puzzle = new String[configuration.getHeight()][configuration.getWidth()];
        this.configuration = configuration;
        this.capabilities = new CapabilityService(wordGenerator).getCapabilities(configuration.getCapabilities());
        this.random = new Random(this.configuration.getSeed());

        generatePuzzle();
    }

    private void generatePuzzle() throws CouldNotGeneratePuzzleException {
        int iterations = 0;
        long startTick = System.currentTimeMillis();

        while(words.size() < configuration.getWords()){
            // pick a random capability and use it to add a word
            try{
                iterations++;
                getRandomCapability().addWord(this, configuration);
            } catch (Exception e){
                // do nothing
            }

            if(System.currentTimeMillis() - startTick > 10000) {
                throw new CouldNotGeneratePuzzleException(String.format("Could not generate puzzle as configured after %s tries.", iterations));
            }
        }

        // fill in the blanks with random letters
        for(int y = 0 ; y < configuration.getHeight() ; y++){
            for(int x = 0 ; x < configuration.getWidth() ; x++){
                if(puzzle[y][x] == null){
                    puzzle[y][x] = String.valueOf((char) (random.nextInt(26) + 97));
                }
            }
        }
    }

    private Capability getRandomCapability(){
        return capabilities.get(random.nextInt(capabilities.size()));
    }

    public String[][] getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(String[][] puzzle) {
        this.puzzle = puzzle;
    }

    public ArrayList<Word> getWords() {
        return words;
    }

    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();

        for(String[] row : puzzle){
            for(String letter : row){
                stringBuilder.append(" ").append(letter != null ? letter : "_").append(" ");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

}

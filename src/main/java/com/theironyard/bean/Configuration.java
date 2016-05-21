package com.theironyard.bean;

import com.sun.istack.internal.Nullable;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents the configuration for a crossword puzzle game.
 */
@ScriptAssert(lang="javascript", script="_this.minLength <= _this.maxLength", message = "The minimum word length must be less than or equal to the maximum word length")
public class Configuration {

    /**
     * This is how many letters wide the puzzle is
     */
    @NotNull
    @Range(min=10, max=100, message = "Width must be from 10 to 100")
    private Integer width;

    /**
     * This is how many letters tall the puzzle is
     */
    @NotNull
    @Range(min=10, max=100, message = "Height must be from 10 to 100")
    private Integer height;

    /**
     * This is the number of words in the puzzle
     */
    @NotNull
    @Range(min=1, max=1000, message = "Number of words must be from 1 to 1000")
    private Integer words;

    @NotNull
    @Range(min=3, max=10, message = "The minimum length of words must be from 3 to 10")
    private Integer minLength;

    @NotNull
    @Range(min=3, max=20, message = "The maximum length of words must be from 3 to 20")
    private Integer maxLength;

    @NotNull
    @NotEmpty(message = "At least one capability must be selected.")
    private ArrayList<String> capabilities = new ArrayList<>();

    private Long seed = null;

    public Configuration(){}

    public Configuration(Integer width, Integer height, Integer words, Integer minLength, Integer maxLength, ArrayList<String> capabilities, Long seed) {
        this.width = width;
        this.height = height;
        this.words = words;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.capabilities = capabilities;
        this.seed = seed;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWords() {
        return words;
    }

    public void setWords(Integer words) {
        this.words = words;
    }

    public Integer getMinLength() {
        return minLength;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public ArrayList<String> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(ArrayList<String> capabilities) {
        this.capabilities = capabilities;
    }

    public Long getSeed() {
        return seed;
    }

    public void setSeed(Long seed) {
        this.seed = seed;
    }
}

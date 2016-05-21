package com.theironyard.bean;


public class Pattern {

    private String letter;
    private int position;

    public Pattern(String letter, int position) {
        this.letter = letter;
        this.position = position;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}

package com.example.galgespil_aflevering;

public class ResultObject implements Comparable {

    String word;
    String time;
    String score;

    public ResultObject(String word, String time, String score) {
        this.word = word;
        this.time = time;
        this.score = score;
    }

    public String getWord() {
        return word;
    }


    public String getTime() {
        return time;
    }


    public String getScore() {
        return score;
    }

    @Override
    public int compareTo(Object compareTo) {
        int a = Integer.parseInt(score);
        int b = Integer.parseInt(((ResultObject)compareTo).score);

        return b-a;
    }
}

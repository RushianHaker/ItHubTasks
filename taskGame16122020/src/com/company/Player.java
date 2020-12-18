package com.company;

public class Player {
    private final Game gameInstance = Game.getInstance();

    private int score;
    private String playerName;


    public Player(String playerName) {
        this.playerName = playerName;
    }

    public void updatePoints(int count) {
        this.score += count;
    }

    public AnswerStatus submitPhrase(String text) {
        return gameInstance.updateSentence(this, text);
    }

    public int getScore() {
        return this.score;
    }

    public String getName() {
        return this.playerName;
    }

    @Override
    public String toString() {
        return this.getName() + ": " + this.getScore();
    }
}

package org.guessthenumber.dto;

import java.time.LocalDateTime;

public class Round {

    private int roundId;

    private String guess;

    private LocalDateTime guessTime;

    private String roundResult;

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public LocalDateTime getGuessTime() {
        return guessTime;
    }

    public void setGuessTime(LocalDateTime guessTime) {
        this.guessTime = guessTime;
    }

    public String getRoundResult() {
        return roundResult;
    }

    public void setRoundResult(String roundResult) {
        this.roundResult = roundResult;
    }
}

package org.guessthenumber.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class Round {

    private int roundId;

    private String guess;

    private LocalDateTime guessTime;

    private String roundResult;

    private int gameId;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

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

    @Override
    public String toString() {
        return "Round{" +
                "roundId=" + roundId +
                ", guess='" + guess + '\'' +
                ", guessTime=" + guessTime +
                ", roundResult='" + roundResult + '\'' +
                ", gameId=" + gameId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Round round = (Round) o;
        return roundId == round.roundId && gameId == round.gameId && guess.equals(round.guess) && guessTime.equals(round.guessTime) && roundResult.equals(round.roundResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roundId, guess, guessTime, roundResult, gameId);
    }
}

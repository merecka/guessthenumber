package org.guessthenumber.dto;

import java.util.Objects;

public class Game {

    private int gameId;

    private String gameAnswer;

    private String gameStatus;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGameAnswer() {
        return gameAnswer;
    }

    public void setGameAnswer(String gameAnswer) {
        this.gameAnswer = gameAnswer;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameId=" + gameId +
                ", gameAnswer='" + gameAnswer + '\'' +
                ", gameStatus='" + gameStatus + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return gameId == game.gameId && gameAnswer.equals(game.gameAnswer) && gameStatus.equals(game.gameStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, gameAnswer, gameStatus);
    }
}

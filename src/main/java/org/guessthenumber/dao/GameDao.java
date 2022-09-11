package org.guessthenumber.dao;

import org.guessthenumber.dto.Game;

import java.util.List;

public interface GameDao {
    Game addGame(Game game);

    List<Game> getAllGames();

    Game findGameById(int id);

    // true if item exists and is updated
    boolean updateGame(Game game);

    public void deleteGameById(int id);
}

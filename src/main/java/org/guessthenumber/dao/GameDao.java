package org.guessthenumber.dao;

import org.guessthenumber.dto.Game;

import java.util.List;

public interface GameDao {
    Game add(Game game);

    List<Game> getAllGames();

    Game findGameById(int id);

    // true if item exists and is updated
    boolean update(Game game);
}

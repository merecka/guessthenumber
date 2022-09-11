package org.guessthenumber.dao;

import org.guessthenumber.dto.Game;
import org.guessthenumber.dto.Round;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GameInDatabaseDaoTest {

    @Autowired
    private GameDao gameDao;

    @Autowired
    private RoundDao roundDao;

    @BeforeEach
    void setUp() {
        List<Round> rounds = roundDao.getAllRounds();
        for(Round round : rounds) {
            roundDao.deleteRoundById(round.getRoundId());
        }

        List<Game> games = gameDao.getAllGames();
        for(Game game : games) {
            gameDao.deleteGameById(game.getGameId());
        }
    }

    @org.junit.jupiter.api.Test
    void testAddGame() {
        Game game = new Game();
        game.setGameAnswer("1234");
        game.setGameStatus("In Progress");
        gameDao.addGame(game);

        Game gameFromDao = gameDao.findGameById(game.getGameId());

        assertEquals(game, gameFromDao);
    }

    @org.junit.jupiter.api.Test
    void testGetAllGames() {
        Game game1 = new Game();
        game1.setGameAnswer("1234");
        game1.setGameStatus("In Progress");
        gameDao.addGame(game1);

        Game game2 = new Game();
        game2.setGameAnswer("2451");
        game2.setGameStatus("In Progress");
        gameDao.addGame(game2);

        Game game3 = new Game();
        game3.setGameAnswer("5629");
        game3.setGameStatus("In Progress");
        gameDao.addGame(game3);

        List<Game> allGames = gameDao.getAllGames();

        assertEquals(3, allGames.size());
    }

    @org.junit.jupiter.api.Test
    void findGameById() {
    }

    @org.junit.jupiter.api.Test
    void testUpdateGame() {
        Game game = new Game();
        game.setGameAnswer("1234");
        game.setGameStatus("In Progress");
        gameDao.addGame(game);

        Game gameFromDao = gameDao.findGameById(game.getGameId());

        assertEquals(game, gameFromDao);

        game.setGameStatus("Finished");
        game.setGameAnswer("1234");
        gameDao.updateGame(game);

        assertNotEquals(game, gameFromDao);

        gameFromDao = gameDao.findGameById(game.getGameId());

        assertEquals(game, gameFromDao);
    }
}
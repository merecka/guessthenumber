package org.guessthenumber.dao;

import org.guessthenumber.dto.Game;
import org.guessthenumber.dto.Round;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoundInDatabaseDaoTest {

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
    void testAddRound() {
        Game game = new Game();
        game.setGameAnswer("1234");
        game.setGameStatus("In Progress");
        gameDao.addGame(game);

        Game gameFromDao = gameDao.findGameById(game.getGameId());

        Round round = new Round();
        round.setGuess("5896");
        round.setRoundResult("e0:p0");
        round.setGameId(gameFromDao.getGameId());
        roundDao.addRound(round);

        Round roundFromDao = roundDao.findRoundById(round.getRoundId());

        assertEquals(round, roundFromDao);

    }

    @org.junit.jupiter.api.Test
    void testGetAllRounds() {
        Game game = new Game();
        game.setGameAnswer("5631");
        game.setGameStatus("In Progress");
        gameDao.addGame(game);

        Game gameFromDao = gameDao.findGameById(game.getGameId());

        Round round1 = new Round();
        round1.setGuess("5896");
        round1.setRoundResult("e0:p0");
        round1.setGameId(gameFromDao.getGameId());
        roundDao.addRound(round1);

        Round round2 = new Round();
        round2.setGuess("6321");
        round2.setRoundResult("e0:p3");
        round2.setGameId(gameFromDao.getGameId());
        roundDao.addRound(round2);

        Round round3 = new Round();
        round3.setGuess("7845");
        round3.setRoundResult("e0:p1");
        round3.setGameId(gameFromDao.getGameId());
        roundDao.addRound(round3);

        List<Round> allRounds = roundDao.getAllRounds();

        assertEquals(3, allRounds.size());
    }

    @org.junit.jupiter.api.Test
    void findRoundById() {
    }

    @org.junit.jupiter.api.Test
    void testFindRoundsByGameId() {
        Game game = new Game();
        game.setGameAnswer("4972");
        game.setGameStatus("In Progress");
        gameDao.addGame(game);

        Game gameFromDao = gameDao.findGameById(game.getGameId());

        Round round1 = new Round();
        round1.setGuess("5896");
        round1.setRoundResult("e0:p1");
        round1.setGameId(gameFromDao.getGameId());
        roundDao.addRound(round1);

        Round round2 = new Round();
        round2.setGuess("6321");
        round2.setRoundResult("e0:p1");
        round2.setGameId(gameFromDao.getGameId());
        roundDao.addRound(round2);

        Round round3 = new Round();
        round3.setGuess("7845");
        round3.setRoundResult("e0:p0");
        round3.setGameId(gameFromDao.getGameId());
        roundDao.addRound(round3);

        List<Round> rounds = roundDao.findRoundsByGameId(game.getGameId());

        assertEquals(3, rounds.size());
    }
}
package org.guessthenumber.controller;

import org.guessthenumber.dao.GameDao;
import org.guessthenumber.dao.RoundDao;
import org.guessthenumber.dto.Game;
import org.guessthenumber.dto.Round;
import org.guessthenumber.service.GameServiceLayer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class GameController {

    private final GameDao gameDao;

    private final RoundDao roundDao;

    private final GameServiceLayer gameService;

    public GameController(GameDao gameDao, GameServiceLayer gameService, RoundDao roundDao) {
        this.gameService = gameService;
        this.gameDao = gameDao;
        this.roundDao = roundDao;
    }

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public Game createNewGame() {
        Game newGame = gameService.generateNewGame();
        return gameDao.addGame(newGame);
    }

    @GetMapping("/game")
    public List<Game> allGames() {
        return gameDao.getAllGames();
    }

    @GetMapping("game/{id}")
    public ResponseEntity<Game> findGameById(@PathVariable int id) {
        Game result = gameDao.findGameById(id);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/guess")
    @ResponseStatus(HttpStatus.CREATED)
    // Request is in the form of { gameId: "1", guess: "1234" }
    public Round createNewGuess(@RequestBody Round round) {
        Game retrievedGame = gameDao.findGameById(round.getGameId());
        System.out.println("retrievedGame is: " + retrievedGame.toString());
        gameService.generateNewGuess(retrievedGame, round);
        gameDao.updateGame(retrievedGame);
        Round addedRound = roundDao.addRound(round);
        System.out.println("addedRound is: " + addedRound.toString());
        return roundDao.findRoundById(addedRound.getRoundId());
    }

    @GetMapping("round/{id}")
    public ResponseEntity<Round> findRoundById(@PathVariable int id) {
        Round result = roundDao.findRoundById(id);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }
}

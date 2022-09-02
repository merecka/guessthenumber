package org.guessthenumber.controller;

import org.guessthenumber.dao.GameDao;
import org.guessthenumber.dto.Game;
import org.guessthenumber.service.GameServiceLayer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class GameController {

    private final GameDao gameDao;

    private final GameServiceLayer gameService;

    public GameController(GameDao gameDao, GameServiceLayer gameService) {
        this.gameService = gameService;
        this.gameDao = gameDao;
    }

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public Game createNewGame() {
        Game newGame = gameService.generateNewGameAnswer();
        return gameDao.addGame(newGame);
    }

    @GetMapping("/game")
    public List<Game> allGames() {
        return gameDao.getAllGames();
    }

    @GetMapping("game/{id}")
    public ResponseEntity<Game> findById(@PathVariable int id) {
        Game result = gameDao.findGameById(id);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }
}

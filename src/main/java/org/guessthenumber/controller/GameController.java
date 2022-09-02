package org.guessthenumber.controller;

import org.guessthenumber.dao.GameDao;
import org.guessthenumber.dto.Game;
import org.guessthenumber.service.GameServiceLayer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class GameController {

//    private final GameDao gameDao;

    private final GameServiceLayer gameService;

    public GameController(GameServiceLayer gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public String createGame() {
        String newGameAnswer = gameService.generateNewGameAnswer();
        return newGameAnswer;
//        return gameDao.add(game);
    }
}

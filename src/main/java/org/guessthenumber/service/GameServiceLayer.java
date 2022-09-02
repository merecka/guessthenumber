package org.guessthenumber.service;

import org.guessthenumber.dto.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Repository
public class GameServiceLayer {

    @Autowired
    public GameServiceLayer() {
    }

    public Game generateNewGameAnswer() {
        List<String> newGameStringArray = new ArrayList<>();
        Random random = new Random();
        List<Integer> answerArray = new ArrayList<>();
        boolean keepGoing = false;
        for (int i = 0; i < 4; i++) {
            do {
                // generates random numbers from 0-9
                int randomNum = random.nextInt(10);
                if (answerArray.size() == 0) {
                    answerArray.add(randomNum);
                } else if (randomNum == answerArray.get(i-1)) {
                    keepGoing = true;
                } else {
                    answerArray.add(randomNum);
                    keepGoing = false;
                }
            } while (keepGoing);
        }

        StringBuffer answerStringBuffer = new StringBuffer();
        for (int number : answerArray)
        {
            answerStringBuffer.append(Integer.toString(number));
        }

        Game newGame = new Game();
        newGame.setGameAnswer(answerStringBuffer.toString());
        newGame.setGameStatus("In Progress");
        return newGame;
    }
}

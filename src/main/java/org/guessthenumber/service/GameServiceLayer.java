package org.guessthenumber.service;

import org.guessthenumber.dto.Game;
import org.guessthenumber.dto.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Repository
public class GameServiceLayer {

    @Autowired
    public GameServiceLayer() {
    }

    public Game generateNewGame() {
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
                } else if (answerArray.contains(randomNum)) {
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

    public void generateNewGuess(Game game, Round round) {
        int exactMatches = 0;
        int partialMatches = 0;

        // Checks to see if there is an exact match between Guess and Answer
        if (game.getGameAnswer().equals(round.getGuess())) {
            exactMatches = 4;
            String roundResult = "e:" + Integer.toString(exactMatches) + ":e:" + Integer.toString(partialMatches);
            game.setGameStatus("Finished");
            round.setRoundResult(roundResult);
            return;
        }

        // Check to see if there is a partial match
        ArrayList<Character> guessArray = new ArrayList<Character>();
        for (char c : round.getGuess().toCharArray()) {
            guessArray.add(c);
        }

        ArrayList<Character> answerArray = new ArrayList<Character>();
        for (char c : game.getGameAnswer().toCharArray()) {
            answerArray.add(c);
        }

//        for (int i = 0; i < guessArray.size(); i++) {
//            if (answerArray.contains(guessArray.get(i))) {
//                partialMatches++;
//            }
//        }

        for (int i = 0; i < guessArray.size(); i++) {
            if (answerArray.get(i).equals(guessArray.get(i))) {
                exactMatches++;
            }
            else if (answerArray.contains(guessArray.get(i))) {
                partialMatches++;
            }
        }

        String roundResult = "e:" + Integer.toString(exactMatches) + ":p:" + Integer.toString(partialMatches);
        round.setRoundResult(roundResult);
    }
}

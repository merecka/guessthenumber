package org.guessthenumber.dao;

import org.guessthenumber.dto.Game;
import org.guessthenumber.dto.Round;

import java.util.List;

public interface RoundDao {
    Round addRound(Round round);

    List<Round> getAllRounds();
//
//    Round findRoundById(int id);

}

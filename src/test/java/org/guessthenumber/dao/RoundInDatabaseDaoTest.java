package org.guessthenumber.dao;

import org.guessthenumber.dto.Round;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoundInDatabaseDaoTest {

    @Autowired
    private GameDao gameDao;

    @Autowired
    private RoundDao roundDao;

    @BeforeEach
    void setUp() {
        List<Round> rounds = roundDao.getAllRounds();
        for(Round round : rounds) {
            roundDao.rou(room.getId());
        }
    }

    @org.junit.jupiter.api.Test
    void addRound() {
    }

    @org.junit.jupiter.api.Test
    void getAllRounds() {
    }

    @org.junit.jupiter.api.Test
    void findRoundById() {
    }

    @org.junit.jupiter.api.Test
    void findRoundsByGameId() {
    }
}
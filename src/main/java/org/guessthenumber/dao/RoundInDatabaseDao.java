package org.guessthenumber.dao;

import org.guessthenumber.dto.Round;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;

@Repository
public class RoundInDatabaseDao implements RoundDao {
    private final JdbcTemplate jdbcTemplate;

    public RoundInDatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Round addRound(Round round) {

        final String sql = "INSERT INTO round(guess, roundResult, gameId) VALUES(?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, round.getGuess());
            statement.setString(2, round.getRoundResult());
            statement.setInt(3, round.getGameId());
            return statement;

        }, keyHolder);

        int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        round.setRoundId(newId);
        return round;
    }

    @Override
    public List<Round> getAllRounds() {
        final String sql = "SELECT * FROM round;";
        return jdbcTemplate.query(sql, new RoundInDatabaseDao.RoundMapper());
    }

    @Override
    public Round findRoundById(int roundId) {
        final String sql = "SELECT * from round WHERE roundId = ?; ";
        return jdbcTemplate.queryForObject(sql, new RoundInDatabaseDao.RoundMapper(), roundId);
    }

    @Override
    public List<Round> findRoundsByGameId(int gameId) {
        final String sql = "SELECT * from round WHERE gameId = ? ORDER BY guessTime ASC; ";
        return jdbcTemplate.query(sql, new RoundInDatabaseDao.RoundMapper(), gameId);
    }

    @Override
    @Transactional
    public void deleteRoundById(int id) {
        final String DELETE_ROUND = "DELETE FROM round "
                + "WHERE roundId = ?";
        jdbcTemplate.update(DELETE_ROUND, id);
    }

    private static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setRoundId((rs.getInt("roundId")));
            round.setGuess(((rs.getString("guess"))));
            round.setGuessTime(rs.getTimestamp(3).toLocalDateTime());
            round.setRoundResult(rs.getString("roundResult"));
            round.setGameId(rs.getInt("gameId"));
            return round;
        }
    }
}

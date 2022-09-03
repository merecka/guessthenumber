package org.guessthenumber.dao;

import org.guessthenumber.dto.Game;
import org.guessthenumber.dto.Round;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class RoundInDatabaseDao implements RoundDao {
    private final JdbcTemplate jdbcTemplate;

    public RoundInDatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Game addGame(Game game) {

        final String sql = "INSERT INTO game(gameAnswer, gameStatus) VALUES(?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, game.getGameAnswer());
            return statement;

        }, keyHolder);

        game.setGameId(keyHolder.getKey().intValue());

        return game;
    }

//    @Override
//    public List<Game> getAllGames() {
//        final String sql = "SELECT gameId, gameAnswer, gameStatus FROM game;";
//        return jdbcTemplate.query(sql, new GameInDatabaseDao.GameMapper());
//    }
//
//    @Override
//    public Game findGameById(int id) {
//
//        final String sql = "SELECT gameId, gameAnswer, gameStatus "
//                + "FROM game WHERE gameId = ?;";
//
//        return jdbcTemplate.queryForObject(sql, new GameInDatabaseDao.GameMapper(), id);
//    }

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

package org.guessthenumber.dao;

import org.guessthenumber.dto.Game;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class GameInDatabaseDao implements GameDao {
    private final JdbcTemplate jdbcTemplate;

    public GameInDatabaseDao(JdbcTemplate jdbcTemplate) {
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
            statement.setString(2, game.getGameStatus());
            return statement;

        }, keyHolder);

        game.setGameId(keyHolder.getKey().intValue());

        return game;
    }

    @Override
    public List<Game> getAllGames() {
        final String sql = "SELECT gameId, gameAnswer, gameStatus FROM game;";
        return jdbcTemplate.query(sql, new GameMapper());
    }

    @Override
    public Game findGameById(int id) {

        final String sql = "SELECT gameId, gameAnswer, gameStatus "
                + "FROM game WHERE gameId = ?;";

        return jdbcTemplate.queryForObject(sql, new GameMapper(), id);
    }

    private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setGameId(rs.getInt("gameId"));
            game.setGameAnswer(rs.getString("gameAnswer"));
            game.setGameStatus(rs.getString("gameStatus"));
            if (game.getGameStatus().equals("In Progress")) {
                game.setGameAnswer("");
            }
            return game;
        }
    }
}

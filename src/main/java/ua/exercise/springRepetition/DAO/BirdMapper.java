package ua.exercise.springRepetition.DAO;

import ua.exercise.springRepetition.models.Bird;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BirdMapper implements org.springframework.jdbc.core.RowMapper <Bird> {
    @Override
    public Bird mapRow(ResultSet rs, int rowNum) throws SQLException {
        Bird bird = new Bird();

        bird.setId(rs.getInt("id"));
        bird.setName(rs.getString("name"));
        bird.setSpecies(rs.getString("species"));
        bird.setAge(rs.getInt("age"));

        return bird;
    }
}

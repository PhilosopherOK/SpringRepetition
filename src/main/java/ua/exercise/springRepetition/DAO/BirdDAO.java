package ua.exercise.springRepetition.DAO;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.exercise.springRepetition.models.Bird;

import java.sql.*;
import java.util.List;


/*CREATE TABLE Bird(
    id int,
    name  varchar,
    species varchar,
    age int
    );

INSERT INTO bird(id, name, species, age) values (1, 'Dodo', 'Crow', 21);
INSERT INTO bird(id, name, species, age) values (2, 'Zida', 'Crow', 20);
INSERT INTO bird(id, name, species, age) values (3, 'Gachi', 'Sparrow', 3);
INSERT INTO bird(id, name, species, age) values (4, 'Pixi', 'Freya', 167);
INSERT INTO bird(id, name, species, age) values (5, 'Frown', 'Carbuncl', 50)
 */

@Component
public class BirdDAO {
    //jdbc:postgresql://localhost:5432/jdbc_API_db
    private final JdbcTemplate jdbcTemplate;

    public BirdDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Bird> index() {
        return jdbcTemplate.query("SELECT * FROM Bird", new BirdMapper());

    }

    public Bird show(int id) {
        return jdbcTemplate.query("SELECT * FROM Bird WHERE id=?", new Object[]{id}, new BirdMapper())
                .stream().findAny().orElse(null);
    }

    public void save(Bird bird) {
        jdbcTemplate.update("INSERT INTO Bird(name, species, age) VALUES(?, ?, ?)",
                bird.getName(), bird.getSpecies(), bird.getAge());
    }

    public void update(int id, Bird newBird) {
        jdbcTemplate.update("UPDATE Bird SET name=?, species=?, age=? WHERE id=?",
                newBird.getName(), newBird.getSpecies(), newBird.getAge(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Bird WHERE id=?", id);
    }
}

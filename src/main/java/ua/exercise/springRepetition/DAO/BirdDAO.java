package ua.exercise.springRepetition.DAO;

import org.springframework.stereotype.Component;
import ua.exercise.springRepetition.models.Bird;

import java.sql.*;
import java.util.ArrayList;
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
    private static int NumberOfBird = 6;
    private static Connection connection;
    private static final String URL = "jdbc:postgresql://localhost:5432/jdbc_API_db";
    private static final String USERNAME = "postgres";
    private static final String PASWORD = "619916";


    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Bird> index() {
        List<Bird> birds = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Bird";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Bird bird = new Bird();

                bird.setId(resultSet.getInt("id"));
                bird.setName(resultSet.getString("name"));
                bird.setSpecies(resultSet.getString("species"));
                bird.setAge(resultSet.getInt("age"));

                birds.add(bird);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return birds;
    }

    public Bird show(int id) {
        Bird bird = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Bird WHERE id=?");

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            bird = new Bird();

            bird.setId(resultSet.getInt("id"));
            bird.setName(resultSet.getString("name"));
            bird.setSpecies(resultSet.getString("species"));
            bird.setAge(resultSet.getInt("age"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return bird;
    }

    public void save(Bird bird) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO Bird(id, name, species, age) VALUES(?, ?, ?, ?)");
            //INSERT INTO bird(id, name, species, age) values (5, 'Frown', 'Carbuncl', 50)
            preparedStatement.setInt(1, NumberOfBird++);
            preparedStatement.setString(2, bird.getName());
            preparedStatement.setString(3, bird.getSpecies());
            preparedStatement.setInt(4, bird.getAge());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(int id, Bird newBird) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE Bird SET name=?, species=?, age=? WHERE id=?");

            preparedStatement.setString(1, newBird.getName());
            preparedStatement.setString(2, newBird.getSpecies());
            preparedStatement.setInt(3, newBird.getAge());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM Bird WHERE id=?");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

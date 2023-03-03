package ua.exercise.springRepetition.DAO;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.exercise.springRepetition.models.Person;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return (Person) jdbcTemplate.query("SELECT * FROM Person WHERE person_id=?", new Object[]{id},
                        new BeanPropertyRowMapper(Person.class)).stream().findAny().orElse(null);
    }
    public Person show(String name){
        return (Person) jdbcTemplate.query("SELECT * FROM Person WHERE name=?", new Object[]{name},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name, yearOfBirthday) VALUES (?, ?)",
                person.getName(), person.getYearOfBirthday());
    }

    public void update(int id, Person newPerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, yearOfBirthday=? WHERE person_id=?",
                newPerson.getName(), newPerson.getYearOfBirthday(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", id);
    }
}

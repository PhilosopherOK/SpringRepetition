package ua.exercise.springRepetition.mapper;

import org.springframework.jdbc.core.RowMapper;
import ua.exercise.springRepetition.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapperForBook implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();

        person.setPerson_id(rs.getInt("person_id"));
        person.setName(rs.getString("name"));
        person.setYearOfBirthday(rs.getInt("yearOfBirthday"));

        return person;
    }
}

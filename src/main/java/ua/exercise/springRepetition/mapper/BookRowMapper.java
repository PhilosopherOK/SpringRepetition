package ua.exercise.springRepetition.mapper;

import org.springframework.jdbc.core.RowMapper;
import ua.exercise.springRepetition.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setBook_id(rs.getInt("book_id"));
        book.setPerson_id(rs.getInt("person_id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setYearOfWriting(rs.getInt("yearOfWriting"));
        return book;
    }
}

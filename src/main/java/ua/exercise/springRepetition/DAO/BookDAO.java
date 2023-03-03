package ua.exercise.springRepetition.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.exercise.springRepetition.models.Book;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id){
        return (Book) jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?", new Object[]{id},
                new BeanPropertyRowMapper(Book.class)).stream().findAny().orElse(null);
    }
    public void save(Book newBook){
        jdbcTemplate.update("INSERT INTO Book(title, author, yearOfWriting) VALUES (?,?,?)",
                newBook.getTitle(), newBook.getAuthor(), newBook.getYearOfWriting());
    }
    public void update(int id, Book updateBook){
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, yearOfWriting=? WHERE book_id=?",
                updateBook.getTitle(), updateBook.getAuthor(), updateBook.getYearOfWriting(), id);
    }
    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
    }
}

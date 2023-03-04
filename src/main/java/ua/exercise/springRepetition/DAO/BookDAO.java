package ua.exercise.springRepetition.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.exercise.springRepetition.mapper.BookRowMapper;
import ua.exercise.springRepetition.mapper.PersonMapperForBook;
import ua.exercise.springRepetition.models.Book;
import ua.exercise.springRepetition.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM Book", new BookRowMapper());
    }

    public Book show(int id){
        return (Book) jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?", new Object[]{id},
                new BookRowMapper()).stream().findAny().orElse(null);
    }
    public List<Person> showWhoTake(int id){
        Book book = show(id);
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id=?",
                new Object[]{book.getPerson_id()}, new BeanPropertyRowMapper<>(Person.class));
    }
    public void releaseBook(int id){
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE book_id=?", null, id );
    }

    public void addHost(int book_id, int person_id){
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE book_id=?", person_id, book_id);
    }
    public void save(Book newBook){
        jdbcTemplate.update("INSERT INTO Book(person_id,title, author, yearOfWriting) VALUES (?,?,?,?)",null,
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

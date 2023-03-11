package ua.exercise.springRepetition.servises;

import org.springframework.stereotype.Service;
import ua.exercise.springRepetition.models.Book;
import ua.exercise.springRepetition.repositories.BooksRepositories;

import java.util.List;

@Service
public class BooksService {
    private final BooksRepositories booksRepositories;

    public BooksService(BooksRepositories booksRepositories) {
        this.booksRepositories = booksRepositories;
    }
    public List<Book> findAll(){
        return booksRepositories.findAll();
    }
    public Book findOne(int id){
        return booksRepositories.findById(id).orElse(null);
    }
    public void save(Book newBook){
        booksRepositories.save(newBook);
    }
    public void update(int id, Book updateBook){
        updateBook.setBook_id(id);
        booksRepositories.save(updateBook);
    }
    public void delete(int id){
        booksRepositories.deleteById(id);
    }
}

package ua.exercise.springRepetition.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.exercise.springRepetition.models.Book;
import ua.exercise.springRepetition.models.Person;
import ua.exercise.springRepetition.repositories.BooksRepositories;
import ua.exercise.springRepetition.repositories.PeopleRepositories;

import java.util.List;
import java.util.Optional;

@Service
public class BooksService {
    private final BooksRepositories booksRepositories;
    private final PeopleRepositories peopleRepositories;

    @Autowired
    public BooksService(BooksRepositories booksRepositories, PeopleRepositories peopleRepositories) {
        this.booksRepositories = booksRepositories;
        this.peopleRepositories = peopleRepositories;
    }
    public List<Book> findAll(){
        return booksRepositories.findAll();
    }
    public Book findOne(int id){
        return booksRepositories.findById(id).orElse(null);
    }

    public List<Book> findByNameStartingWith(String startingWith){
        return booksRepositories.findByTitleStartingWith(startingWith);
    }
    public void releaseBook(int id){
        Optional<Book> book = booksRepositories.findById(id);
        Optional<Person> person = peopleRepositories.findById(book.get().getHost().getPerson_id());
        if(book.isPresent()){
            book.get().setHost(null);
            person.get().getTakenBooks().remove(book.get());
            booksRepositories.save(book.get());
            peopleRepositories.save(person.get());
        }
    }

    public void addHost(int book_id, Person person){
        Optional<Book> book = booksRepositories.findById(book_id);
        Optional<Person> person1 = peopleRepositories.findById(person.getPerson_id());
        if(book.isPresent()){
            person1.get().setTakenBooks(book.get());
            book.get().setHost(person);
            booksRepositories.save(book.get());
            peopleRepositories.save(person1.get());
        }
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

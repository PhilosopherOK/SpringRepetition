package ua.exercise.springRepetition.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.exercise.springRepetition.models.Book;
import ua.exercise.springRepetition.models.Person;
import ua.exercise.springRepetition.repositories.BooksRepositories;
import ua.exercise.springRepetition.repositories.PeopleRepositories;

import java.util.Date;
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
    public List<Book> findAll(int page, int books_per_page, boolean sort_by_year){
        if(page != 0 && books_per_page != 0 && sort_by_year){
            return booksRepositories.findAll
                    (PageRequest.of(page, books_per_page, Sort.by("yearOfWriting"))).getContent();
        }else if(page != 0 && books_per_page != 0){
            return booksRepositories.findAll
                    (PageRequest.of(page, books_per_page)).getContent();
        }else if(sort_by_year){
            return booksRepositories.findAll(Sort.by("yearOfWriting"));
        }else{
            return booksRepositories.findAll();
        }
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
            book.get().setDateTaken(null);
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
            book.get().setDateTaken(new Date());
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

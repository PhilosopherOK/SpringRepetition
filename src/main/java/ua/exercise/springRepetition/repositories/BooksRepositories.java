package ua.exercise.springRepetition.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.exercise.springRepetition.models.Book;

@Repository
public interface BooksRepositories extends JpaRepository<Book, Integer> {
}

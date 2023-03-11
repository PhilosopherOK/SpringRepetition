package ua.exercise.springRepetition.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.exercise.springRepetition.models.Person;

@Repository
public interface PeopleRepositories extends JpaRepository<Person, Integer> {
}

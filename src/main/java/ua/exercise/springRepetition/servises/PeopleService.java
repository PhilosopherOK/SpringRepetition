package ua.exercise.springRepetition.servises;

import org.springframework.stereotype.Service;
import ua.exercise.springRepetition.models.Person;
import ua.exercise.springRepetition.repositories.PeopleRepositories;

import java.util.List;
import java.util.Optional;

@Service
public class PeopleService {
    private final PeopleRepositories peopleRepositories;

    public PeopleService(PeopleRepositories peopleRepositories) {
        this.peopleRepositories = peopleRepositories;
    }
    public List<Person> findAll(){
        return peopleRepositories.findAll();
    }
    public Person findOne(int id){
        //Optional<Person> foundPerson = peopleRepositories.findById(id);
        //return foundPerson.orElse(null);
        return peopleRepositories.findById(id).orElse(null);
    }
    public void save(Person person){
        peopleRepositories.save(person);
    }
    public void update(int id, Person person){
        person.setPerson_id(id);
        peopleRepositories.save(person);
    }
    public void delete(int id){
        peopleRepositories.deleteById(id);
    }
}

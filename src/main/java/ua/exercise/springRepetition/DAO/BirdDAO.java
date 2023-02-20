package ua.exercise.springRepetition.DAO;

import org.springframework.stereotype.Component;
import ua.exercise.springRepetition.models.Bird;

import java.util.LinkedList;
import java.util.List;

@Component
public class BirdDAO {
    private static int NumberOfBird = 1;
    private List<Bird> aviFauna;

    {
        aviFauna = new LinkedList<>();

        aviFauna.add(new Bird(NumberOfBird++, "Grony", "Crow"));
        aviFauna.add(new Bird(NumberOfBird++, "Zizi", "Tit"));
        aviFauna.add(new Bird(NumberOfBird++, "Fluppi", "Sparrow"));
    }

    public List<Bird> index() {
        return aviFauna;
    }

    public Bird show(int id) {
        return aviFauna.stream().filter(bird -> bird.getId() == id).findAny().orElse(null);
    }

    public void save(Bird bird) {
        bird.setId(NumberOfBird++);
        aviFauna.add(bird);
    }

    public void update(int id, Bird newBird) {
        aviFauna.get(id - 1).setName(newBird.getName());
        aviFauna.get(id - 1).setSpecies(newBird.getSpecies());
    }


}

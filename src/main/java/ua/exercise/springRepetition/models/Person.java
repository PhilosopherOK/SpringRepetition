package ua.exercise.springRepetition.models;



import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/*Create TABLE Person(
    person_id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name varchar NOT NULL UNIQUE,
    yearOfBirthday int CHECK ( yearOfBirthday > 1900 )
)*/
@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int person_id;

    @NotEmpty(message = "name mush be empty")
    @Size(min = 3, max = 40, message = "Name should be more than 3, and dont max than 40")
    @Column(name = "name")
    private String name;

    @NotNull(message = "year mush be not null")
    @Min(value = 1900, message = "must be after 1900")
    @Column(name = "yearOfBirthday")
    private int yearOfBirthday;

    @OneToMany(mappedBy = "host", fetch = FetchType.EAGER)
    private List<Book> takenBooks = new ArrayList<>();


    public Person() {
    }

    public Person(int person_id, String name, int yearOfBirthday) {
        this.person_id = person_id;
        this.name = name;
        this.yearOfBirthday = yearOfBirthday;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBirthday() {
        return yearOfBirthday;
    }

    public void setYearOfBirthday(int yearOfBirthday) {
        this.yearOfBirthday = yearOfBirthday;
    }

    public List<Book> getTakenBooks() {
        return takenBooks;
    }

    public void setTakenBooks(List<Book> takenBooks) {
        this.takenBooks = takenBooks;
    }
    public void setTakenBooks(Book takenBooks) {
        this.takenBooks.add(takenBooks);
    }
}

package ua.exercise.springRepetition.models;



import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Bird {
    private int id;
    @NotEmpty(message = "name mush be empty")
    @Size(min = 3,max = 20, message = "size should be between 3 and 20")
    private String name;
    @NotEmpty(message = "species mush be empty")
    @Size(min = 3,max = 20, message = "size should be between 3 and 20")
    private String species;

    @Min(value = 0, message = "age should be more then 0")
    private int age;

    public Bird() {
    }

    public Bird(int id, String name, String kind, int age) {
        this.id = id;
        this.name = name;
        this.species = kind;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

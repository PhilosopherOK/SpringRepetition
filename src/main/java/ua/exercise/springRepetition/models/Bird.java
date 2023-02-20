package ua.exercise.springRepetition.models;

public class Bird {
    private int id;
    private String name;
    private String species;

    public Bird() {
    }

    public Bird(int id, String name, String kind) {
        this.id = id;
        this.name = name;
        this.species = kind;
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
}

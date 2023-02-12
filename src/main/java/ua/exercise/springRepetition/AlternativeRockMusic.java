package ua.exercise.springRepetition;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class AlternativeRockMusic implements Music {
    @Override
    public List<String> getSong() {
        List<String> songs = new ArrayList<>();
        songs.add("Radioactive1");
        songs.add("Radioactive2");
        songs.add("Radioactive3");
        return songs;
    }
}

package ua.exercise.springRepetition;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class ClassicalMusic implements Music{
    @Override
    public List<String> getSong() {
        List<String> songs = new ArrayList<>();
        songs.add("Times of year1");
        songs.add("Times of year2");
        songs.add("Times of year3");
        return songs;
    }
}

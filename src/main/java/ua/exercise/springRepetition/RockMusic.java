package ua.exercise.springRepetition;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class RockMusic implements Music{
    @Override
    public List<String> getSong() {
        List<String> songs = new ArrayList<>();
        songs.add("Red Hot Chilly Peppers1");
        songs.add("Red Hot Chilly Peppers2");
        songs.add("Red Hot Chilly Peppers3");
        return songs;
    }
}

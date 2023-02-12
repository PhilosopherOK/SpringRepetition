package ua.exercise.springRepetition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;


public class MusicPlayer {
    Random random = new Random();
    List<Music> musicList;
    int volume;
    String nameOfGadget;

    ClassicalMusic classicalMusic;
    AlternativeRockMusic alternativeRockMusic;
    RockMusic rockMusic;



    MusicPlayer(ClassicalMusic classicalMusic,
                 AlternativeRockMusic alternativeRockMusic,
                 RockMusic rockMusic) {
        this.classicalMusic = classicalMusic;
        this.alternativeRockMusic = alternativeRockMusic;
        this.rockMusic = rockMusic;
    }

    void playSong(GenreOfMusic genreOfMusic){
        switch (genreOfMusic){
            case ROCK:
                System.out.println(rockMusic.getSong().get(random.nextInt(3)));
            case CLASSICAL:
                System.out.println(classicalMusic.getSong().get(random.nextInt(3)));
            case ALTERNATIVEROCK:
                System.out.println(alternativeRockMusic.getSong().get(random.nextInt(3)));

        }
    }


    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getNameOfGadget() {
        return nameOfGadget;
    }

    public void setNameOfGadget(String nameOfGadget) {
        this.nameOfGadget = nameOfGadget;
    }
}

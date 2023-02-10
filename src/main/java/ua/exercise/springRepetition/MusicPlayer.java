package ua.exercise.springRepetition;

import java.util.List;

public class MusicPlayer {
    List<Music> musicList;
    int volume;
    String nameOfGadget;

    void playSong(){
        for(Music music:musicList)
        System.out.println(music.getSong());
    }
    public MusicPlayer(List musicList) {
        this.musicList = musicList;
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

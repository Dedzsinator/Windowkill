package controller;

import utils.AudioPlayer;

public class AudioController {
    private AudioPlayer backgroundMusic;

    public AudioController() {
        backgroundMusic = new AudioPlayer("background_music.wav");
        backgroundMusic.playLoop();
    }

    public void playSoundEffect(String soundFile) {
        new AudioPlayer(soundFile).play();
    }
}
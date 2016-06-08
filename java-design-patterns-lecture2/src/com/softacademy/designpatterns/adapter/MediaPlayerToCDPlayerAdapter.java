package com.softacademy.designpatterns.adapter;

public class MediaPlayerToCDPlayerAdapter implements CDPlayer {
    private MediaPlayer mediaPlayer;

    public MediaPlayerToCDPlayerAdapter(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    @Override
    public void play() {
        mediaPlayer.playMusic();
    }

}

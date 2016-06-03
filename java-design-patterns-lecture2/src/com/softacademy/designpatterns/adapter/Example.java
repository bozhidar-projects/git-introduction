package com.softacademy.designpatterns.adapter;

public class Example {

    public static void main(String[] args) {
        CDPlayer player = new SonyPlayer();

        MediaPlayer mediaPlayer = new SamsungPlayer();
        MediaPlayer coolPlayer = new SamsungCoolPlayer();

        MediaPlayerToCDPlayerAdapter adapter = new MediaPlayerToCDPlayerAdapter(
                mediaPlayer);

        MediaPlayerToCDPlayerAdapter adapter2 = new MediaPlayerToCDPlayerAdapter(
                coolPlayer);

        startPlayingMusic(adapter2);
    }

    private static void startPlayingMusic(CDPlayer player) {
        player.play();
    }

}

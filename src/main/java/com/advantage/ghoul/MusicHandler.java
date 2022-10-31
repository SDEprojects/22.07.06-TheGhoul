package com.advantage.ghoul;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.InputStream;

public class MusicHandler {

    private String fileName;
    private Player player;

//    public static void main(String[] args) {
//        MusicHandler music = new MusicHandler();
//        music.playIntroMusic();
//    }

    public MusicHandler(String fileName) {
        this.fileName = fileName;
    }

    public MusicHandler() {

    }

    //-----stops music from playing-----
    public void close() {
        if (player != null)
            player.close();
    }

    public void play() {
        try {
            ClassLoader classLoader = FileReading.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("music/Intro.mp3");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            player = new Player(bufferedInputStream);
        } catch (Exception e) {
            System.out.println("Music File can not find " );
            System.out.println(e);
        }

        new Thread() {
            public void run() {
                try {
                    player.play();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }.start();
    }

    //-----plays music-----
    public void playIntroMusic() {
        MusicHandler introMusic = new MusicHandler("music/Intro.mp3");
        introMusic.play();
//        String input = scanner.nextLine();
//        input = input.toLowerCase();
//
//        if (input.equals("stop music")) {
//            introMusic.play();
//        } else if (input.equals("play music")) {
//            introMusic.play();
//        }
//        else {
//            System.out.println("wrong input");
    }
}






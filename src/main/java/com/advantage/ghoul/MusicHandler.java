package com.advantage.ghoul;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

class MusicHandler {

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

    void play() {
        try {
            FileInputStream inputStream = new FileInputStream(fileName);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            player = new Player(bufferedInputStream);
            //music will not loop
        } catch (Exception e) {
            System.out.println("Problem playing " + fileName);
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
    void playIntroMusic() {
        MusicHandler introMusic = new MusicHandler("resources/music/Intro.mp3");
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






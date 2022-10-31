package com.advantage.ghoul.app;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;
import javax.sound.midi.Soundbank;
import javax.sound.sampled.*;

public class MusicPlayer {

    public void runMusic() {
        try {
            File file = new File("resources/music/Intro.wav");
            Scanner scanner = new Scanner(System.in);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);


            //-----Commands to play, stop, control music volume-----

            String response = scanner.nextLine();
            response = response.toLowerCase();

            if (response.equals("play music")) {
                clip.start();
            }
            if (response.equals("stop music")) {
                clip.stop();
            }
            if (response.equals("reset music")) {
                clip.setMicrosecondPosition(0);
                gainControl.setValue(0);
            }
            if (response.equals("lower volume")) {
                gainControl.setValue(-8.0f);
            }
            if (response.equals("raise volume")) {
                gainControl.setValue(5.0f);
            } else {
                System.out.println("Wrong input");
            }

//
//                switch (response) {
//                    case ("play music"):
//                        clip.start();
//                        break;
//                    case ("stop music"):
//                        clip.stop();
//                        break;
//                    case ("reset"):
//                        clip.setMicrosecondPosition(0);
//                        gainControl.setValue(0);
//                        break;
//                    case ("lower volume"):
//                        gainControl.setValue(-8.0f);
//                        break;
//                    case ("raise volume"):
//                        gainControl.setValue(5.0f);
//                    default:
//                        System.out.println("Wrong input");
//                }
            //}

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}

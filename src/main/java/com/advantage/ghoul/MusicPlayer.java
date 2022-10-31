package com.advantage.ghoul;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;
import javax.sound.midi.Soundbank;
import javax.sound.sampled.*;

class MusicPlayer {

    void runMusic(String response) {
        try {
            ClassLoader classLoader = FileReading.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("music/Intro.wav");
//            System.out.println(inputStream.getClass().getSimpleName());
//            File file = new File("resources/music/Intro.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new BufferedInputStream(inputStream));
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            if (response.equals("play")) {
                clip.start();
            }
            else if (response.equals("stop")) {
                clip.stop();
            }
            else if (response.equals("reset")) {
                System.out.println(3);
                clip.setMicrosecondPosition(0);
                gainControl.setValue(0);
            }
            else if (response.equals("lower")) {
                System.out.println(2);
                gainControl.setValue(-8.0f);
            }
            else if (response.equals("raise")) {
                System.out.println(1);
                gainControl.setValue(5.0f);
            }
            else {
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

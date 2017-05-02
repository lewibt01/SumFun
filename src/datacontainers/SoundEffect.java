package datacontainers;

import view.GridView;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

public class SoundEffect {
    private AudioClip soundClip;

    public SoundEffect(File inputFile){
        try {
            URL path = Paths.get(inputFile.getPath()).toUri().toURL();
            soundClip = Applet.newAudioClip(path);
        }catch(Exception ex){
            System.err.println("soundFile URL error");
            ex.printStackTrace();
        }
    }

    public SoundEffect(String inputPath){
        try{
            System.out.println(inputPath);
            URL path = GridView.class.getResource(inputPath);
            System.out.println(path);
            soundClip = Applet.newAudioClip(path);
        }catch(Exception ex){
            System.err.println("soundFile URL error");
            ex.printStackTrace();
        }
    }

    public void play(){
        soundClip.play();
    }

}

package Engine;
import java.io.*;
import java.util.ArrayList;
import javax.sound.sampled.*;

public class SoundPlayer extends PositionedObj{

    private ArrayList<AudioInputStream> playList = new ArrayList<AudioInputStream>();
    private Clip clip;
    private FloatControl panControl;
    private FloatControl volumeControl;
    private boolean surround;

    public SoundPlayer(String name, World myWorld, Vector2D position, boolean surround){
        super(name,myWorld, position);
        try{
            clip = AudioSystem.getClip();
        }catch(Exception e) {
            System.out.println("Somthing went wrong with the Audio");
        }
        this.surround = surround;
    }

    public void addSound(String path){
        try{
            playList.add(AudioSystem.getAudioInputStream(new File(path)));
        }catch(Exception e){
            System.out.println("Audio File " + path + " not found or wrong File Format: "+ e.getMessage());
            System.exit(0);
        }
    }

    public void stopPlayer(){
        clip.stop();
        clip.close();
    }

    public void playSound(int index){
        stopPlayer();
        new Thread(new Runnable(){
            @Override
            public void run() {
                try{
                    clip.open(playList.get(index));
                    if(surround){
                        Cam c = getWorld().getActiveCamera();

                        volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                        panControl = (FloatControl) clip.getControl(FloatControl.Type.PAN);

                        //copute the right values
                        float distance =  (float) new Vector2D((int)c.getPosition().getA() - getPosition().getA(),(int) c.getPosition().getB() - getPosition().getB()).abs();
                        double xDist = (double) getPosition().getA() - c.getPosition().getA();

                        //set the right values
                        volumeControl.setValue(-distance*0.1f);
                        panControl.setValue((float) Math.tanh(xDist*0.06));

                    }
                    
                    clip.start();
                }catch(Exception e){}
            }
        }).run();
    }
    
}
        
package Engine;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;



public class ImageSequence extends Drawable{


    private ArrayList<Images> baseFrames = new ArrayList<Images>();
    private int frameCounter = 0;

    public ImageSequence(String folder, int startingFrame){
        this.frameCounter = startingFrame;
        for(int i = 0; true; i++){

            try{
                Images im = new Images(folder + "/"+i);
                baseFrames.add(im);
            }catch(IOException e){
                break;
            }
            
        }
    }

    public void setFrame(int frame){
        frameCounter = frame;
    }

    @Override
    public Image getImage(){
        return baseFrames.get(frameCounter).getImage();
    }

    @Override
    public void updateImage(Cam c,Vector2D size,double rotation) {
        for(Images g : baseFrames){
            g.updateImage(c,size, rotation);
        }
    }

}
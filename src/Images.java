package Engine;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images extends Drawable{

    private Image refImage;
    private BufferedImage image;


    public Images(String path) throws IOException{
        refImage = ImageIO.read(new File(path+".png"));
    }

    public void updateImage(Cam c,Vector2D size,double rotation){
        Vector2D v = c.getCamScale(size);
        image = new BufferedImage((int) (v.getIntA()/Math.sqrt(2) + v.getIntB()/Math.sqrt(2)),(int) (v.getIntA()/Math.sqrt(2) + v.getIntB()/Math.sqrt(2)),BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        g2.translate(image.getWidth()/2,image.getHeight()/2);
        g2.rotate(rotation);
        g2.drawImage(refImage, -v.getIntA()/2, -v.getIntB()/2,v.getIntA(), v.getIntB(), null);
    }


    @Override
    public Image getImage(){
        return image;
    }

}
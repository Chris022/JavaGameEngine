package Engine;
import java.awt.Image;

public abstract class Drawable{

    public abstract Image getImage();
    public abstract void updateImage(Cam c,Vector2D size,double rotation);

}
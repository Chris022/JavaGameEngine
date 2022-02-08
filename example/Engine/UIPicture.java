package Engine;
import java.awt.Graphics2D;

public class UIPicture extends UI {


    private Vector2D position;
    private Vector2D size;
    private Drawable drawable;
    private double rotation;

    public UIPicture(String name,Vector2D position, Vector2D size, Drawable d, double rotation){
        super(name);
        this.position = position;
        this.size = size;
        this.drawable = d;
        this.rotation = rotation;
    }

    @Override
    public void update(Cam c) {
        drawable.updateImage(c,size, rotation);

    }

    @Override
    public void draw(Graphics2D g,Cam c) {
        Vector2D p = c.getCamCoordinatesWithOutPos(getPosition());
        g.drawImage(drawable.getImage(),p.getIntA(),p.getIntB(),null);
    }

    public Vector2D getPosition(){
        return position;
    }
    public void setPosition(Vector2D pos){
        position = pos;
    }


    
}
package Engine;
import java.awt.Graphics2D;

public class DrawableObj extends PositionedObj{

    private Vector2D size;
    private Drawable drawable;
    private double rotation;

    public DrawableObj(String name, World myWorld, Vector2D position, Vector2D size, Drawable drawable, double rotation){
        super(name,myWorld,position);
        this.size = size;
        bufferSize = size;
        this.drawable = drawable;
        this.rotation = rotation;
        bufferRotation = rotation;
        //drawable.updateImage(myWorld,size,rotation);

    }
    public DrawableObj(String name, World myWorld, Vector2D position, Vector2D size, Drawable drawable, double rotation, Hitbox hitbox){
        super(name,myWorld,position,hitbox);
        this.size = size;
        bufferSize = size;
        this.drawable = drawable;
        this.rotation = rotation;
        bufferRotation = rotation;
    }
    public void setSize(Vector2D size){
        this.size = size;
    }

    public void setRotation(double rotation){
        this.rotation = rotation;
    }

    public void addRotation(double rot){
        rotation += rot;
    }

    public double getRotation(){
        return rotation;
    }
    public Vector2D getSize(){
        return size;
    }

    public void updateImageSize(Cam c){
        drawable.updateImage(c,size, rotation);
    }

    private Vector2D bufferSize;
    private double bufferRotation;
    public void drawGraphics(Graphics2D g){
        //only draw the obj if there is a active cam
        Cam activeCam = getWorld().getActiveCamera();
        if(activeCam != null){

            if(bufferRotation != rotation || bufferSize.getA() != size.getA() || bufferSize.getB() != size.getB()){
                drawable.updateImage(activeCam,size,rotation);
                bufferRotation = rotation;
                bufferSize = size;
            }
            Vector2D p = activeCam.getCamCoordinates(getPosition());
            g.drawImage(drawable.getImage(),(int)p.getA(),(int)p.getB(),null);

        }
        
    }

}
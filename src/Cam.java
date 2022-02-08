package Engine;

import java.awt.Graphics;
public class Cam extends PositionedObj{

    private Vector2D aspectRatio;
    private Vector2D screenSize;
    private Vector2D size;
    private boolean active;
    private double rotation;


    public Cam(String name,World myWorld,Vector2D aspectRatio, Vector2D position, boolean active, double fov,double rotation){
        super(name,myWorld,position);
        this.aspectRatio = aspectRatio.multi(fov);
        this.active = active;
        setScale(new Vector2D(Universe.getScreen().getWidth(),Universe.getScreen().getHeight()));
        this.rotation = rotation;
    }

    public void setRotation(double rotation){
        this.rotation = rotation;
    }

    public double getRotation(){
        return this.rotation;
    }

    public Vector2D getSize(){
        return size;
    }

    public void setActive(boolean ac){
        this.active = ac;
    }

    public boolean getActive(){
        return this.active;
    }

    //re-calculate the Vector2D of the camera
    public void setScale(Vector2D screenSize){
        this.screenSize = screenSize;
        if((screenSize.getB() < screenSize.getA() && aspectRatio.getB() >= aspectRatio.getA()||aspectRatio.getB()*screenSize.getA()/aspectRatio.getA() > screenSize.getB())){
            size = new Vector2D(aspectRatio.getA()*screenSize.getB()/aspectRatio.getB(), screenSize.getB());
        }else{
            size = new Vector2D(screenSize.getA(),aspectRatio.getB()*screenSize.getA()/aspectRatio.getA());
        }
    }

    public Vector2D getViewPortSize(){
        return new Vector2D(aspectRatio.getA()*10, aspectRatio.getB()*10);
    }

    //convert global Vector2Ds to global Vector2Ds
    public Vector2D getCamScale(Vector2D scale){
        return new Vector2D(   scale.getA()*size.getA()/(10*aspectRatio.getA())   ,    scale.getB()*size.getB()/(10*aspectRatio.getB())   );
    }

    //Also calculates the Cam Position in
    public Vector2D getCamCoordinates(Vector2D coordinates){
        double x = coordinates.getA()-getPosition().getA();
        double y = coordinates.getB()-getPosition().getB();
        return Vector2D.add(  getOrigin()  ,  new Vector2D(    x*size.getA()/(10*aspectRatio.getA())    ,    y*size.getB()/(10*aspectRatio.getB())   ) );
    }

    public Vector2D getCamCoordinatesWithOutPos(Vector2D coordinates){
        double x = coordinates.getA();
        double y = coordinates.getB();
        return Vector2D.add(  getOrigin()  ,  new Vector2D(    x*size.getA()/(10*aspectRatio.getA())    ,    y*size.getB()/(10*aspectRatio.getB())   )  );
    }

    public Vector2D getGlobalCoordinates(Vector2D coordinates){
        coordinates = Vector2D.sub(  coordinates  , getOrigin());
        coordinates = new Vector2D(coordinates.getA(),coordinates.getB()-30);
        return Vector2D.add(new Vector2D(coordinates.getA()*(10*aspectRatio.getA()/size.getA()),coordinates.getB()*(10*aspectRatio.getB())/size.getB()),getPosition());
    }

    //get the global coordinates of the starting Vector2D of the camera
    public Vector2D getOrigin(){
        return new Vector2D(screenSize.getA()/2-size.getA()/2,screenSize.getB()/2-size.getB()/2);
    }
}
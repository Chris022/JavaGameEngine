package Engine;
import java.awt.event.*;

public abstract class Obj{

    private String NAME;
    private World WORLD;

    public Obj(String name,World myWorld){
        this.NAME = name;
        this.WORLD = myWorld;
    }

    public String getName(){
        return NAME;
    }
    public World getWorld(){
        return WORLD;
    }

    public void tick(String timerName){}
    public void keyPressed(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
    public void mousePressed(GlobalMouseEvent m){}
    public void mouseReleased(GlobalMouseEvent m){}
    public void mouseMoved(GlobalMouseEvent m){}
    public void mouseDragged(GlobalMouseEvent m){}

}
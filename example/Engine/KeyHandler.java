package Engine;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    private World WORLD;

    public KeyHandler(World myWorld){
        WORLD = myWorld;
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        try{
            for(Obj o : WORLD.getAllObjs()){
                o.keyPressed(e);
            }
        }catch(java.util.ConcurrentModificationException er){}
    }

    public void keyReleased(KeyEvent e) {
        try{
            for(Obj o : WORLD.getAllObjs()){
                o.keyReleased(e);
            }
        }catch(java.util.ConcurrentModificationException er){}
    }

}
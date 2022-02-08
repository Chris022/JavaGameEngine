package Engine;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMotionHandler implements MouseMotionListener{

    private World WORLD;

    public MouseMotionHandler(World myWorld){
        WORLD = myWorld;
    }

    public void mouseMoved(MouseEvent e) {
        try{
            for(Obj o : WORLD.getAllObjs()){
                o.mouseMoved(new GlobalMouseEvent(e,WORLD));
            }
        }catch(java.util.ConcurrentModificationException er){}
    }
 
     public void mouseDragged(MouseEvent e) {
        try{
            for(Obj o : WORLD.getAllObjs()){
                o.mouseDragged(new GlobalMouseEvent(e,WORLD));
            }
        }catch(java.util.ConcurrentModificationException er){}
        
    }
}
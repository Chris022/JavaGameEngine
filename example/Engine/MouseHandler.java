package Engine;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener{

    private World WORLD;

    public MouseHandler(World myWorld){
        WORLD = myWorld;
    }


    public void mousePressed(MouseEvent e) {
        try{
            for(Obj o : WORLD.getAllObjs()){
                o.mousePressed(new GlobalMouseEvent(e,WORLD));
            }
        }catch(java.util.ConcurrentModificationException er){}
    }
 
    public void mouseReleased(MouseEvent e) {
        while(true){
            try{
                for(Obj o : WORLD.getAllObjs()){
                    o.mouseReleased(new GlobalMouseEvent(e,WORLD));
                }
                break;
            }catch(java.util.ConcurrentModificationException er){}
        }
        
    }
 
    public void mouseEntered(MouseEvent e) {
    }
 
    public void mouseExited(MouseEvent e) {
    }
 
    public void mouseClicked(MouseEvent e) {
    }
}
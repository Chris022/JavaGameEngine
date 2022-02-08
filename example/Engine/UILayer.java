package Engine;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class UILayer{

    World myWorld;
    ArrayList<UI> UIobj = new ArrayList<UI>();

    public UILayer(World myWorld){
        this.myWorld = myWorld;
    }

    public void updateSize(){
        Cam c = myWorld.getActiveCamera();
        if(c != null){
            for(UI u : UIobj){
                u.update(c);
            }
        }
    }

    public void draw(Graphics g){
        Cam c = myWorld.getActiveCamera();
        if(c != null){
            for(UI u : UIobj){
                u.draw((Graphics2D) g,c);
            }
        }
        
    }

    public void addUIElement(UI u){
        UIobj.add(u);
    }

}
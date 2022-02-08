package Engine;
import java.awt.Component;
import java.awt.event.MouseEvent;


public class GlobalMouseEvent extends MouseEvent {

    public GlobalMouseEvent(MouseEvent e,World myWorld) {
        super((Component) e.getSource(), e.getID(), e.getWhen(), e.getModifiersEx(), e.getX(), e.getY(), e.getClickCount(), e.isPopupTrigger());
        Cam c = myWorld.getActiveCamera();
        if(c != null){
            this.globalPos = c.getGlobalCoordinates(new Vector2D(e.getX(),e.getY()));
        }
    }

    private static final long serialVersionUID = 1L;

	private Vector2D globalPos;

    public Vector2D getGlobalPos(){
        return globalPos;
    }

    public boolean touches(DrawableObj po){
        if(getGlobalPos().getA() > po.getPosition().getA() && getGlobalPos().getA() < po.getPosition().getA() + po.getSize().getA()){
            if(getGlobalPos().getB() > po.getPosition().getB() && getGlobalPos().getB() < po.getPosition().getB() + po.getSize().getB()){
                return true;
            }
            return false;
        }
        return false;
    }


    
}
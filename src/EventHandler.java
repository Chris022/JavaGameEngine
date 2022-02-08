package Engine;
public abstract class EventHandler extends Obj{

    public EventHandler(String name, World myWorld) {
        super(name,myWorld);
    }
    
    public abstract void start();
    public abstract void stop();


}
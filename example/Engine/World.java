package Engine;
import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class World{

    private String NAME;
    private ArrayList<Obj> objectList = new ArrayList<Obj>();
    private UILayer uiLayer;
    
    public World(String name){
        this.NAME = name;
        uiLayer = new UILayer(this);
    }
    public String getName(){
        return NAME;
    }
    public void addObj(Obj t1){
        objectList.add(t1);
    }
    public void removeObj(Obj e){
        objectList.remove(e);
    }
    private void removeAllObjs(){
        objectList = new ArrayList<Obj>();
    }
    public UILayer getUILayer(){
        return uiLayer;
    }


    public ArrayList<PositionedObj> getObjWithHitbox(){
        return new ArrayList(objectList.stream().filter(x -> x instanceof PositionedObj && ((PositionedObj)x).hasHitbox()).collect(Collectors.toList()));
    }
    public ArrayList<PositionedObj> getAllPositionedObjs(){
        return new ArrayList(objectList.stream().filter(x -> x instanceof PositionedObj).collect(Collectors.toList()));
    }
    public ArrayList<DrawableObj> getAllDrawableObjs(){
        return new ArrayList(objectList.stream().filter(x -> x instanceof DrawableObj).collect(Collectors.toList()));
    }
    public ArrayList<Cam> getAllCamObjs(){
        return new ArrayList(objectList.stream().filter(x -> x instanceof Cam).collect(Collectors.toList()));
    }
    public ArrayList<Obj> getAllObjs(){
        return objectList;
    }
    public Obj getObjByName(String name){
        for(Obj o : objectList){
            if(o.getName().equals(name)){
                return o;
            }
        }
        return null;
    }
    public Cam getActiveCamera(){
        for(Cam c : this.getAllCamObjs()){
            if(c.getActive()){
                return c;
            }
        }
        return null;
    }

    public void startEventHandlers(){
        stopEventHandlers();
        objectList.stream().filter(x -> x instanceof EventHandler).forEach(x -> ((EventHandler)x).start());
    }

    public void stopEventHandlers(){
        objectList.stream().filter(x -> x instanceof EventHandler).forEach(x -> ((EventHandler)x).stop());
    }

    public void resetWorld(){
        stopEventHandlers();
        removeAllObjs();
        loadWorld();
    }

    public abstract void loadWorld();

    
}
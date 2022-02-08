package Engine;
import java.util.ArrayList;
import java.util.Arrays;

public class ClockedAction extends EventHandler implements Runnable{

    private int time;
    private ArrayList<String> objTickList = new ArrayList<String>();
    private boolean running = false;

    public ClockedAction(String name,World myWorld, int time){
        super(name,myWorld);
        this.time = time;
    }
    public ClockedAction(String name,World myWorld, int time,String[] objTickList){
        super(name,myWorld);
        this.time = time;
        this.objTickList = new ArrayList<String>(Arrays.asList(objTickList));
    }

    private Thread t;
    @Override
    public void start(){
        running = true;
        t = new Thread(this);
        t.start();
    }
    @Override
    public void stop() {
        running = false;
    }
    @Override
    public void run() {
        try{
            Thread.sleep(time);
        }catch(Exception e){}
        while(running){
            try{
                for(Obj o : getWorld().getAllObjs()){
                    if(objTickList.contains(o.getName()) || objTickList.size() == 0){
                        o.tick(getName());
                    }
                }
                break;
            }catch(java.util.ConcurrentModificationException er){}
        }        
    }


}
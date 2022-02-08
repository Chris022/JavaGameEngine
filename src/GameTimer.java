package Engine;
import java.util.ArrayList;
import java.util.Arrays;


public class GameTimer extends EventHandler implements Runnable{

    private int time;
    ArrayList<String> objTickList = new ArrayList<String>();
    private boolean running = false;

    public GameTimer(String name,World myWorld,int time){
        super(name,myWorld);
        this.time = time;
    }

    //give an List of the names of all objects that should be ticked by the timer
    public GameTimer(String name,World myWorld, int time,String[] objTickList){
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
    public void stop(){
        running = false;
        try{Thread.sleep(20);}catch(Exception e){}
    }
    @Override
    public void run() {
        while(running){
            try{
                for(Obj o : getWorld().getAllObjs()){
                    if(objTickList.contains(o.getName()) || objTickList.size() == 0){
                        o.tick(getName());
                    }
                }
                try{
                    Thread.sleep(time);
                }catch(Exception e){}
            }catch(java.util.ConcurrentModificationException er){}
        }
    }

}
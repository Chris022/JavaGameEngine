package Engine;
public class PhysicsHandler extends EventHandler implements Runnable {

    private boolean running = false;

    public PhysicsHandler(String name,World myWorld){
        super(name,myWorld);
    }

    private Thread t;

    public void start(){
        running = true;
        t = new Thread(this);
        t.start();
    }

    public void stop(){
        running = false;
    }

    @Override
    public void run() {
        while(running){
            //System.out.println(running);
            try{
                for(PositionedObj o1 : getWorld().getObjWithHitbox()){
                    for(PositionedObj o2 : getWorld().getObjWithHitbox()){
                        if(o1 != o2){
                            if(o2.colliding(o1)) {
                                if(o1.getName().equals("tree"))
                                    System.out.println("collision: "+o1.getName() + " "+o2.getName());
                                o1.onCollision(o2);
                            }
                        }
                    }
                }
            try{Thread.sleep(10);}catch(Exception e){}
            }catch(java.util.ConcurrentModificationException er){} 
        }
        
    }
}
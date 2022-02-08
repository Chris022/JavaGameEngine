package Engine;
import java.util.ArrayList;

public class Universe{

    private static ArrayList<World> worlds = new ArrayList<World>();
    private static String activeWorld;
    private static Screen screen;


    public static void setScreen(Screen s){
        screen = s;
    }

    public static Screen getScreen(){
        return screen;
    }

    public static void addWorld(World w){
        worlds.add(w);
    }

    public static void setActiveWorld(String world){
        screen.stopGraphics();
        if(getActiveWorld() != null){
            getActiveWorld().stopEventHandlers();
        }
        activeWorld = world;
        getActiveWorld().resetWorld();
        getActiveWorld().startEventHandlers();
        screen.startGraphics();
    }

    public static void removeWorld(String name){
        for(World o : worlds){
            if(o.getName().equals(name)){
                worlds.remove(o);
            }
        }
    }

    public static World getWorldByName(String name){
        for(World o : worlds){
            if(o.getName().equals(name)){
                return o;
            }
        }
        return null;
    }

    public static World getActiveWorld(){
        return getWorldByName(activeWorld);
    }


}
package Engine;
import java.awt.Graphics2D;

public abstract class UI{
    private String name;
    public UI(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
    public abstract void update(Cam c);

    public abstract void draw(Graphics2D g,Cam c);

}
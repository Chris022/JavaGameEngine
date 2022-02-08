import java.awt.event.KeyEvent;

import Engine.*;
public class Ball extends DrawableObj{

    Vector2D vel = new Vector2D(0, 0);

    public Ball(String name,World myWorld, Vector2D position, Vector2D size, Drawable drawable, double rotation,Hitbox hitbox) {
        super(name, myWorld, position, size, drawable, rotation,hitbox);
        double angle = Math.random()*(2*Math.PI);
    }

    public void tick(String timer){
    }

    @Override
    public void keyPressed(KeyEvent e){
        addPosition(new Vector2D(1, 1));
    }
    @Override
    public void keyReleased(KeyEvent e){
        
    }

}
import java.awt.Font;

import Engine.*;

public class GameWorld extends World{

    boolean ui;

    public GameWorld(boolean UI,String Name) {
        super(Name);
        this.ui = UI;
    }

    @Override
    public void loadWorld() {
        if(ui)
            this.getUILayer().addUIElement(new Text("my Text1", new Vector2D(10, 10), "This is an UI Element", new Font("Areal", Font.PLAIN, 10)));
        
        PhysicsHandler ph1 = new PhysicsHandler("phHander 1",this);
        this.addObj(ph1);

        GameTimer t1 = new GameTimer("timer1",this, 1,new String[]{"tree"});
        this.addObj(t1);

        KeyHandler k1 = new KeyHandler(this);

        Cam cam = new Cam("cam1",this,new Vector2D(16,7),new Vector2D(2,2),true,2,0);
        this.addObj(cam);

        Obj ball = new Ball("tree",this, cam.getViewPortSize().multi(0.5), new Vector2D(10, 10), new ImageSequence("Images/person",0),0,new Hitbox(new Vector2D(10, 10)));
        this.addObj(ball);

        Obj wallLeft = new PositionedObj("wall Left",this,new Vector2D(0, 0),new Hitbox(new Vector2D(10, cam.getViewPortSize().getB())));
        this.addObj(wallLeft);
        Obj wallRight = new PositionedObj("wall Right",this,new Vector2D(cam.getViewPortSize().getA(), 0),new Hitbox(new Vector2D(10, cam.getViewPortSize().getB())));
        this.addObj(wallRight);
        Obj wallUp = new PositionedObj("wall Up",this,new Vector2D(0, -3),new Hitbox(new Vector2D(cam.getViewPortSize().getA(), 3)));
        this.addObj(wallUp);
        Obj wallDown = new PositionedObj("wall Down",this,new Vector2D(0,cam.getViewPortSize().getB()-10),new Hitbox(new Vector2D(cam.getViewPortSize().getA(), 10)));
        this.addObj(wallDown);

        SoundPlayer player = new SoundPlayer("adio1", this, new Vector2D(0, 0), true);
        player.addSound("Sounds/sound.wav");
        player.playSound(0);
        this.addObj(player);
    }

    
}
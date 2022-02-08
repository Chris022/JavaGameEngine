package Engine;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Screen extends JFrame{

    private Color backgroundColor;
    private DrawArea drawArea;
    private int height,width;
    private boolean running = false;
    private KeyHandler kh;
    private MouseHandler mh;
    private MouseMotionHandler mmh;


    public Screen(int x, int y,String name,Color backgroundColor,boolean fullScreen){
        height = this.getHeight();
        width = this.getWidth();
        if(fullScreen){
            GraphicsDevice vc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            this.setUndecorated(true);
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            vc.setFullScreenWindow(this);
        }else{
            this.setSize(x,y);
        }
        this.backgroundColor = backgroundColor;
        this.setTitle(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawArea = new DrawArea();
        this.add(drawArea);
        this.setVisible(true);
    }

    Thread t = new Thread(new Runnable(){
        @Override
        public void run() {
            while(running){
                Toolkit.getDefaultToolkit().sync();
                drawArea.repaint();
                try{Thread.sleep(10);}catch(Exception e){}
            }
        }
    });
    public void startGraphics(){
        stopGraphics();
        kh = new KeyHandler(Universe.getActiveWorld());
        mh = new MouseHandler(Universe.getActiveWorld());
        mmh = new MouseMotionHandler(Universe.getActiveWorld());
        this.addKeyListener(kh);
        this.addMouseListener(mh);
        this.addMouseMotionListener(mmh);
        t = new Thread(new Runnable(){
            @Override
            public void run() {
                while(running){
                    Toolkit.getDefaultToolkit().sync();
                    drawArea.repaint();
                    try{Thread.sleep(10);}catch(Exception e){}
                }
            }
        });
        running = true;
        t.start();
    }

    public void stopGraphics(){
        running = false;
        while(t.isAlive())
            try{Thread.sleep(1);}catch(Exception e){}
        this.removeKeyListener(kh);
        this.removeMouseListener(mh);
        this.removeMouseMotionListener(mmh);
    }


    private class DrawArea extends JPanel{

        @Override
        public void paintComponent(Graphics g){
            Graphics2D g2 = (Graphics2D) g;
            //super.paintComponent(g);
            World activeWorld = Universe.getActiveWorld();
            //System.out.println(activeWorld.getName());
            if(activeWorld != null){
                Cam cam = activeWorld.getActiveCamera();
                //if there exists a active cam
                if(cam != null){
                    g2.translate(this.getWidth()/2, this.getHeight()/2);
                    g2.rotate(cam.getRotation());
                    g2.translate(-this.getWidth()/2, -this.getHeight()/2);
                    //check if the screen Size was changed
                    boolean changed = this.getHeight() != height || this.getWidth() != width;
                    if(changed){
                        //set the scale of the active cam
                        cam.setScale(new Vector2D(this.getWidth(), this.getHeight()));
                        height = this.getHeight();
                        width = this.getWidth();
                        //update size for the UI
                        activeWorld.getUILayer().updateSize();
                    }
                    //draw all drawable Obj's
                    ArrayList<DrawableObj> dr = activeWorld.getAllDrawableObjs();
                    g2.clearRect(0, 0, width, height);
                    for(DrawableObj e : dr){
                        if(changed) e.updateImageSize(cam);
                        e.drawGraphics(g2);
                    }

                    activeWorld.getUILayer().draw(g);
                    g2.translate(this.getWidth()/2, this.getHeight()/2);
                    g2.rotate(-cam.getRotation());
                    g2.translate(-this.getWidth()/2, -this.getHeight()/2);
                    clearSpareArea(cam, g2);
                }

            }
            
        }

        public void clearSpareArea(Cam cam, Graphics2D g){
            g.setColor(backgroundColor);
            //clear everything that isn't in the cameras view
            g.fillRect(0, 0,(int) cam.getOrigin().getA(), this.getHeight());
            g.fillRect(0, 0, this.getWidth(),(int) cam.getOrigin().getB());
            g.fillRect((int) Vector2D.add(cam.getOrigin() , cam.getSize()).getA(), 0, this.getWidth(), this.getHeight());
            g.fillRect(0, (int) Vector2D.add(cam.getOrigin() , cam.getSize()).getB(), this.getWidth(), this.getHeight());
        }
           
    }

}
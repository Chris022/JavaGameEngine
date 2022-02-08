package Engine;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Text extends UI{

    private Vector2D position;
    private Image image;
    private Image baseImage;
    private int height;
    private int width;
    private Font font;
    private String text;

    public Text(String name,Vector2D position, String text,Font t){
        super(name);
        this.position = position;
        this.font = t;
        this.text = text;
        renderText();
    }

    public void renderText(){
        height = new Canvas().getFontMetrics(font).getHeight();
        width = new Canvas().getFontMetrics(font).stringWidth(text);
        int ascent = new Canvas().getFontMetrics(font).getDescent();
        baseImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D)baseImage.getGraphics();
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString(text, 0, height-ascent);
        image = baseImage;
    }

    public void setText(String text){
        this.text = text;
        renderText();
    }


    @Override
    public void update(Cam c) {
        if(c != null){
            Vector2D size = c.getCamScale(new Vector2D(width,height));
            image = baseImage.getScaledInstance(size.getIntA(), size.getIntB(), Image.SCALE_SMOOTH);
        }
    }

    @Override
    public void draw(Graphics2D g,Cam c) {
        if(c != null){
            Vector2D pos = c.getCamCoordinatesWithOutPos(position);
            g.drawImage(image,pos.getIntA(),pos.getIntB(),null);
        }
    }

}
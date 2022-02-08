package Engine;
public class Hitbox{

    private Vector2D size;
    private Vector2D pos;
    public Hitbox(Vector2D size, Vector2D pos){
        this.size = size;
        this.pos = pos;
    }
    public Hitbox(Vector2D size){
        this.size = size;
        this.pos = new Vector2D(0, 0);
    }

    public Vector2D getPos(){
        return pos;
    }

    public Vector2D getSize(){
        return size;
    }

}
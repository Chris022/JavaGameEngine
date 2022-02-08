package Engine;
public class PositionedObj extends Obj{

    private Vector2D position;
    private Hitbox hitbox;

    public PositionedObj(String name, World myWorld, Vector2D position){
        super(name,myWorld);
        this.position = position;
        this.hitbox = null;
    }
    public PositionedObj(String name, World myWorld, Vector2D position,Hitbox hitbox){
        super(name,myWorld);
        this.position = position;
        this.hitbox = hitbox;
    }

    public void setPosition(Vector2D position){
        this.position = position;
    }
    public void addPosition(Vector2D position){
        this.position = Vector2D.add(this.position,position);
    }
    public Vector2D getPosition(){
        return this.position;
    }


    public boolean hasHitbox(){
        if(hitbox == null){
            return false;
        }
        return true;
    }
    public void setHitbox(Hitbox hitbox){
        this.hitbox = hitbox;
    }
    public Hitbox getHitbox(){
        return hitbox;
    }
    public Vector2D getHitboxPosition(){
        return Vector2D.add(getPosition(),hitbox.getPos());
    }
    public boolean colliding(PositionedObj b){
        if(Vector2D.biggetOrEqual(  b.getHitboxPosition()   ,  this.getHitboxPosition()  )  ){
            if(  Vector2D.smallerOrEqual(  b.getHitboxPosition()  ,  Vector2D.add(  this.getHitboxPosition()  ,  this.hitbox.getSize()  )  )  ){
                return true;
            }
        }
        return false;
    }

    public void onCollision(Obj o){}

}
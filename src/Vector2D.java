package Engine;
public class Vector2D{

    private double a;
    private double b;

    public Vector2D(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double getA() {
        return a;
    }
    public double getB() {
        return b;
    }
    public int getIntA() {
        return (int)a;
    }
    public int getIntB() {
        return (int)b;
    }
    public double abs() {
        return Math.sqrt(a * a + b * b);
    }
    public Vector2D multi(double lam){
        return new Vector2D(getA()*lam, getB()*lam);
    }
    public String toString() {
        return a + " " + b;
    }

    public static Vector2D add(Vector2D a,Vector2D b) {
        return new Vector2D(a.getA() + b.getA(), a.getB() + b.getB());
    }
    public static Vector2D sub(Vector2D a,Vector2D b) {
        return new Vector2D(a.getA() - b.getA(), a.getB() - b.getB());
    }
    public static boolean biggetOrEqual(Vector2D a,Vector2D b){
        if(a.getA() >= b.getA() && a.getB() >= b.getB()){
            return true;
        }
        return false;
    }
    public static boolean smallerOrEqual(Vector2D a,Vector2D b){
        if(a.getA() <= b.getA() && a.getB() <= b.getB()){
            return true;
        }
        return false;
    }

}
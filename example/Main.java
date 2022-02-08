import java.awt.Color;
import java.io.IOException;

import Engine.*;

public class Main {

    public static void main(String[] args) {
        try {
            new Main().main();
        } catch (Exception e) {
        }
    }

    public void main() throws IOException {

        Screen s = new Screen(500,900,"TestGame",Color.WHITE,false);
        Universe.setScreen(s);
        
        World w = new GameWorld(true,"World 1");
        Universe.addWorld(w);
        Universe.setActiveWorld(w.getName());

    }

}
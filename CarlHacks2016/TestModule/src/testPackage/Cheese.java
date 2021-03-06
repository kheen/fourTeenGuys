package testPackage;

import acm.graphics.GImage;
import acm.graphics.GOval;
import acm.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Kai on 4/9/16.
 */
public class Cheese {

    GImage cheese;
    double x;
    double y;

    public Cheese(double x, double y) {
        this.x = x+10;
        this.y = y+10;

        try {
            cheese = new GImage(ImageIO.read(getClass().getResource("/cheese.png")));
        } catch (IOException e) {
        }

        cheese.setLocation(x, y);
        cheese.setSize(MouseGame.WIDTH / 50, MouseGame.HEIGHT / 50);
    }

    public GImage getCheese() {
        return cheese;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String toString() {
        if (x == (50)) {
            return ("Banana");
        }
        return "Orange";
    }
}

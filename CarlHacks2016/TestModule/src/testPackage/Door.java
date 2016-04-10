package testPackage;

import acm.graphics.GImage;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Created by Kai on 4/9/16.
 */
public class Door{
    GImage door;
    double x;
    double y;


    public Door (double x, double y) {
        this.x=x+30;
        this.y=y+30;

        try {
            this.door = new GImage(ImageIO.read(getClass().getResource("/door.png")));
        } catch (IOException e) {}


        this.door.setLocation(x,y);
        this.door.setSize(MouseGame.WIDTH/15,MouseGame.HEIGHT/8);
    }

    public GImage getDoor() {
        return door;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}

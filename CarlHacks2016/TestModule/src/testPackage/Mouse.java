package testPackage;

import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GOval;
import acm.*;
import acm.graphics.GPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Kai on 4/9/16.
 */
public class Mouse {


    GImage mouse;
    double x;
    double y;

    public Mouse(double x, double y) {



        try {
            mouse = new GImage(ImageIO.read(getClass().getResource("/mouse.png")));
        } catch (IOException e) {
        }

        this.x = x+15;
        this.y = y+15;


        mouse.setSize(MouseGame.WIDTH / 20, MouseGame.HEIGHT / 15);
        mouse.setLocation(x-10, y-7);
    }


    public GImage getMouse() {
        return mouse;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    public void setMouse(GImage mouse) {
        this.mouse = mouse;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }


    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Mouse)) {
            return false;
        }

        Mouse mouse = (Mouse) other;
        return (mouse.getX()==x && mouse.getY()==y);
    }
}

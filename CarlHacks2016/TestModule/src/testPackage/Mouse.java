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

        this.x = x;
        this.y = y;

        try {
            mouse = new GImage(ImageIO.read(getClass().getResource("/mouse.png")));
        } catch (IOException e) {
        }


        mouse.setSize(MouseGame.WIDTH / 20, MouseGame.HEIGHT / 15);
        mouse.setLocation(x, y);
    }

    public double distance(GPoint x1, GPoint x2) {
        double mouseX = x1.getX();
        double mouseY = x1.getY();
        double cheeseX = x2.getX();
        double cheeseY = x2.getY();
        double dis = Math.sqrt(Math.pow(cheeseX - mouseX, 2) + Math.pow(cheeseY - mouseY, 2));
        return dis;
    }
}

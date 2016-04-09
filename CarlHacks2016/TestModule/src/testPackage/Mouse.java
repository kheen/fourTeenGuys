package testPackage;

import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GOval;
import acm.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Kai on 4/9/16.
 */
public class Mouse{


    GImage mouse;
    double x;
    double y;

    public Mouse(double x, double y) {

        this.x=x;
        this.y=y;

        try {
            mouse = new GImage(ImageIO.read(getClass().getResource("/mouse.png")));
        } catch (IOException e) {}


        mouse.setSize(MouseGame.WIDTH / 20, MouseGame.HEIGHT / 15);
        mouse.setLocation(x,y);
    }
}

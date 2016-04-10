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
        this.x=x;
        this.y=y;

        try {
            this.door = new GImage(ImageIO.read(getClass().getResource("/door.png")));
        } catch (IOException e) {}


        this.door.setLocation(x,y);
        this.door.setSize(MouseGame.WIDTH/15,MouseGame.HEIGHT/8);
    }


}

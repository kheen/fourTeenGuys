package testPackage;

import acm.graphics.GImage;
import acm.graphics.GOval;
import acm.*;

import javax.imageio.ImageIO;
import java.awt.*;

/**
 * Created by Kai on 4/9/16.
 */
public class Cheese extends GImage{

    public Cheese (double x, double y) {

        super ("/Users/Kai/Desktop/Hack/fourTeenGuys/CarlHacks2016/TestModule/src/testPackage/cheese.png",x,y);

        this.setSize(MouseGame.WIDTH/50,MouseGame.HEIGHT/50);
    }
}

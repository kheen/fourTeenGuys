package testPackage;

import acm.graphics.GOval;
import acm.*;

import java.awt.*;

/**
 * Created by Kai on 4/9/16.
 */
public class Mouse extends GOval {



    public Mouse (double x, double y, double width, double height) {
        super(x, y, width, height);
        this.setFillColor(Color.GRAY);
        this.setFilled(true);

    }
}

package testPackage;
import acm.graphics.GRect;

import java.awt.*;

/**
 * Created by Kai on 4/9/16.
 */
public class Barrier extends GRect{

        private int wall1Y = 100;

        public Barrier(double x, double y, double width, double height) {
            super(x,y,width,height);
            Color color = new Color(128,128,128);
            setColor(color);
            setFillColor(Color.BLACK);
            setFilled(true);

        }

        public void moveRect (double x, double y){
        setLocation(x,y);




    }
}


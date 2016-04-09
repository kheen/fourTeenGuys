package testPackage;

import acm.program.GraphicsProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Kai on 4/9/16.
 */
public class MouseGame extends GraphicsProgram{

    Mouse mouse = new Mouse (100,100,50,50);

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    public static int numCheeses = 10;


    public void init(){
        setSize (WIDTH, HEIGHT);
        add(mouse);
        addMouseListeners();

    }

    public void run (){
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        if (numCheeses>0){
            double chX = e.getX();
            double chY = e.getY();
            add(new Cheese (chX-WIDTH/200,chY-HEIGHT/200));
            numCheeses--;
        }




    }
}

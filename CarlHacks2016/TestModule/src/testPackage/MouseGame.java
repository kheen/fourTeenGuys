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

    public static MouseEvent e;


    public void init(){
        setSize (WIDTH, HEIGHT);
        add(mouse);
        addMouseListeners();

    }

    public void run (){
        mouseClicked(e);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        this.e=e;
        double chX = e.getX();
        double chY = e.getY();
        add(new Cheese (chX-WIDTH/200,chY-HEIGHT/200));


    }
}

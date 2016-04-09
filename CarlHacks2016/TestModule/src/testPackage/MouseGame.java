package testPackage;

import acm.program.GraphicsProgram;

import java.awt.*;

/**
 * Created by Kai on 4/9/16.
 */
public class MouseGame extends GraphicsProgram{

    Mouse mouse = new Mouse (100,100,50,50);

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;


    public void init(){
        setSize (WIDTH, HEIGHT);
        add(mouse);

    }

    public void run (){

    }
}

package testPackage;

import acm.graphics.GImage;
import acm.program.GraphicsProgram;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

/**
 * Created by Kai on 4/9/16.
 */
public class MouseGame extends GraphicsProgram implements MouseListener {

    Mouse mouse1;
    Door door1;
    GImage bg;

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    public static int numCheeses = 10;


    public void init(){
        setSize(WIDTH, HEIGHT);
        addMouseListeners();

        //Set Bg
        try {
            bg = new GImage(ImageIO.read(getClass().getResource("/floor.jpg")));
        } catch (IOException e) {}
        bg.setSize(WIDTH, HEIGHT);
        add(bg);

        mouse1 = new Mouse (100,100);
        add(mouse1.mouse);
        door1 = new Door(600,600);
        add(door1.door);


    }

    public void run (){
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        if (numCheeses>0){
            double chX = e.getX();
            double chY = e.getY();
            add(new Cheese(chX-WIDTH/200,chY-HEIGHT/200).cheese);
            numCheeses--;
        }




    }
}

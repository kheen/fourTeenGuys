package testPackage;

import acm.graphics.GImage;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Kai on 4/9/16.
 */
public class MouseGame extends GraphicsProgram implements MouseListener {

    private Mouse mouse1;
    private Door door1;
    private GImage bg;
    private ArrayList<Cheese> cheeses;

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    public static int numCheesesLeft = 10;


    public void init(){
        setSize(WIDTH, HEIGHT);
        addMouseListeners();
        cheeses = new ArrayList<Cheese>();

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


//        Cheese c1 = new Cheese (50,50);
//        Cheese c2 = new Cheese (100,100);
//
//        cheeses.add(c1);
//        cheeses.add(c2);
//
//        System.out.println(cheeses.get(0).toString());
//        System.out.println(cheeses.get(1).toString());




//        for (int i=0; i<100; i++){
//            GPoint direction = pathFinder(cheeses);
//            mouse1.mouse.move(direction.getX(),direction.getY());
//
//            mouse1.setX(mouse1.getX() + direction.getX());
//            mouse1.setY(mouse1.getY() + direction.getY());
//
//            pause(100);
//        }
    }



    public GPoint pathFinder(ArrayList<Cheese> cheeses){

        int i = 0;

        //Final cheese-mouse vector
        double totalX = 0;
        double totalY = 0;

        //add up all cheese-mouse vectors
        while (i < 9){
            if (cheeses.get(i)!=null) {
                Cheese chz = cheeses.get(i);
                double vectorX = chz.getX() - mouse1.getX();
                double vectorY = chz.getY() - mouse1.getY();
                totalX += vectorX;
                totalY += vectorY;
                i++;
            }
        }

        //Scale down to unit vector
        double length = distance(new GPoint(0,0), new GPoint(totalX,totalY));
        double finalX = totalX/length;
        double finalY = totalY/length;


        return new GPoint (finalX, finalY);
    }




    public double distance(GPoint x1, GPoint x2) {
        double mouseX = x1.getX();
        double mouseY = x1.getY();
        double cheeseX = x2.getX();
        double cheeseY = x2.getY();
        double dis = Math.sqrt(Math.pow(cheeseX - mouseX, 2) + Math.pow(cheeseY - mouseY, 2));
        return dis;
        }

//    @Override
//    public void mouseClicked(MouseEvent e) {
//        if (numCheesesLeft>0){
//            double chX = e.getX();
//            double chY = e.getY();
//            Cheese c1 = new Cheese (chX-WIDTH/200,chY-HEIGHT/200);
//            add(c1.cheese);
//            cheeses.add(c1);
//            numCheesesLeft--;
//        }
//
//    }


}

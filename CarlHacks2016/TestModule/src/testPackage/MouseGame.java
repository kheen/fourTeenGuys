package testPackage;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/**
 * Created by Kai on 4/9/16.
 */
public class MouseGame extends GraphicsProgram implements MouseListener {

    private Mouse mouse1;
    private Door door1;
    private GImage bg;
    private Stack<Cheese> cheeses;

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    public static int numCheesesLeft = 10;


    public void init(){
        setSize(WIDTH, HEIGHT);
        addMouseListeners();

        cheeses = new Stack <Cheese> ();


        //Set Bg
//        try {
//            bg = new GImage(ImageIO.read(getClass().getResource("/floor.jpg")));
//        } catch (IOException e) {}
//        bg.setSize(WIDTH, HEIGHT);
//        add(bg);

        mouse1 = new Mouse (600,600);
        add(mouse1.mouse);
        door1 = new Door(100,100);
        add(door1.door);


    }

    public void run (){



        while (numCheesesLeft>0 && numCheesesLeft<=10){
//          System.out.println(mouse1.getX());
//          System.out.println(mouse1.getY());


            GPoint direction = pathFinder(cheeses);
            double xMove = direction.getX()*2;
            double yMove = direction.getY()*2;

            mouse1.mouse.move(xMove, yMove);


            mouse1.setX(mouse1.getX() + direction.getX() * 2);
            mouse1.setY(mouse1.getY() + direction.getY()*2);

            GPoint position = new GPoint (mouse1.getX() + direction.getX() * 2,mouse1.getY() + direction.getY()*2);

            if (hitsDoor(mouse1)){
                endgame();
                break;
            }

            //Check for Cheese collisions

            if (getElementAt(position) != null && (getElementAt(position) != mouse1.mouse) && (getElementAt(position)!= door1.door)){
                System.out.println("Hello");

                Iterator <Cheese> iter = cheeses.iterator();

                while (iter.hasNext()){
                    Cheese c = iter.next();
                    if ((Math.abs(c.getX()-mouse1.mouse.getX())<35) && (Math.abs(c.getY()-mouse1.mouse.getY())<35)){
                        iter.remove();
                    }
                }
                remove(getElementAt(position));
            }

            pause(20);
        }
    }



    public GPoint pathFinder(Stack<Cheese> cheeses){

        Iterator <Cheese> iter = cheeses.iterator();

        //Final cheese-mouse vector
        double totalX = 0;
        double totalY = 0;

        //add up all cheese-mouse vectors
            while (iter.hasNext()) {
                Cheese chz = iter.next();
                double vectorX = (chz.getX() - mouse1.getX()) / Math.pow(distance(mouse1.mouse.getLocation(),chz.cheese.getLocation()),3);
                double vectorY = (chz.getY() - mouse1.getY()) / Math.pow(distance(mouse1.mouse.getLocation(),chz.cheese.getLocation()),3);
                totalX += vectorX;
                totalY += vectorY;
            }

        //Scale down to unit vector

        double length = distance(new GPoint(0,0), new GPoint(totalX,totalY));

        if (length==0){
            return new GPoint (0,0);
        }

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

    @Override
    public void mouseClicked(MouseEvent e) {
        if (numCheesesLeft>0){
            double chX = e.getX();
            double chY = e.getY();
            Cheese c1 = new Cheese (chX-WIDTH/200,chY-HEIGHT/200);
            cheeses.push(c1);
            numCheesesLeft--;
            System.out.println(numCheesesLeft);
            add(c1.cheese);
        }

    }


    public boolean hitsDoor (Mouse mouse){
        double startX = door1.door.getX();
        double startY = door1.door.getY();

        double endX = startX + door1.door.getWidth();
        double endY = startY + door1.door.getHeight();

        double startMouseX = mouse1.mouse.getX();
        double startMouseY = mouse1.mouse.getY();

        double endMouseX = startMouseX + mouse1.mouse.getWidth();
        double endMouseY = startMouseY + mouse1.mouse.getHeight();

        //from the Right
        if (endMouseX>startX && endMouseX<endX && endMouseY>startY && endMouseY<endY){
            return true;
        }

        else{
            return false;
        }
    }

    public void endgame(){
        GLabel endLabel = new GLabel ("Congratulations!");
        endLabel.setLocation(WIDTH/2,HEIGHT/2);
        add(endLabel);
    }

}

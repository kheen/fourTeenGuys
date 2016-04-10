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

        Cheese emptyCheese = new Cheese(0,0);
        emptyCheese.setEmptyCheese("Empty");
        cheeses.push(emptyCheese);

        //Set Bg
//        try {
//            bg = new GImage(ImageIO.read(getClass().getResource("/floor.jpg")));
//        } catch (IOException e) {}
//        bg.setSize(WIDTH, HEIGHT);
//        add(bg);

        mouse1 = new Mouse (100,100);
        add(mouse1.mouse);
        door1 = new Door(600,600);
        add(door1.door);


    }

    public void run (){



        while (numCheesesLeft>0 && numCheesesLeft<=10){
            System.out.println(mouse1.getX());
            System.out.println(mouse1.getY());

            GPoint direction = pathFinder(cheeses);
            double xMove = direction.getX()*2;
            double yMove = direction.getY()*2;

            mouse1.mouse.move(xMove, yMove);



            mouse1.setX(mouse1.getX() + direction.getX());
            mouse1.setY(mouse1.getY() + direction.getY());

            pause(20);
        }
    }



    public GPoint pathFinder(Stack<Cheese> cheeses){

        Stack newCheeses = new Stack <Cheese> ();

        Cheese emptyCheese = new Cheese(0,0);
        emptyCheese.setEmptyCheese("Empty");
        newCheeses.push(emptyCheese);


        //Final cheese-mouse vector
        double totalX = 0;
        double totalY = 0;

        //add up all cheese-mouse vectors
            while ((cheeses.peek().getEmptyCheese()).equals("Hi")) {
                Cheese chz = cheeses.pop();
                double vectorX = chz.getX() - mouse1.getX();
                double vectorY = chz.getY() - mouse1.getY();
                totalX += vectorX;
                totalY += vectorY;
                newCheeses.push(chz);
            }

        //Scale down to unit vector

        double length = distance(new GPoint(0,0), new GPoint(totalX,totalY));

        if (length==0){
            return new GPoint (0,0);
        }

        double finalX = totalX/length;
        double finalY = totalY/length;

        this.cheeses=newCheeses;

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


}

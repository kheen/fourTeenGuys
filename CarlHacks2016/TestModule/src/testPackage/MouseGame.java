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
    private Barrier barrier1;
    private Barrier barrier2;
    private Barrier barrier3;
    private Barrier barrier4;
    private Barrier barrier5;

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    public static int numCheesesLeft = 10;
    public GLabel cheeseLeft;

    public void init(){
        setSize(WIDTH, HEIGHT);
        addMouseListeners();
        cheeses = new Stack <Cheese> ();

        try {
            bg = new GImage(ImageIO.read(getClass().getResource("/floor.jpg")));
        } catch (IOException e) {}
        bg.setSize(WIDTH, HEIGHT);
        add(bg);

        barrier1 = new Barrier(0, 250, 320, 20);
        barrier2 = new Barrier(300, 350, 350, 110);
        barrier3 = new Barrier(200, 600, 240, 300);
        barrier4 = new Barrier(800, 200, 80, 400);
        barrier5 = new Barrier(500, 0, 80, 250);

        add(barrier1);
        add(barrier2);
        add(barrier3);
        add(barrier4);
        add(barrier5);


        mouse1 = new Mouse (600,600);
        add(mouse1.mouse);
        door1 = new Door(100,100);
        add(door1.door);
        cheeseLeft = new GLabel("You have " + numCheesesLeft + " Cheeses Left");
        cheeseLeft.setLocation(WIDTH-150,50);
        add(cheeseLeft);


    }

    public void run (){


        while (true){
//          System.out.println(mouse1.getX());
//          System.out.println(mouse1.getY());


            GPoint direction = pathFinder(cheeses);
            double xMove = direction.getX()*2;
            double yMove = direction.getY()*2;

            mouse1.mouse.move(xMove, yMove);


            mouse1.setX(mouse1.getX() + direction.getX() * 2);
            mouse1.setY(mouse1.getY() + direction.getY()*2);

            GPoint position = new GPoint (mouse1.getX() + direction.getX() * 2,mouse1.getY() + direction.getY()*2);

            if (hitsDoor(mouse1)) {
                endgame();
                break;
            }
            if (hitsBarrier(barrier1, mouse1) || hitsBarrier(barrier2, mouse1) || hitsBarrier(barrier3, mouse1) || hitsBarrier(barrier4, mouse1) || hitsBarrier(barrier5, mouse1)) {
                barrierResponse();
                break;
            }

            //Check for Cheese collisions

            if (getElementAt(position) != null && (getElementAt(position) != mouse1.mouse) && (getElementAt(position)!= door1.door)){
                System.out.println("Hello");

                Iterator <Cheese> iter = cheeses.iterator();

                while (iter.hasNext()){
                    Cheese c = iter.next();
                    if ((Math.abs(c.getX()-mouse1.mouse.getX())<40) && (Math.abs(c.getY()-mouse1.mouse.getY())<40)){
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
            cheeseLeft.setLabel("You have " + numCheesesLeft + " Cheeses Left");
        }

    }


    public boolean hitsDoor (Mouse mouse){
        double doorX = door1.getX();
        double doorY = door1.getY();

        double mouseX = mouse1.getX();
        double mouseY = mouse1.getY();

        //from the Right
        if (Math.abs(mouseX-doorX) < 40 && Math.abs(mouseY-doorY) < 40) {
            return true;
        }
        else{
            return false;
        }
    }

    public boolean hitsBarrier(Barrier barrier, Mouse mouse) {
        double startX = barrier.getX();
        double startY = barrier.getY();

        double endX = startX + barrier.getWidth();
        double endY = startY + barrier.getHeight();

        double startMouseX = mouse.mouse.getX();
        double startMouseY = mouse.mouse.getY();

        double endMouseX = startMouseX + mouse.mouse.getWidth();
        double endMouseY = startMouseY + mouse.mouse.getHeight();

        if ((endMouseX-5 > startX && startMouseX+5 < endX) && (endMouseY-5 > startY && startMouseY+5 < endY)){
            return true;
        }
        return false;
    }




    public void endgame() {
        GLabel endLabel = new GLabel("Congratulations!");
        endLabel.setLocation(WIDTH / 2, HEIGHT / 2);
        endLabel.setFont("Arial 40");
        add(endLabel);
    }

    public void barrierResponse(){
        GLabel endLabel = new GLabel ("GAME OVER. Go try League instead");
        endLabel.setLocation(WIDTH/2,HEIGHT/2);
        //endLabel.setFont(new Font("Arial", Font.BOLD, 36));
        add(endLabel);
    }

}

package testPackage;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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


    public void init(){
        setSize(WIDTH, HEIGHT);
        addMouseListeners();
        cheeses = new Stack <Cheese> ();

        Cheese emptyCheese = new Cheese(0,0);
        emptyCheese.setEmptyCheese("Empty");
        cheeses.push(emptyCheese);
        barrier1 = new Barrier(350, 80, 40, 315);
        barrier2 = new Barrier(50, 200, 40, 315);
        barrier3 = new Barrier(250, 150, 40, 315);
        barrier4 = new Barrier(400, 300, 40, 315);
        barrier5 = new Barrier(0, 0, 40, 315);
        add(barrier1);
        add(barrier2);
        add(barrier3);
        add(barrier4);
        add(barrier5);


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
            System.out.println(mouse1.getX());
            System.out.println(mouse1.getY());


            GPoint direction = pathFinder(cheeses);
            double xMove = direction.getX()*2;
            double yMove = direction.getY()*2;

            mouse1.mouse.move(xMove, yMove);


            mouse1.setX(mouse1.getX() + direction.getX());
            mouse1.setY(mouse1.getY() + direction.getY());

            if (hitsDoor(mouse1)){
                endgame();
                break;
            }
            if (hitsBarrier(barrier1, mouse1) || hitsBarrier(barrier2, mouse1) || hitsBarrier(barrier3, mouse1) || hitsBarrier(barrier4, mouse1) || hitsBarrier(barrier5, mouse1)) {
                barrierResponse();
                break;

            }
            pause(20);
        }
    }



    public GPoint pathFinder(Stack<Cheese> cheeses){

//        System.out.println("SDJKHFGKSDFJHGKSFDJHGSDFKJHGFSKJHGDFKJHSDFGKSDJFHG");
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
                double vectorX = (chz.getX() - mouse1.getX())/Math.pow(distance(mouse1.mouse.getLocation(),chz.cheese.getLocation()),3);
                double vectorY = (chz.getY() - mouse1.getY())/Math.pow(distance(mouse1.mouse.getLocation(),chz.cheese.getLocation()),3);
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


    public boolean hitsDoor(Mouse mouse) {
        double startX = door1.door.getX();
        double startY = door1.door.getY();

        double endX = startX + door1.door.getWidth();
        double endY = startY + door1.door.getHeight();

        double startMouseX = mouse1.mouse.getX();
        double startMouseY = mouse1.mouse.getY();

        double endMouseX = startMouseX + mouse1.mouse.getWidth();
        double endMouseY = startMouseY + mouse1.mouse.getHeight();

        //from the Right
        if ((endMouseX - 30 > startX && endMouseX + 30 < endX) && (endMouseY - 30 > startY && endMouseY + 30 < endY)) {
            return true;
        } else {
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

        if (((endMouseX > startX && endMouseX < endX) && (endMouseY > startY && endMouseY < endY)
                || (startMouseX < endX && startMouseX > startX) && (startMouseY < startY && startMouseY > endY))) {
            return true;
        }
        return false;
    }












    public void endgame() {
        GLabel endLabel = new GLabel("Congratulations!");
        endLabel.setLocation(WIDTH / 2, HEIGHT / 2);
        add(endLabel);
    }

    public void barrierResponse(){
        GLabel endLabel = new GLabel ("GAME OVER. Go try League instead");
        endLabel.setLocation(WIDTH/2,HEIGHT/2);
        add(endLabel);

    }

}

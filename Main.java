package sample;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.print.DocFlavor;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.*;
import javax.swing.*;
import java.awt.FlowLayout;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;


// Unfortunately, we could not get to figuring out the interface. We are uploading
// the Java code for the Start Menu and the JPEG of the other two screens we had in mind.
//Open it in Full Screen for maximum experience!!

public class Main {

    public static void main(String avg[]) throws Exception {
        BufferedImage img = ImageIO.read(new URL(
                "http://i.imgur.com/dOOiBu0.jpg"));
        ImageIcon icon = new ImageIcon(img);
        JFrame frame = new JFrame();

        frame.setContentPane(new JPanel() {
            BufferedImage image = ImageIO.read(new URL("http://i.imgur.com/dOOiBu0.jpg"));

            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 830, 10, 1500, 1500, this);

            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
        JToggleButton buttonone = new JToggleButton();
        buttonone.setLocation(0,-400);
        buttonone.setVisible(true);
        buttonone.setPreferredSize(new Dimension(570,635));
        buttonone.setText("PLAY");
        buttonone.setFont(new Font("Arial", Font.PLAIN, 100));
        buttonone.setBounds(0,-500,500,250);

        frame.add(buttonone);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        JToggleButton buttontwo = new JToggleButton();
        buttontwo.setVisible(true);
        buttontwo.setPreferredSize(new Dimension(530,635));
        buttontwo.setText("HOW TO PLAY");
        buttontwo.setFont(new Font("Arial", Font.PLAIN, 65));
        buttontwo.setBounds(0,-500,500,250);
        buttontwo.setLocation(0,-400);
        frame.add(buttontwo);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        JToggleButton buttonthree = new JToggleButton();
        buttonthree.setVisible(true);
        buttonthree.setPreferredSize(new Dimension(420,635));
        buttonthree.setText("ABOUT US");
        buttonthree.setFont(new Font("Arial", Font.PLAIN, 65));
        buttonthree.setBounds(0,-500,500,250);
        buttonthree.setLocation(0,-400);
        frame.add(buttonthree);



        //http://i.imgur.com/ic2Q9xu.png - How Do You Play
        //http://i.imgur.com/6BwYHUo.png - About Us



    }
    }




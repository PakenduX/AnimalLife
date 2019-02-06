package views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Panneau_option extends JPanel {


    public void paintComponent(Graphics g)
    {
        try {

            Image img3= ImageIO.read(new File("/home/pkss/AnimalLife/App/images/menu.jpg"));
            g.drawImage(img3, 0, 0,this.getWidth(),this.getHeight(),null);

        }catch(Exception e) {
            System.out.println("erreur d'image");
        }

    }
}

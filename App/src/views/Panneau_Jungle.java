package views;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class Panneau_Jungle extends JPanel {
 
	public void paintComponent(Graphics g)
	 {
		 try {
			
			 Image img3=ImageIO.read(this.getClass().getResource("/images/jungle.jpg"));
			 g.drawImage(img3, 0, 0,this.getWidth(),this.getHeight(),null);
			 
		 }catch(Exception e) {
			 System.out.println("erreur d'image");
		 }
		 
	 }
}

package views;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Dimension;

public class Panneau_animaux extends JPanel {
	private Image img;
	private int x=10;
	private int y=10;
	private Dimension dimension;
	private BufferedImage[] image = new BufferedImage[5];
	private BufferedImage[] image1 = new BufferedImage[1];
	private int i=1;
	
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	
	public Panneau_animaux()
	{
		this.dimension=new Dimension(100, 50);
	}
	
	public void Init() {
		for (i = 1; i <= 3; i++) {
			try {

				image[i] = ImageIO
						.read(new File("/home/pap-c/AnimalLife/App/images/pas_"+i+".png"));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void paintComponent(Graphics g)
	{
		
		try {
             
			g.drawImage(image[i], x, y, 100,50, null);
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}

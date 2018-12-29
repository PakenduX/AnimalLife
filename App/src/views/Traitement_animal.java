package views;
import models.Animal;
public class Traitement_animal implements Runnable {
	private Animal a;
	private Panneau_animaux pan_animal;
	private Panneau_Jungle Jungle;
	private int h;

	public void run() {

		for(;;)
		{
			h=pan_animal.getI();
			pan_animal.Init();
			for(int i=0;i<3;i++)
			{
				if(h==3)
				{
					h=1;

				}
				else
					h=h+1;
				System.out.println("l'image executée est "+h+"pour l'animale "+a+"a la position"+a.getPosition().getX()+" "+a.getPosition().getY());
				a.seDeplacer();
				pan_animal.setX(a.getPosition().getX());
				pan_animal.setY(a.getPosition().getY());
				pan_animal.repaint();
				pan_animal.setI(h);
				//Jungle.repaint();

				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
	}

	public Traitement_animal(Animal a,Panneau_animaux pan,Panneau_Jungle jungle) {
		super();
		this.a = a;
		this.pan_animal=pan;
		this.Jungle=jungle;
	}
}


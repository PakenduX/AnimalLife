package views;

import models.Animal;
import models.Herbivore;

import javax.swing.*;

/**
 * Cette classe permet de lancer le traitement
 * pour un herbivore (deplacement, mort ).
 */

public class TraitementHerbivore implements Runnable {
	private Animal<?> a;
	private JLabel animal;
	private Panneau_Jungle panneau;

	public TraitementHerbivore(Herbivore a, JLabel animal, Panneau_Jungle panneau){
		this.animal = animal;
		this.panneau = panneau;
		this.a = a;
	}

	public void run() {
		int j = 2;
		int i = 6;
		int t = 10;
		int k = 14;
		int x;
		x = (int)(Math.random()*3.1);

		while(true) {

			if(a.getDeplacementVersDroite()){
				if (i == 8)
					i = 5;
				else
					i++;
				animal.setIcon(new ImageIcon(this.getClass().getResource("/images/pas_"+i+".png")));
			}
			else {
				if (j == 4)
					j = 1;
				else
					j++;
				animal.setIcon(new ImageIcon(this.getClass().getResource("/images/pas_"+j+".png")));

			}

			if(a.estVivant() && x <= 1) {
				if(a.getDeplacementVersHaut()){
					if(t==12)
						t=10;
					else
						t++;

					animal.setIcon(new ImageIcon(this.getClass().getResource("/images/pas_"+t+".png")));

				}
				else{
					if(k==16)
						k=13;
					else
						k++;

					animal.setIcon(new ImageIcon(this.getClass().getResource("/images/pas_"+k+".png")));
				}
				a.seDeplacerHigh(panneau.getWidth(), panneau.getHeight());
				animal.setLocation(a.getPosition().getX(), a.getPosition().getY());
			} if(a.estVivant() && x > 1 && x <= 2){
				a.seDeplacerLeft(panneau.getWidth(), panneau.getHeight());
				animal.setLocation(a.getPosition().getX(), a.getPosition().getY());
			}
			if(a.estVivant() && x > 2 && x <= 3){
				a.seDeplacerRight(panneau.getWidth(), panneau.getHeight());
				animal.setLocation(a.getPosition().getX(), a.getPosition().getY());
			}

			if(!a.estVivant())
				animal.setLocation(-100, -100);

			panneau.repaint();

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


}


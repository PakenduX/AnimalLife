package views;

import controllers.Jeu;
import models.Animal;
import models.Herbivore;

import javax.swing.*;


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
		int i = 2;
		while(true) {
			if (i == 3)
				i = 1;
			else
				i++;

			animal.setIcon(new ImageIcon("/home/pkss/AnimalLife/App/images/pas_" + i + ".png"));
			a.seDeplacer(panneau.getWidth(), panneau.getHeight());
			Jeu.traiter_collisions();
			animal.setLocation(a.getPosition().getX(), a.getPosition().getY());
			panneau.repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


}


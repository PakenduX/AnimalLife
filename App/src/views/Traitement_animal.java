package views;

import controllers.Jeu;
import models.Animal;

import javax.swing.*;


public class Traitement_animal implements Runnable {
	private Animal<?> a;
	private JLabel animal;
	private Panneau_Jungle panneau;

	public Traitement_animal(Animal<?> a, JLabel animal, Panneau_Jungle panneau){
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
			animal.setIcon(new ImageIcon("/home/pap-c/AnimalLife/App/images/pas_" + i + ".png"));
			a.seDeplacer();
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


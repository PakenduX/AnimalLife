package controllers;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

import models.*;
import views.Jungle;
import views.Options;
import views.TraitementCarnivore;

import javax.swing.*;

/**
 * La classe qui permet de lancer le jeu
 */
public class Jeu {

	static Jungle jungle = new Jungle();

	/**
	 * La fonction qui va traiter la collision entre les différents animaux
	 * de la jungle.
	 */
	public static void traiter_collisions() throws IOException {

		ArrayList<Animal<?>> coll_anim = Animal.getCollec_anim();
		ArrayList<Herbe> coll_herb = Herbe.getColl_herb();
		Animal<?> ani;
		Animal<?> anj;
		// Ici on traite la collision entre animaux.
		// Pour chaque animal de la collection
		for(int i = 0; i < coll_anim.size(); i++) {
			ani = coll_anim.get(i);
			//On teste son éventuelle collision avec tous les autres.
			for(int j = 0; j < coll_anim.size(); j++) {
				anj = coll_anim.get(j);
				if(ani.collision(anj)) {
					if(ani.meme_espece(anj)) {
						if (!ani.meme_sexe(anj)) {
							if (ani.adult() && anj.adult()) {
								Animal<?> fils = (Animal<?>) ani.seReproduire();
								fils.setPosition(new Position((int)Math.random()*Toolkit.getDefaultToolkit().getScreenSize().width, (int)Math.random()*Toolkit.getDefaultToolkit().getScreenSize().height));
								ani.setChild(fils);
								ani.setHasChild(true);
							}

							//Les deux animaux sont de même sexe.
						} else {
							if (ani.getAge() > anj.getAge())
								anj.mourrir(j);
							else if (ani.getAge() < anj.getAge())
								ani.mourrir(i);
						}
						//Les deux animaux ne sont pas de la même espèce
					}else{
						if(ani.isCarnivore()){
							if(anj.isHerbivore())
								ani.seNourrir(anj);
							else
								anj.seNourrir(ani);
						}else if(ani.isHerbivore()){
							if(anj.isCarnivore())
								anj.seNourrir(ani);
							else
								ani.seNourrir(anj);
						}else{
							if(anj.isCarnivore())
								ani.seNourrir(anj);
							else
								anj.seNourrir(ani);
						}
					}
				}
			}
		}
		//Ici on va traiter la collision des animaux avec l'eau et l'herbe.
		Animal<?> a;
		Herbe h;
		for(int i = 0; i < coll_anim.size(); i++) {
			a = coll_anim.get(i);
			for (int j = 0; j < coll_herb.size(); j++){
				h = coll_herb.get(j);
				if(a.proche_nourriture(h.getPosition())) {
					if (a.isHerbivore())
						((Herbivore) a).seNourrir(h);
					else if (a.isOmnivore())
						((Omnivore) a).seNourrir(h);
				}
			}

			if(a.proche_nourriture(Eau.getPosition())) {
				a.boire();
				if (Eau.getQuantite() == 0)
					Eau.disparaitre();
			}

		}

	}
	
	public void lancer() {
		//On crée les différents éléments du jeu.
		for(int i = 0; i < Options.getNbCarn(); i++)
			new Carnivore();
		for(int i = 0; i < Options.getNbHerbv(); i++)
			new Herbivore();
		for(int i = 0; i < Options.getNbOmn(); i++)
			new Omnivore();

		//Les herbes sont placées de façon aléatoire dans la jungle
		for (int i = 0; i < Options.getQHerbN(); i++)
			new HerbeNormale().setPosition(new Position((int)(Math.random()*(jungle.getWidth())), (int)(Math.random()*(jungle.getHeight()))));

		for (int i = 0; i < Options.getQHerbE(); i++)
			new HerbeEmpoisonnee().setPosition(new Position((int)(Math.random()*(jungle.getWidth())), (int)(Math.random()*(jungle.getHeight()))));

		ArrayList<Animal<?>> coll_anim = Animal.getCollec_anim();

		for (int i = 0; i < coll_anim.size(); i++)
			jungle.placer_animal(coll_anim.get(i));

		jungle.deplacement();

	}

}

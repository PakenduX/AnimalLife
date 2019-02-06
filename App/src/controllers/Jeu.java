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
	static BufferedWriter bw = null;

	{
		try {
			bw = new BufferedWriter(new FileWriter(new File("/home/pkss/AnimalLife/retour.txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * La fonction qui va traiter la collision entre les différents animaux
	 * de la jungle.
	 */
	public static void traiter_collisions() throws IOException {

		ArrayList<Animal<?>> coll_anim = Animal.getCollec_anim();
		ArrayList<Herbe> coll_herb = Herbe.getColl_herb();
		int quantite_o = Eau.getQuantite();
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
					bw.write("Collision détectée");
					bw.newLine();
					if(ani.meme_espece(anj)) {
						bw.write("Meme espece");
						bw.newLine();
						bw.write("sexe de ani = " + ani.getSexe() + " et sexe de anj = " + anj.getSexe());
						bw.newLine();
						if (!ani.meme_sexe(anj)) {
							bw.write(" Pas même sexe");
							bw.newLine();
							if (ani.adult() && anj.adult()) {
								bw.write(" les deux sont adultes");
								bw.newLine();
								Animal<?> fils = (Animal<?>) ani.seReproduire();
								fils.setPosition(new Position((int)Math.random()*Toolkit.getDefaultToolkit().getScreenSize().width, (int)Math.random()*Toolkit.getDefaultToolkit().getScreenSize().height));
								ani.setChild(fils);
								ani.setHasChild(true);
								bw.write("Reproduction");
								bw.write("Taille de la collection = "+ coll_anim.size());

							}else {
								bw.write("Pas adult");
								bw.newLine();
							}

							//Les deux animaux sont de même sexe.
						} else {
							if (ani.getAge() > anj.getAge()) {
								anj.mourrir(j);
								bw.write("ani est plus agé age = "+ani.getAge());
							}else if (ani.getAge() < anj.getAge()) {
								ani.mourrir(i);
								bw.write("anj est plus agé age = "+anj.getAge());
							}
							bw.newLine();
						}
						//Les deux animaux ne sont pas de la même espèce
					}else{
						if(ani.isCarnivore()){
							if(anj.isHerbivore()) {
								ani.seNourrir(anj);
								bw.write("La position de celui qui a mangé est x = "+ani.getPosition().getX() + " y = "+ani.getPosition().getY());
							}else {
								anj.seNourrir(ani);
								bw.write("La position de celui qui a mangé est x = "+anj.getPosition().getX() + " y = "+ani.getPosition().getY());
							}
							bw.newLine();
						}else if(ani.isHerbivore()){
							if(anj.isCarnivore()) {
								anj.seNourrir(ani);
								bw.write("La position de celui qui a mangé est x = "+anj.getPosition().getX() + " y = "+ani.getPosition().getY());
							}else {
								ani.seNourrir(anj);
								bw.write("anj est etre omnivore, il est "+anj.getClass().getName());
								bw.newLine();
								bw.write("La position de celui qui a mangé est x = "+ani.getPosition().getX() + " y = "+ani.getPosition().getY());
							}
						}else{
							if(anj.isCarnivore()) {
								ani.seNourrir(anj);
								bw.write("La position de celui qui a mangé est x = "+ani.getPosition().getX() + " y = "+ani.getPosition().getY());
							}else {
								anj.seNourrir(ani);
								bw.write("ani est etre omnivore, il est "+ani.getClass().getName());
								bw.newLine();
								bw.write("La position de celui qui a mangé est x = "+anj.getPosition().getX() + " y = "+ani.getPosition().getY());
							}
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
				bw.write("Boire quantit = " +Eau.getQuantite());
				if (Eau.getQuantite() == 0)
					Eau.disparaitre();
			}

		}

	}
	
	public void lancer() {
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

		//Eau.setPosition(new Position(960, 540));
		//Eau.setQuantite(100);
		//Eau.setEau_is_suffisant(true);

		ArrayList<Animal<?>> coll_anim = Animal.getCollec_anim();

		for (int i = 0; i < coll_anim.size(); i++)
			jungle.placer_animal(coll_anim.get(i));

		jungle.deplacement();

	}

}

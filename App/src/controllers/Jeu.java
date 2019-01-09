package controllers;

import java.util.ArrayList;

import models.*;
import views.Jungle;
import views.Options;

import javax.swing.*;

public class Jeu {

	static Jungle jungle = new Jungle();

	/**
	 * La fonction qui va traiter la collision entre les différents animaux
	 * de la jungle.
	 */
	public static void traiter_collisions() {
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
			for(int j = 1; j < coll_anim.size(); j++) {
				anj = coll_anim.get(j);
				System.out.println("La taille de la collection = "+ Animal.getCollec_anim().size());
				if(ani.collision(anj)) {
					System.out.println("Collision détectée");
					if(ani.meme_espece(anj)) {
						System.out.println("Meme espece");
						System.out.println("sexe de ani = " + ani.getSexe() + " et sexe de anj = " + anj.getSexe());
						if (!ani.meme_sexe(anj)) {
							System.out.println("Pas même sexe");
							if (ani.adult() && anj.adult()) {
								System.out.println("les deux sont adultes");
								Animal<?> fils = (Animal<?>) ani.seReproduire();
								fils.setPosition(new Position(ani.getPosition().getX() + 20, ani.getPosition().getY() + 10));
								if (fils instanceof Carnivore)
									System.out.println("C'est un type Carnivore");
								else if (fils instanceof Herbivore)
									System.out.println("C'est un type Herbivore");
								else
									System.out.println("C'est un type Omnivore");

							}
							//Les deux animaux sont de même sexe.
						} else {
							if (ani.getAge() > anj.getAge()) {
								anj.mourrir(j);
								System.out.println("ani est plus agé age = "+ani.getAge());
                                System.out.println("La taille de la collection = "+ Animal.getCollec_anim().size());
							}else if (ani.getAge() < anj.getAge()) {
								ani.mourrir(i);
								System.out.println("anj est plus agé age = "+anj.getAge());
                                System.out.println("La taille de la collection = "+ Animal.getCollec_anim().size());
							}
						}
						//Les deux animaux ne sont pas de la même espèce
					}else{
						if(ani.isCarnivore()){
							if(anj.isHerbivore()) {
								ani.seNourrir(anj);
								System.out.println("La position de celui qui a mangé est x = "+ani.getPosition().getX() + " y = "+ani.getPosition().getY());
							}else {
								anj.seNourrir(ani);
								System.out.println("La taille de la collection = "+ Animal.getCollec_anim());
								System.out.println("La position de celui qui a mangé est x = "+anj.getPosition().getX() + " y = "+ani.getPosition().getY());
							}
						}else if(ani.isHerbivore()){
							if(anj.isCarnivore()) {
								anj.seNourrir(ani);
								System.out.println("La position de celui qui a mangé est x = "+anj.getPosition().getX() + " y = "+ani.getPosition().getY());
							}else {
								ani.seNourrir(anj);
								System.out.println("anj est etre omnivore, il est "+anj.getClass().getName());
								System.out.println("La position de celui qui a mangé est x = "+ani.getPosition().getX() + " y = "+ani.getPosition().getY());
							}
                            System.out.println("La taille de la collection = "+ Animal.getCollec_anim().size());
						}else{
							if(anj.isCarnivore()) {
								ani.seNourrir(anj);
								System.out.println("La position de celui qui a mangé est x = "+ani.getPosition().getX() + " y = "+ani.getPosition().getY());
							}else {
								anj.seNourrir(ani);
								System.out.println("ani est etre omnivore, il est "+ani.getClass().getName());
								System.out.println("La position de celui qui a mangé est x = "+anj.getPosition().getX() + " y = "+ani.getPosition().getY());
							}
                            System.out.println("La taille de la collection = "+ Animal.getCollec_anim().size());
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
				if(a.proche_nourriture(h.getPosition())){
					if(a.isHerbivore())
						((Herbivore)a).seNourrir(h);
					else if(a.isOmnivore())
						((Omnivore)a).seNourrir(h);
				}else if(a.proche_nourriture(Eau.getPosition()))
					a.boire();
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

		Eau.setPosition(new Position(100, 50));

		ArrayList<Animal<?>> coll_anim = Animal.getCollec_anim();

		for (int i = 0; i < coll_anim.size(); i++)
			jungle.placer_animal(coll_anim.get(i));

		jungle.deplacement();

	}

}

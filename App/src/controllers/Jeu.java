package controllers;

import java.util.ArrayList;
import java.util.Iterator;

import models.*;

public class Jeu {

	/**
	 * La fonction qui va traiter la collision entre les différents animaux
	 * de la jungle.
	 */
	public void traiter_collisions() {
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
					if(ani.meme_espece(anj)) {
						System.out.println("Collision détectée");
						System.out.println("sexe de ani = " + ani.getSexe() + " et sexe de anj = " + anj.getSexe());
						if (!ani.meme_sexe(anj)) {
							if (ani.adult() && anj.adult()) {
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
                                System.out.println("La taille de la collection = "+ Animal.getCollec_anim());
							}else if (ani.getAge() < anj.getAge()) {
								anj.mourrir(i);
								System.out.println("anj est plus agé age = "+anj.getAge());
                                System.out.println("La taille de la collection = "+ Animal.getCollec_anim());
							}
						}
						//Les deux animaux ne sont pas de la même espèce
					}else{
						if(ani.isCarnivore()){
							if(anj.isHerbivore())
								ani.seNourrir(anj);
							else
								anj.seNourrir(ani);
                            System.out.println("La taille de la collection = "+ Animal.getCollec_anim());
						}else if(ani.isHerbivore()){
							if(anj.isCarnivore())
								anj.seNourrir(ani);
							else {
								ani.seNourrir(anj);
								System.out.println("anj est etre omnivore, il est "+anj.getClass().getName());
							}
                            System.out.println("La taille de la collection = "+ Animal.getCollec_anim().size());
						}else{
							if(anj.isCarnivore())
								ani.seNourrir(anj);
							else {
								anj.seNourrir(ani);
								System.out.println("ani est etre omnivore, il est "+ani.getClass().getName());
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
		Animal<Carnivore> carn = new Carnivore();
		carn.setPosition(new Position(20, 9));
		Animal<Herbivore> herb = new Herbivore();
		herb.setPosition(new Position(10, 4));
		Animal<Omnivore> omn = new Omnivore();
		omn.setPosition(new Position(15, 10));

		Animal<Carnivore> carn2 = new Carnivore();
		carn2.setPosition(new Position(55, 5));
		Animal<Herbivore> herb2 = new Herbivore();
		herb2.setPosition(new Position(27, 1));
		Animal<Omnivore> omn2 = new Omnivore();
		omn2.setPosition(new Position(13, 30));

		Animal<Carnivore> carn3 = new Carnivore();
		carn3.setPosition(new Position(20, 23));
		Animal<Herbivore> herb3 = new Herbivore();
		herb3.setPosition(new Position(29, 39));
		Animal<Omnivore> omn3 = new Omnivore();
		herb3.setPosition(new Position(45, 40));

		Animal<Carnivore> carn4 = new Carnivore();
		carn4.setPosition(new Position(10, 5));
		Animal<Herbivore> herb4 = new Herbivore();
		herb4.setPosition(new Position(11, 14));
		Animal<Omnivore> omn4 = new Omnivore();
		omn4.setPosition(new Position(5, 9));

		Eau.setPosition(new Position(2, 5));
		Herbe h1 = new HerbeNormale();
		h1.setPosition(new Position(1, 1));
		Herbe h2 = new HerbeNormale();
		h2.setPosition(new Position(1, 2));
		Herbe h3 = new HerbeEmpoisonnee();
		h3.setPosition(new Position(3, 3));
		Herbe h4 = new HerbeEmpoisonnee();
		h4.setPosition(new Position(2, 1));
		
		int i = 0;
		while(i < 10) {
			if (carn.estVivant())
				carn.seDeplacer();
			if (herb.estVivant())
				herb.seDeplacer();
			if (omn.estVivant())
				omn.seDeplacer();

			if (carn2.estVivant())
				carn2.seDeplacer();
			if (herb2.estVivant())
				herb2.seDeplacer();
			if (omn2.estVivant())
				omn2.seDeplacer();

			if (carn3.estVivant())
				carn3.seDeplacer();
			if (herb3.estVivant())
				herb3.seDeplacer();
			if (omn3.estVivant())
				omn3.seDeplacer();

			if (carn4.estVivant())
				carn4.seDeplacer();
			if (herb4.estVivant())
				herb4.seDeplacer();
			if (omn4.estVivant())
				omn4.seDeplacer();

			traiter_collisions();
			i++;
		}
	}

}

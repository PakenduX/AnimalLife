package controllers;

import java.util.ArrayList;
import java.util.Iterator;

import models.Animal;
import models.Carnivore;
import models.Herbivore;
import models.Omnivore;
import models.Position;

public class Jeu {
	
	ArrayList<Animal<?>> coll_anim = Animal.getCollec_anim();
	Iterator<Animal<?>> it = coll_anim.iterator();
	
	/**
	 * La fonction qui va traiter la collision entre les différents animaux
	 * de la jungle.
	 */
	public void traiter_collisions() {
		Animal<?> ani;
		Animal<?> anj;
		// Pour chaque animal de la collection
		for(int i = 0; i < coll_anim.size(); i++) {
			 ani = coll_anim.get(i);
			 //On teste son éventuelle collision avec tous les autres.
			for(int j = 1; j < coll_anim.size(); j++) {
				anj = coll_anim.get(j);
				if(ani.collision(anj)) {
					System.out.println("Collision détectée");
					System.out.println("sexe de ani = "+ ani.getSexe() + " et sexe de anj = "+ anj.getSexe());
					if(!ani.meme_sexe(anj)) {
						if(ani.adult() && anj.adult()) {
							Animal<?> fils = (Animal<?>) ani.seReproduire();
							fils.setPosition(new Position(ani.getPosition().getX() + 1, ani.getPosition().getY() + 1));
							if(fils instanceof Carnivore)
								System.out.println("C'est un type Carnivore");
							else if(fils instanceof Herbivore)
								System.out.println("C'est un type Herbivore");
							else
								System.out.println("C'est un type Omnivore");
							
						}
					}else {
						if(ani.getAge() > anj.getAge())
							anj.mourrir(j);
						else if(ani.getAge() < anj.getAge())
							anj.mourrir(i);
					}
				}
			}
		}
		
	}
	
	public void lancer() {
		Animal<Carnivore> carn = new Carnivore();
		Animal<Herbivore> herb = new Herbivore();
		Animal<Omnivore> omn = new Omnivore();
		
		int i = 0;
		while(i < 10) {
			carn.seDeplacer();
			herb.seDeplacer();
			omn.seDeplacer();
			traiter_collisions();
			i++;
		}
	}
	public static void main(String[] args) {
		new Jeu().lancer();
	}

}

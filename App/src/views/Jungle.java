package views;

import javax.swing.*;

import controllers.Jeu;
import models.*;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Jungle extends JFrame {

	private Panneau_Jungle panneau = new Panneau_Jungle();

	private ArrayList<Animal<?>> coll_anim;
	private ArrayList<Herbe> coll_herb;

	/*
	 * la fenetre du jeu pour initialiser
	 */
	public Jungle() {

		setTitle("Jungle");
		setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		panneau.setLayout(null);
		this.setContentPane(panneau);
		setVisible(true);

	}
	/*
	 * la fonction qui ajoute le nouvel animal dans la fenetre du jeu
	 */
	public void ajouter(JLabel animal, int x, int y) {
		animal.setBounds(x, y, 100, 70);
		panneau.add(animal);
	}

	public void ajouterHerbe(JLabel herb, int x, int y){
		herb.setBounds(x, y, 100, 120);
		panneau.add(herb);
	}
	public void ajouterEau(JLabel eau, int x, int y){
		eau.setBounds(x, y, 100, 120);
		panneau.add(eau);
	}

	/**
	 * Cette fonction va permettre de traiter la reproduction des animaux
	 *
	 */

	public void traitementReproduction(){
		Animal<?> ani;
		Animal<?> fils;
		JLabel animal_label;
		for(int i = 0; i < coll_anim.size(); i++){
			animal_label = new JLabel(new ImageIcon("/home/pkss/AnimalLife/App/images/pas_1.png"));
			ani = coll_anim.get(i);
			if(ani.estVivant() && ani.getHasChild()){
				fils = ani.getChild();
				ajouter(animal_label, fils.getPosition().getX(), fils.getPosition().getY());
				if (fils.isHerbivore())
					new Thread(new TraitementHerbivore((Herbivore) fils, animal_label, panneau), "TraitementHerbivore").start();
				else if (fils.isCarnivore())
					new Thread(new TraitementCarnivore((Carnivore) fils, animal_label, panneau), "TraitementCarnivore").start();
				else
					new Thread(new TraitementOmnivore((Omnivore) fils, animal_label, panneau), "TraitementOmnivore").start();

				ani.setHasChild(false);
			}
		}
	}

	/**
	 * La fonction lance le traitement des déplacements des animaux
	 * dans la jungle.
	 */
	public void deplacement() {
		Animal<?> ani_courant;
		Herbe h;
		JLabel animal_label;
		JLabel herb_label;
		JLabel eau_label;
		coll_anim = Animal.getCollec_anim();
		coll_herb = Herbe.getColl_herb();

		eau_label = new JLabel();
		ajouterEau(eau_label, Eau.getPosition().getX(), Eau.getPosition().getY());
		new Thread(new TraitementEau(eau_label, panneau)).start();

		for (int i = 0; i < coll_herb.size(); i++){
			h = coll_herb.get(i);
			herb_label = new JLabel();
			ajouterHerbe(herb_label, h.getPosition().getX(), h.getPosition().getY());
			if(h.isNormal())
				new Thread(new TraitementHerbeNormal(h,herb_label,panneau)).start();
			else
				new Thread(new TraitementHerbeEmpois(h,herb_label,panneau)).start();

		}

		new Thread(() -> {
			while (true)
				traitementReproduction();
		}).start();

		new Thread(() -> {
			try {
				while (true)
					Jeu.traiter_collisions();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();

		for (int j = 0; j < coll_anim.size(); j++) {
			System.out.println("La taille de la collection dans deplacer de jungle = " + coll_anim.size());
			ani_courant = coll_anim.get(j);
			animal_label = new JLabel(new ImageIcon("/home/pkss/AnimalLife/App/images/pas_1.png"));
			ajouter(animal_label, ani_courant.getPosition().getX(), ani_courant.getPosition().getY());

			if (ani_courant.estVivant()) {
				if (ani_courant.isHerbivore())
					new Thread(new TraitementHerbivore((Herbivore) ani_courant, animal_label, panneau), "TraitementHerbivore").start();
				else if (ani_courant.isCarnivore())
					new Thread(new TraitementCarnivore((Carnivore) ani_courant, animal_label, panneau), "TraitementCarnivore").start();
				else
					new Thread(new TraitementOmnivore((Omnivore) ani_courant, animal_label, panneau), "TraitementOmnivore").start();

			}

		}

	}

	/**
	 * Cette fonction permet de placer les animaux
	 * à leur postion initiale qu'on a définie pour chaque
	 * espèce.
	 *
	 * Les carnivores sont placés dans le tier gauche de la jungle
	 * les herbivores au tier milieu et les omnivores dans le tier droit.
	 * @param a l'animal à placer.
	 */
	public void placer_animal(Animal<?> a){
		int x;
		int y = (int)(Math.random()*(this.getHeight()));

		if(a.isCarnivore()) {
			x = (int) (Math.random() * (this.getWidth() / 3));
			System.out.println("Dans placer animal postion carni= " +x + " "+ y);
		}else if(a.isHerbivore()) {
			x = (int) ((this.getWidth() / 3) + Math.random() * (this.getWidth() * 2 / 3 - this.getWidth() / 3));
			System.out.println("Dans placer animal postion herbi= " +x + " "+ y);
		}else {
			x = (int) ((this.getWidth() * 2 / 3) + Math.random() * (this.getWidth() - this.getWidth() * 2 / 3));
			System.out.println("Dans placer animal postion omni= " +x + " "+ y);
		}

		a.setPosition(new Position(x, y));
	}


}
package views;

import javax.swing.*;
import models.Animal;
import models.Position;

import java.awt.*;
import java.util.ArrayList;

public class Jungle extends JFrame {
	private Panneau_Jungle panneau = new Panneau_Jungle();

	private ArrayList<Animal<?>> coll_anim;

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
	 * la fonction qui ajoute le nouveau élement dans la fenetre du jeu
	 */
	public void ajouter(JLabel animal, int x, int y) {
		animal.setBounds(x, y, 80, 50);
		panneau.add(animal);

		try {
			Thread.sleep(100);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public void deplacer() {

		coll_anim = Animal.getCollec_anim();
		Animal<?> ani_courant ;
		JLabel animal_label;
		for(int j = 0; j < coll_anim.size(); j++){
			ani_courant = coll_anim.get(j);
			animal_label = new JLabel(new ImageIcon("/home/pap-c/AnimalLife/App/images/pas_1.png"));
			if(ani_courant.estVivant()) {
				ajouter(animal_label, ani_courant.getPosition().getX(), ani_courant.getPosition().getY());
				new Thread(new Traitement_animal(ani_courant, animal_label, panneau)).start();
			}

		}
	}

	public void placer_animal(Animal<?> a, JLabel animal_icon){
		int x;
		int y = (int)(Math.random()*(this.getHeight()));

		if(a.isCarnivore())
			x = (int)(Math.random()*(this.getWidth()/3));
		else if(a.isHerbivore())
			x = (int)((this.getWidth()/3) + Math.random()*(this.getWidth()*2/3));
		else
			x = (int)((this.getWidth()*2/3) + Math.random()*(this.getWidth()));

		a.setPosition(new Position(x, y));
		ajouter(animal_icon, x, y);
	}

}
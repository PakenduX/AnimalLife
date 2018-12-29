package views;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import controllers.Jeu;
import models.Animal;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;

public class Jungle extends JFrame {
	private Panneau_Jungle panneau = new Panneau_Jungle();
	private Panneau_animaux pan_animal = new Panneau_animaux();
	private int i, h;
	private BufferedImage[] image = new BufferedImage[5];
	private Traitement_animal traitement;
	/*
	 * la fenetre du jeu pour initialiser
	 */
	public Jungle() {

		setTitle("Jungle");
		setBounds(0, 0, 1000, 1000);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		pan_animal.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
		panneau.add(pan_animal);
		this.setContentPane(panneau);
		this.setVisible(true);

	}
	/*
	 * la fonction qui ajoute le nouveau élement dans la fenetre du jeu
	 */
	public void ajouter(int x, int y) {
		// this.repaint();
		System.out.println("j'ai ajouter un element"+Animal.getCollec_anim().get(i));
		pan_animal.setX(x);
		pan_animal.setY(y);
		System.out.println("le x" + pan_animal.getX());
		System.out.println("le y" + pan_animal.getY());

		this.getContentPane().add(pan_animal).repaint();
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void supprimer(Animal<?> a) {

	}

	public void afficher() {
		int i = 0;
		while (i < Animal.getCollec_anim().size()) {
			System.out.println("le nombre d'element dans la jungle" + Animal.getCollec_anim().size());
			ajouter(Animal.getCollec_anim().get(i).getPosition().getX(),
					Animal.getCollec_anim().get(i).getPosition().getY());
			i++;
		}
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public void deplacer() {


		Runnable runnable=new Traitement_animal(Animal.getCollec_anim().get(i),pan_animal,panneau) ;
		i = 0;

		for(;;)
		{
			//this.getContentPane().repaint();
			while (i < Animal.getCollec_anim().size()) {

				System.out.println("la taille de la collection d'element" + Animal.getCollec_anim().size());
				ajouter(Animal.getCollec_anim().get(i).getPosition().getX(),
						Animal.getCollec_anim().get(i).getPosition().getY());
				pan_animal.setX(Animal.getCollec_anim().get(i).getPosition().getX());
				pan_animal.setY(Animal.getCollec_anim().get(i).getPosition().getY());

				Thread t=new Thread(new Traitement_animal(Animal.getCollec_anim().get(i),pan_animal,panneau));
				t.start();

				i++;
				System.out.println("je suis a l'index "+i);

			}
			//this.getContentPane().repaint();
			try {
				this.getContentPane().repaint();
				Thread.sleep(200);
			}catch(Exception e) {

			}

		}

	}
	public static void main(String[] args) {
		Jeu j = new Jeu();
		Jungle jungle=new Jungle();
		j.lancer();
		jungle.deplacer();

	}
}
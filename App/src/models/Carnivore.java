package models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * La classe représentant un carnivore
 * @author Équipe Jungle
 *
 */
public class Carnivore extends Animal<Carnivore>{

	boolean ydecrement = false;
	boolean yincrement = false;

	BufferedWriter bw;

	public Carnivore() {
		super();
		this.position = new Position(19, 1);
		try {
			bw = new BufferedWriter(new FileWriter(new File("/home/pkss/AnimalLife/deplacement_carni.txt")));
		}catch (IOException e){}
	}

	/**
	 * Cette fonction permet au carnivore de se nourrir
	 * de l'herbivore qui meurt après.
	 * @param a
	 */
	public void seNourrir(Animal<?> a) {
		this.age += 5;
		a.mourrir(a.getIndex());
		System.out.println("Carnivore se nourrit de "+ a.getClass().getName() + " son age vaut = "+ this.age);
	}

	public void seNourrir(EtreVivant<?> ev) {}

	public void seDeplacer(int xMax, int yMax) {
		int x = this.getPosition().getX();
		int y = this.getPosition().getY();

		if(ydecrement)
			this.position.setY(this.position.getY() - OFFSET);

		if(yincrement)
			this.position.setY(this.position.getY() + 20);

		if(x <= 0 && y <= 0){
			this.position.setX(this.position.getX() + OFFSET);
			deplacementVersDroite = true;
			deplacementVersGauche = false;
			yincrement = true;
			ydecrement = false;
		}else if( x > 0 && y <= 0){
			ydecrement = false;
			yincrement = true;
			deplacementVersDroite = true;
			deplacementVersGauche = false;
			this.position.setX(this.position.getX() + OFFSET);
		}else if(y > 0 && x <= 0){

			this.position.setX(this.position.getX() + OFFSET);
			deplacementVersDroite = true;
			deplacementVersGauche = false;
			if(y > Math.abs(y - yMax))
				ydecrement = true;
			else
				yincrement = true;

		}else if(x >= xMax && y >= yMax){
			ydecrement = true;
			yincrement = false;
			deplacementVersGauche = true;
			deplacementVersDroite = false;
			this.position.setX(this.position.getX() + OFFSET);

		}else if(x < xMax && y >= yMax){
			ydecrement = false;
			yincrement = true;
			if(deplacementVersDroite)
				this.position.setX(this.position.getX() + OFFSET);
			else
				this.position.setX(this.position.getX() - OFFSET);

		}else if(x >= xMax && y < yMax){
			if(y > Math.abs(y - yMax))
				ydecrement = true;
			else
				yincrement = true;
			deplacementVersGauche = true;
			deplacementVersDroite = false;
			this.position.setX(this.position.getX() - OFFSET);

		}else{
			if(deplacementVersDroite)
				this.position.setX(this.position.getX() + OFFSET);
			else
				this.position.setX(this.position.getX() - OFFSET);

			yincrement = false;
			ydecrement = false;

			try {
				bw.write(x + " " + y + ", ");
				bw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	public Carnivore seReproduire() {
		return new Carnivore();
	}

}

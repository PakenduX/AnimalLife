package models;

/**
 * La classe représentant un carnivore
 * @author Équipe Jungle
 *
 */
public class Carnivore extends Animal<Carnivore>{

	public Carnivore() {
		super();
		this.position = new Position(19, 1);
	}

	/**
	 * Cette fonction permet au carnivore de se nourrir
	 * de l'herbivore qui meurt après.
	 * @param a
	 */
	public void seNourrir(Animal<?> a) {
		this.age++;
		a.mourrir(a.getIndex());
		System.out.println("Carnivore se nourrit de "+ a.getClass().getName() + " son age vaut = "+ this.age);
	}

	public void seNourrir(EtreVivant<?> ev) {

	}

	public void seDeplacer() {
		this.position.setX(this.position.getX() + 10);
		this.position.setY(this.position.getY() + 1);
		System.out.println("Deplacement du carnivore x = " + this.position.getX() + " y = "+ this.position.getY());
	}

	public Carnivore seReproduire() {
		return new Carnivore();
		
	}

}

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
		this.age += 5;
		a.mourrir(a.getIndex());
	}

	public void seNourrir(EtreVivant<?> ev) {}


	public Carnivore seReproduire() {
		return new Carnivore();
	}

}

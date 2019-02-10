package models;

/**
 * La classe représentant un Herbivore
 * @author Équipe Jungle
 *
 */
public class Herbivore extends Animal<Herbivore>{

	public Herbivore() {
		super();
		this.position = new Position(1, 2);
	}

	/**
	 * Herbivore se nourrit d'herbes, quand il mange une
	 * herbre empoisonnée il meurt.
	 * @param herb l'herbe à brouter.
	 */
	public void seNourrir(Herbe herb) {
		if(herb instanceof HerbeNormale)
			this.age += 5;
		else
			this.mourrir(this.getIndex());

		herb.disparaitre(herb.getIndex());
	}

	public void seNourrir(EtreVivant<?> ev) {}

	public void seNourrir(Animal<?> omn) {
		this.age += 5;
		omn.mourrir(omn.getIndex());
	}

	public Herbivore seReproduire() {
		return new Herbivore();
	}
	
}

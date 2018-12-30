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
			this.age++;
		else
			this.mourrir(this.getIndex());

		herb.disparaitre(herb.getIndex());

		System.out.println("Herbivore se nourrit de "+ herb.getClass().getName());
	}

	public void seNourrir(EtreVivant<?> ev) {}

	public void seNourrir(Animal<?> omn) {
		this.age++;
		omn.mourrir(omn.getIndex());
		System.out.println("Herbivore se nourrit de "+ omn.getClass().getName() + " son age vaut = "+ this.age);
	}

	public void seDeplacer() {
		this.position.setX(this.position.getX() + 5);
		this.position.setY(this.position.getY());
		System.out.println("Deplacement du Herbivore x = " + this.position.getX() + " y = "+ this.position.getY());
	}

	public Herbivore seReproduire() {
		return new Herbivore();
	}
	
}

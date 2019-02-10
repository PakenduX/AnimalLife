package models;

/**
 * La classe représentant un Omnivore
 * @author Équipe Jungle
 *
 */
public class Omnivore extends Animal<Omnivore>{

	public Omnivore() {
		super();
		this.position = new Position(2, 2);
	}

	public void seNourrir(Animal<?> carn) {
		this.age++;
		carn.mourrir(carn.getIndex());
	}

	public void seNourrir(Herbe herb){
		if(herb instanceof HerbeNormale)
			this.age += 5;
		else
			this.mourrir(this.getIndex());

		herb.disparaitre(herb.getIndex());
	}

	public void seNourrir(EtreVivant<?> ev) {}

	public Omnivore seReproduire() {
		return new Omnivore();
	}

}

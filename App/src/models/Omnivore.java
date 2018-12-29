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
		System.out.println("Omnivore se nourrit de "+ carn.getClass().getName() + " son age vaut = "+ this.age);
	}

	public void seNourrir(Herbe herb){
		if(herb instanceof HerbeNormale)
			this.age++;
		else
			this.mourrir(this.getIndex());

		herb.disparaitre(herb.getIndex());
		System.out.println("Omnivore se nourrit de "+ herb.getClass().getName());
	}

	public void seNourrir(EtreVivant<?> ev) {}

	public void seDeplacer() {
		this.position.setX(this.position.getX() + 1);
		this.position.setY(this.position.getY() + 1);
		System.out.println("Deplacement du Omnivore x = " + this.position.getX() + " y = "+ this.position.getY());
	
	}

	public Omnivore seReproduire() {
		return new Omnivore();
	}

}
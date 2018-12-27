package models;

public class Omnivore extends Animal<Omnivore>{

	public Omnivore() {
		super();
		this.position = new Position(2, 2);
	}
	
	public Omnivore seNourrir(Omnivore omn) {
		this.age++;
		return omn;
	}

	public void seDeplacer() {
		this.position.setX(this.position.getX() + 1);
		this.position.setY(this.position.getY() + 1);
		System.out.println("Deplacement du Omnivore x = " + this.position.getX() + " y = "+ this.position.getY());
	
	}

	public Omnivore seReproduire() {
		return new Omnivore();
	}

}
package models;

public class Herbivore extends Animal<Herbivore>{
	
	public Herbivore() {
		super();
		this.position = new Position(1, 2);
	}
	
	public Herbivore seNourrir(Herbivore herb) {
		this.age++;
		return herb;
	}

	public void seDeplacer() {
		this.position.setX(this.position.getX() + 1);
		this.position.setY(this.position.getY() + 1);
		System.out.println("Deplacement du Herbivore x = " + this.position.getX() + " y = "+ this.position.getY());
	}

	public Herbivore seReproduire() {
		return new Herbivore();
	}
	
}

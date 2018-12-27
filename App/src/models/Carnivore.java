package models;

public class Carnivore extends Animal<Carnivore>{

	public Carnivore() {
		super();
		this.position = new Position(1, 1);
	}
	public Carnivore seNourrir(Carnivore a) {
		this.age++;
		return a;
	}

	public void seDeplacer() {
		this.position.setX(this.position.getX() + 1);
		this.position.setY(this.position.getY() + 1);
		System.out.println("Deplacement du carnivore x = " + this.position.getX() + " y = "+ this.position.getY());
	}

	public Carnivore seReproduire() {
		return new Carnivore();
		
	}

}

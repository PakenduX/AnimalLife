package models;

public class Carnivore extends Animal{
	
	public Carnivore() {
		this.age = 0;
		this.famine = false;
		if((int)Math.random() == 0)
			this.sexe = 'M';
		else
			this.sexe = 'F';
	}

	public Animal seNourrir(Animal a) {
		return a;
		
	}

	public void seDeplacer(Position p) {
		
		
	}

	public Carnivore seReproduire() {
		return new Carnivore();
		
	}

	public void mourrir() {
		
		
	}


}

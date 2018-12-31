package models;

/**
 * La classe représentant un carnivore
 * @author Équipe Jungle
 *
 */
public class Carnivore extends Animal<Carnivore>{

	boolean forward = true;
	boolean backward = false;
	boolean ydecrement = false;

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

	public void seDeplacer(int xMax, int yMax) {
		int x = this.getPosition().getX();
		int y = this.getPosition().getY();

		if (ydecrement)
			this.position.setY(this.position.getY() - 5);

		if(x <= 0 && y <= 0) {
			this.position.setX(this.position.getX() + 5);
			this.position.setY(this.position.getY() + 5);
			forward = true;
			backward = false;

		}else if(x > 0 && y <= 0){
			this.position.setY(this.position.getY() + 5);

		}else if(x < 0 && y >= 0){
			this.position.setX(this.position.getX() + 5);
			forward = true;
			backward = false;

		}else if(x >= xMax && y >= yMax) {
			this.position.setX(this.position.getX() - 5);
			backward = true;
			forward = false;
			ydecrement = true;
		}else if(x >= xMax && y < yMax) {
			this.position.setX(this.position.getX() - 5);
			backward = true;
			forward = false;
			ydecrement = true;

		}else{
			if(forward)
				this.position.setX(this.position.getX() + 5);
			else
				this.position.setX(this.position.getX() - 5);

			ydecrement = false;
		}
		System.out.println("Deplacement du carnivore x = " + this.position.getX() + " y = "+ this.position.getY());
	}

	public Carnivore seReproduire() {
		return new Carnivore();
		
	}

}

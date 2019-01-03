package models;

/**
 * La classe représentant un Omnivore
 * @author Équipe Jungle
 *
 */
public class Omnivore extends Animal<Omnivore>{

	boolean xdecrement = false;

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

	public void seDeplacer(int xMax, int yMax) {
		int x = this.getPosition().getX();
		int y = this.getPosition().getY();

		if(x <= 0 && y <= 0){
			this.setPosition(new Position(this.getPosition().getX() + 5, this.getPosition().getY() + 5));
			deplacementVersBas = true;
		}else if( x > 0 && y <= 0){
				if(x >= xMax){
					//TODO
				}
		}

		}

	public Omnivore seReproduire() {
		return new Omnivore();
	}

}
package models;

/**
 * La classe représentant un Herbivore
 * @author Équipe Jungle
 *
 */
public class Herbivore extends Animal<Herbivore>{

	boolean ydecrement = false;
	boolean yincrement = false;
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

	public void seDeplacer(int xMax, int yMax) {
		int x = this.getPosition().getX();
		int y = this.getPosition().getY();

		if(ydecrement)
			this.position.setY(this.position.getY() - OFFSET);

		if(yincrement)
			this.position.setY(this.position.getY() + 20);

		if(x <= 0 && y <= 0){
			this.position.setX(this.position.getX() + OFFSET);
			deplacementVersDroite = true;
			deplacementVersGauche = false;
			yincrement = true;
			ydecrement = false;
		}else if( x > 0 && y <= 0){
			ydecrement = false;
			yincrement = true;
			deplacementVersDroite = true;
			deplacementVersGauche = false;
			this.position.setX(this.position.getX() + OFFSET);
		}else if(y > 0 && x <= 0){

			this.position.setX(this.position.getX() + OFFSET);
			deplacementVersDroite = true;
			deplacementVersGauche = false;
			if(y > Math.abs(y - yMax))
				ydecrement = true;
			else
				yincrement = true;

		}else if(x >= xMax && y >= yMax){
			ydecrement = true;
			yincrement = false;
			deplacementVersGauche = true;
			deplacementVersDroite = false;
			this.position.setX(this.position.getX() + OFFSET);

		}else if(x < xMax && y >= yMax){
			ydecrement = false;
			yincrement = true;
			if(deplacementVersDroite)
				this.position.setX(this.position.getX() + OFFSET);
			else
				this.position.setX(this.position.getX() - OFFSET);

		}else if(x >= xMax && y < yMax){
			if(y > Math.abs(y - yMax))
				ydecrement = true;
			else
				yincrement = true;
			deplacementVersGauche = true;
			deplacementVersDroite = false;
			this.position.setX(this.position.getX() - OFFSET);

		}else{
			if(deplacementVersDroite)
				this.position.setX(this.position.getX() + OFFSET);
			else
				this.position.setX(this.position.getX() - OFFSET);

			yincrement = false;
			ydecrement = false;

		}
	}

	public Herbivore seReproduire() {
		return new Herbivore();
	}
	
}

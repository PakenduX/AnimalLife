package models;

/**
 * La classe représentant un Omnivore
 * @author Équipe Jungle
 *
 */
public class Omnivore extends Animal<Omnivore>{

	boolean xdecrement = false;
	boolean xincrement = false;

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

		if(xdecrement)
			this.position.setX(this.position.getX() - OFFSET);

		if(xincrement)
			this.position.setX(this.position.getX() + 20);

		if(x <= 0 && y <= 0){
			this.position.setY(this.position.getY() + OFFSET);
			deplacementVersBas = true;
			deplacementVersHaut = false;
			xincrement = true;
			xdecrement = false;
		}else if( x > 0 && y <= 0){
			//Pour pouvoir se décaler de la droite vers la gauche ou vice versa
			// il faut tester la distance de la position actuelle par rapport à 0
			// et à xMax.
				if(x > Math.abs(x - xMax))
					xdecrement = true;
				else
					xincrement = true;

			deplacementVersBas = true;
			deplacementVersHaut = false;
			this.position.setY(this.position.getY() + OFFSET);
		}else if(y > 0 && x <= 0){
			if(y >= yMax){
				this.position.setY(this.position.getY() - OFFSET);
				deplacementVersHaut = true;
				deplacementVersBas = false;
			}else{
				if(deplacementVersHaut)
					this.position.setY(this.position.getY() - OFFSET);
				else
					this.position.setY(this.position.getY() + OFFSET);
			}
			xincrement = true;
			xdecrement = false;
		}else if(x >= xMax && y >= yMax){
			xdecrement = true;
			xincrement = false;
			deplacementVersHaut = true;
			deplacementVersBas = false;
			this.position.setY(this.position.getY() - OFFSET);

		}else if(x < xMax && y >= yMax){
			deplacementVersHaut = true;
			deplacementVersBas = false;
			this.position.setY(this.position.getY() - OFFSET);
			if(x > Math.abs(x - xMax))
				xdecrement = true;
			else
				xincrement = true;
		}else if(x >= xMax && y < yMax){
			xdecrement = true;
			xincrement = false;
			if(deplacementVersHaut)
				this.position.setY(this.position.getY() - OFFSET);
			else
				this.position.setY(this.position.getY() + OFFSET);
		}else{
			if(deplacementVersHaut)
				this.position.setY(this.position.getY() - OFFSET);
			else
				this.position.setY(this.position.getY() + OFFSET);

			xincrement = false;
			xdecrement = false;

		}

	}

	public Omnivore seReproduire() {
		return new Omnivore();
	}

}
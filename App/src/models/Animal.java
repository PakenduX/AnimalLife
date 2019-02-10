package models;

import java.io.IOException;
import java.util.ArrayList;

/**
 * La classe générique abstraite représentant un Animal
 * @author Équipe Jungle
 * @param <E> le type d'animal
 */
public abstract class Animal<E> implements EtreVivant<E> {
	protected int age;
	protected char sexe;
	protected boolean famine;
	protected Position position;
	protected boolean estVivant;
	protected boolean hasChild ;
	protected Animal<?> child;
	protected static ArrayList<Animal<?>> collec_anim = new ArrayList<>();
	//L'indice de l'objet dans la collection des animaux
	protected int index;
	//Ces attributs définissent le sens de déplacement d'un animal
	protected boolean deplacementVersDroite = true;
	protected boolean deplacementVersGauche = false;
	protected boolean deplacementVersHaut = false;
	protected boolean deplacementVersBas = false;
	boolean ydecrement = false;
	boolean yincrement = false;
	boolean xdecrement = false;
	boolean xincrement = false;
	protected final int OFFSET = 10;


    public Animal() {
		this.age = (int)(Math.random()*14 + 1);
		this.famine = false;
		this.estVivant = true;
		hasChild = false;
		int r = (int)(Math.random()*4);
		if( r >= 0 && r < 2)
			this.sexe = 'M';
		else
			this.sexe = 'F';
		
		collec_anim.add(this);
		this.index = collec_anim.size() - 1;
	}

	public char getSexe() {
		return sexe;
	}

	public static ArrayList<Animal<?>> getCollec_anim() {
		return collec_anim;
	}

	public abstract void seNourrir(Animal<?> ev);

	public int getAge() {
		return age;
	}

	public abstract E seReproduire();
	
	/**
	 * Quand un animal meurt, on le supprime de la collection
	 * d'animaux et on le met à la position (-1, -1).
	 * @param index l'indice de l'animal dans la collection
	 */
	public void mourrir(int index) {
		estVivant = false;
		collec_anim.remove(index);
		gerer_index(index);
		setPosition(new Position(-100, -100));
		System.out.println("Element à l'indice "+index + " est dead");
	}
	/**
	 * La collision entre deux animaux, on suppose qu'il y a collision
	 * quand les deux sont relativement proches, dans notre cas la 
	 * différence des positions < 5 pixels.
	 * @param a l'animal avec lequel il y a collision
	 * @return true s'il y a collision, false sinon
	 */
	public boolean collision(Animal<?> a) {
		return (Math.abs(this.position.getX() + 50 - a.position.getX()) < 30
				&& Math.abs(this.position.getY() + 35 - a.position.getY()) < 30)
				|| (Math.abs(this.position.getX() - a.position.getX() + 50) < 30
				&& Math.abs(this.position.getY() - a.position.getY() + 35) < 30);
	}

	public boolean proche_nourriture(Position p){
		return Math.abs(this.position.getX() - p.getX()) < 100
				&& Math.abs(this.position.getY() - p.getY()) < 100;
	}
	
	public void boire() {
		Eau.setQuantite(Eau.getQuantite() - 1);
		//System.out.println(this.getClass().getName() + " a bu et la quantite restante est = "+ Eau.getQuantite());
	}
	
	/**
	 * La fonction qui détermine si deux animaux ont le même sexe.
	 * @param a Animal avec lequel tester.
	 * @return true ou false.
	 */
	public boolean meme_sexe(Animal<?> a) {
		return this.sexe == a.sexe;
	}

	/**
	 * La fonction qui détermine si deux animaux sont de la même espèce
	 * dans notre cas s'ils sont tous les deux carnivores, herbivores ou
	 * omnivores
	 * @param a
	 * @return true or false
	 */
	public boolean meme_espece(Animal<?> a){
		return ((this instanceof Carnivore) && (a instanceof Carnivore)) ||
				((this instanceof Herbivore) && (a instanceof Herbivore)) ||
				((this instanceof Omnivore) && (a instanceof Omnivore));
	}

	/**
	 * Cette fonction teste si l'animal courant est un carnivore.
	 * @return true or false.
	 */
	public boolean isCarnivore(){
		return this instanceof Carnivore;
	}

	/**
	 * Cette fonction teste si l'animal courant est un herbivore.
	 * @return true or false.
	 */
	public boolean isHerbivore(){
		return this instanceof Herbivore;
	}

	/**
	 * Cette fonction teste si l'animal courant est un omnivore.
	 * @return true or false.
	 */
	public boolean isOmnivore(){
		return this instanceof Omnivore;
	}
	
	/**
	 * Cette fonction permet de savoir si un animal est adulte ou pas
	 * On suppose que l'animal est adulte s'il est agé d'au moins de 15 ans.
	 * @return true or false.
	 */
	public boolean adult() {
		return this.age >= 15;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

    public int getIndex() {
        return index;
    }

	public void setIndex(int index) {
		this.index = index;
	}

	public boolean estVivant(){
		return estVivant;
	}

	public boolean getHasChild() {
		return hasChild;
	}

	public Animal<?> getChild() {
		return child;
	}

	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}

	public void setChild(Animal<?> child) {
		this.child = child;
	}

	/**
	 * Cette fonction permet de décrementer les indices des animaux
	 * qui suivent l'animal supprimé car après la suppression d'un élément
	 * dans ArrayLIst les éléments sont décalés vers la gauche.
	 * @param index l'indice de l'animal supprimé.
	 */
	public void gerer_index(int index) {
		for (int i = index; i < collec_anim.size(); i++)
			collec_anim.get(i).setIndex(i);
	}

	public boolean getDeplacementVersDroite() {
		return deplacementVersDroite;
	}

	public boolean getDeplacementVersHaut() {
		return deplacementVersHaut;
	}

	public void seDeplacerLeft(int xMax, int yMax) {
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
	public void seDeplacerRight(int xMax, int yMax) {
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
	public void seDeplacerHigh(int xMax, int yMax) {
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
}


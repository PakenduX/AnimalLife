package models;

import java.util.ArrayList;

public abstract class Animal<E> implements EtreVivant<E>{
	protected int age;
	protected char sexe;
	protected boolean famine;
	protected Position position;
	protected boolean estVivant;
	protected static ArrayList<Animal<?>> collec_anim = new ArrayList<>();
	
	public Animal() {
		this.age = 0;
		this.famine = false;
		this.estVivant = true;
		int r = (int)(Math.random()*4);
		System.out.println("random = "+r);
		if( r >= 0 && r < 2)
			this.sexe = 'M';
		else
			this.sexe = 'F';
		
		collec_anim.add(this);
	}

	public char getSexe() {
		return sexe;
	}

	public void setSexe(char sexe) {
		this.sexe = sexe;
	}

	public static ArrayList<Animal<?>> getCollec_anim() {
		return collec_anim;
	}

	public static void setCollec_anim(ArrayList<Animal<?>> collec_anim) {
		Animal.collec_anim = collec_anim;
	}

	public abstract E seNourrir(E ev);

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public abstract void seDeplacer();

	public abstract E seReproduire();
	
	/**
	 * Quand un animal meurt, on le supprime de la collections
	 * d'animaux.
	 * @param index l'indice de l'animal dans la collection
	 */
	public void mourrir(int index) {
		estVivant = false;
		collec_anim.remove(index);
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
		return Math.abs(this.position.getX() - a.position.getX()) < 5
				&& Math.abs(this.position.getY() - a.position.getY()) < 5;
		
	}
	
	public void boire(Eau o) {
		o.setQuantite(o.getQuantite() - 1);
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
	 * Cette fonction permet de savoir si un animal est adulte ou pas
	 * On suppose que l'animal est adulte si son age est entre 1 et 15.
	 * @return true or false.
	 */
	public boolean adult() {
		return this.age >= 1 && this.age <= 15;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

}

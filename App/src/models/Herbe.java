package models;

import java.util.ArrayList;

public class Herbe{

	private int index;
	private Position position;
	private static ArrayList<Herbe> coll_herb = new ArrayList<>();
	private boolean Herbe_est_vivant;

	public Herbe() {
		//this.position = new Position(0, 0);
		Herbe_est_vivant=true;
		coll_herb.add(this);
		this.index = coll_herb.size() - 1;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public static ArrayList<Herbe> getColl_herb() {
		return coll_herb;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	public boolean isHerbe_est_vivant(){return Herbe_est_vivant;}

	public void disparaitre(int index){
		coll_herb.remove(index);
		Herbe_est_vivant=false;
		gerer_index(index);
		setPosition(new Position(-10, -10));
		System.out.println("L'herbe à l'indice "+ index + " disparait");
	}

	/**
	 * Cette fonction permet de décrementer les indices des Herbes
	 * qui suivent l'herbe supprimée car après la suppression d'un élément
	 * dans ArrayLIst les éléments sont décalés vers la gauche.
	 * @param index l'indice de l'herbe supprimée.
	 */
	public void gerer_index(int index) {
		for (int i = index; i < coll_herb.size(); i++)
			coll_herb.get(i).setIndex(i);
	}

	public boolean isNormal(){
		return this instanceof HerbeNormale;
	}

	public boolean isPoisoned(){
		return this instanceof HerbeEmpoisonnee;
	}
}

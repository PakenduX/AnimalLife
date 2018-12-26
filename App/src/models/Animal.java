package models;

import views.Jungle;

public abstract class Animal implements EtreVivant{
	protected int age;
	protected char sexe;
	protected boolean famine;
	protected Jungle jungle;
	

	public EtreVivant seNourrir(EtreVivant ev) {
		return ev;
	}

	public abstract void seDeplacer(Position p);

	public abstract Animal seReproduire();

	public void mourrir() {
		jungle.supprimer(this);
	}
	
	public boolean collision(Animal a) {
		return famine;
		
	}

}

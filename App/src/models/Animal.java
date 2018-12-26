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

	public abstract void seDeplacer(int x, int y);

	public Animal seReproduire() {
		return null;
		
	}

	public void mourrir() {
		
	}
	
	public void collision(Animal a) {
		
	}

}

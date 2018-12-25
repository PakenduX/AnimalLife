package models;

public abstract class Animal implements EtreVivant{
	protected int age;
	protected char sexe;
	protected boolean famine;

	public abstract void seNourrir();

	public abstract void seDeplacer(int x, int y);

	public abstract EtreVivant seReproduire();

	public abstract void mourrir();

}

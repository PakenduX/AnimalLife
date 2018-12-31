package models;

public interface EtreVivant<E> {
	
	public void seNourrir(EtreVivant<?> ev);
	public void seDeplacer(int xMax, int yMax);
	public E seReproduire();
	public void mourrir(int index);
}

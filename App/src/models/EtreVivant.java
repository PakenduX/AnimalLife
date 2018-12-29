package models;

public interface EtreVivant<E> {
	
	public void seNourrir(EtreVivant<?> ev);
	public void seDeplacer();
	public E seReproduire();
	public void mourrir(int index);
}

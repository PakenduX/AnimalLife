package models;

public interface EtreVivant<E> {
	
	public void seNourrir(EtreVivant<?> ev);
	public E seReproduire();
	public void mourrir(int index);
}

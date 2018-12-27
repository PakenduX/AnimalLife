package models;

public interface EtreVivant<E> {
	
	public E seNourrir(E ev);
	public void seDeplacer();
	public E seReproduire();
	public void mourrir(int index);
}

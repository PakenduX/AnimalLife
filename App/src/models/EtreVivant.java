package models;

public interface EtreVivant {
	
	public EtreVivant seNourrir(EtreVivant ev);
	public void seDeplacer(Position p);
	public EtreVivant seReproduire();
	public void mourrir();
}

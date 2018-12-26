package models;

public interface EtreVivant {
	
	public EtreVivant seNourrir(EtreVivant ev);
	public void seDeplacer(int x, int y);
	public EtreVivant seReproduire();
	public void mourrir();
}

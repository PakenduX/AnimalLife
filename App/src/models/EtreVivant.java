package models;

public interface EtreVivant {
	
	public void seNourrir();
	public void seDeplacer(int x, int y);
	public EtreVivant seReproduire();
	public void mourrir();
}

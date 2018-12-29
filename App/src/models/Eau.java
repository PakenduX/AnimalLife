package models;

public class Eau {
	
	private static int quantite;
	private static Position position;

	public Eau() {
		quantite = 10;
		position = new Position(0, 0);
	}

	public static int getQuantite() {
		return quantite;
	}

	public static void setQuantite(int q) {
		quantite = q;
	}

	public static void setPosition(Position p) {
		position = p;
	}

	public static Position getPosition() {
		return position;
	}
	
	
}

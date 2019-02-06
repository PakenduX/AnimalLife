package models;

public class Eau {
	
	private static int quantite = 100;
	private static Position position = new Position(960, 540);
	private static boolean eau_is_suffisant = true;


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

	public static boolean isEau_is_suffisant() {
		return eau_is_suffisant;
	}
	public static void setEau_is_suffisant(boolean b){
		eau_is_suffisant = b;
	}
	public static void disparaitre(){
		eau_is_suffisant = false;
	}
}

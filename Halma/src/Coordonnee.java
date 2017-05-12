/**
 * Utilisée pour retourner des coordonées depuis des fonctions
 * @author lucas.LECOMTE
 */
public class Coordonnee {

	private int x;
	private int y;
	
	/**
	 * Retourne la valeur x de la coordonnée
	 * @return la valeur x de la coordonnée
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Change la valeur x de la coordonnée
	 * @param x Entier
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Retourne la valeur y de la coordonnée
	 * @return la valeur y de la coordonnée
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Change la valeur y de la coordonnée
	 * @param y Entier
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Constructeur
	 * @param x int
	 * @param y int
	 */
	public Coordonnee(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
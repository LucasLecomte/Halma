/**
 * Simule un pion d'un joueur
 * @author lucas.LECOMTE
 *
 */
public class Pion {

	public static final String RESET = "\u001B[0m";
	public static final String BLACK = "\u001B[30m";
	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String YELLOW = "\u001B[33m";
	public static final String BLUE = "\u001B[34m";
	public static final String PURPLE = "\u001B[35m";
	public static final String CYAN = "\u001B[36m";
	public static final String WHITE = "\u001B[37m";
	public static final String BLACK_BACKGROUND = "\u001B[40m";
	public static final String RED_BACKGROUND = "\u001B[41m";
	public static final String GREEN_BACKGROUND = "\u001B[42m";
	public static final String YELLOW_BACKGROUND = "\u001B[43m";
	public static final String BLUE_BACKGROUND = "\u001B[44m";
	public static final String PURPLE_BACKGROUND = "\u001B[45m";
	public static final String CYAN_BACKGROUND = "\u001B[46m";
	public static final String WHITE_BACKGROUND = "\u001B[47m";
	
	protected int p;

	/**
	 * Retourne le numéro d'un joueur
	 * @return
	 */
	public int getP(){
		return p;
	}

	/**
	 * Définit le numéro d'un joueur
	 */
	public void setP(int p) {
		this.p = p;
	}
 
	/**
	 * Constructeur pour un pion avec le numéro p.
	 */
	public Pion(int p) {
		super();
		this.p = p;
	}

	@Override
	public String toString() {
		return " " + p + " ";
	}
	/**
	 * Renvoie un String avec le numéro concaténé à des code ANSI pour afficher de la couleur dans les temrinaux qui le supportent.
	 * @param c Couleur dans laquelle afficher le numéro
	 * @return Retourne un String
	 */
	public String afficherEnCouleur(Couleur c){
		switch(c){
		case BLEU : return " " + BLUE_BACKGROUND + WHITE + " " + p + " " + RESET + " ";
		case ROUGE : return " " + RED_BACKGROUND + WHITE + " " + p + " " + RESET + " ";
		case VERT : return " " + GREEN_BACKGROUND + " " + p + " " + RESET + " ";
		case JAUNE : return " " + YELLOW_BACKGROUND + " " + p + " " + RESET + " ";
		case VIOLET : return " " + PURPLE_BACKGROUND + WHITE + " " + p + " " + RESET + " ";
		case CYAN : return CYAN_BACKGROUND + " " + p + " " + RESET;
		case NONE : return "  " + p + "  ";
		default : return "  " + p + "  ";
		}
	}
	
	/**
	 * Indique si la case est libre donc si le pion qui s'y situe n'appartient a personne.
	 * @return boolean
	 */
	public boolean estLibre(){
		if(p == 0)
			return true;
		return false;
	}
	
	
	
	
	
	
	
	
}

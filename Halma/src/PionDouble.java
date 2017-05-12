
public class PionDouble extends Pion {

	public PionDouble(int p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "[" + getP() + "]";
	}

	public String afficherEnCouleur(Couleur c){
		switch(c){
		case BLEU : return BLUE + " [" + p + "] " + RESET;
		case ROUGE : return RED + " [" + p + "] " + RESET;
		case VERT : return GREEN + " [" + p + "] " + RESET;
		case JAUNE : return YELLOW + " [" + p + "] " + RESET;
		case VIOLET : return PURPLE + " [" + p + "] " + RESET;
		case CYAN : return CYAN + " [" + p + "] " + RESET;
		case NONE : return " [" + p + "] ";
		default : return " [" + p + "] ";
		}
	}
	
	
	
	
	
	
}

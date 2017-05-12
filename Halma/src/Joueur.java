/**
 * Représente un joueur, son numéro, ses préférences, sa couleur et son nom.
 * @author lucas
 *
 */
public class Joueur {


	
	private Preferences pref;
	private Couleur c;	
	private String nom;
	private int p;
	
	/**
	 * Initialise un joueur avec pref préférences de déplacement, la couleur c, le nom nom et le chiffre p.
	 * @param pref Enum indiquant si le joueur souhaite effectuer ses déplacements en donnant les coordonnées ou en utilisant le sélecteur
	 * @param c	Enum indiquant la couleur que le joueur souhaite utiliser
	 * @param nom Nom du joueur.
	 * @param p Chiffre entier indiquant le numéro du joueur.
	 */
	public Joueur(Preferences pref, Couleur c, String nom, int p) {
		this.pref = pref;
		this.c = c;
		this.nom = nom;
		this.p = p;
	}
	
/**
 * Change la préférence du joueur.
 */
	public void setPref(Preferences pref) {
		this.pref = pref;
	}

	/**
	 * Retourne la préférence d'un joueur.
	 */
	public Preferences getPref() {
		return pref;
	}
	
	 /**
	  * Retourne la couleur du joueur. 
	  * @return
	  */
	public Couleur getC() {
		return c;
	}
	
	
	/**
	 * Change la couleur d'un joueur.
	 */
	public void setC(Couleur c) {
		this.c = c;
	}
	
	/**
	 * Retourne le nom du joueur
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Change le nom du joueur.
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Change le numéro d'un joueur.
	 */
	public void setP(int p) {
		this.p = p;
	}

	/**
	 * Retourne le numéro d'un joueur
	 * @return
	 */
	public int getP() {
		return p;
	}
	
	
	public String toString(){
		return nom;
	}
	

		
	
	
	
	
	
	
	
	
	
	
	
	
}

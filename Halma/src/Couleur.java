/**
 * Enum�ration des couleur possibles.
 * @author lucas.LECOMTE
 *
 */
public enum Couleur {

	BLEU(1),
	ROUGE(2),
	VERT(3),
	JAUNE(4),
	VIOLET(5),
	CYAN(6),
	NONE(7);
	
	private int valeur;
	
	/**
	 * Constructeur
	 * @param valeur Valeur de l'�num�ration
	 */
	private Couleur(int valeur){
		this.valeur = valeur;		
	}
	
	/**
	 * Retourne la valeur de la couleur
	 * @return valeur
	 */
	  public int getCouleur(){
	    return valeur;
	  }
	  
	  public String toString(){
	    return this.name();
	  }
	
	  /**
	   * L'�numeration contient-elle la couleur suivante ?
	   * @param s Nom de la couleur
	   * @return boolean true si la couleur est dans l'�num�ration
	   */
	  public static boolean contient(String s){
	      for(Couleur c : Couleur.values())
	           if (c.name().equals(s)) 
	              return true;
	      return false;
	  } 
}
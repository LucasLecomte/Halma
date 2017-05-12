/**
 * Enum�ration des pr�f�rence de s�l�ction possibles.
 * @author lucas.LECOMTE
 *
 */
public enum Preferences {

	COORDONNEES(1),
	SELECTEUR(2);
	
	private int valeur;
	
	/**
	 * Constructeur
	 * @param valeur Valeur de l'�num�ration
	 */
	private Preferences(int valeur){
		this.valeur = valeur;		
	}
	
	/**
	 * Retourne la valeur de la pr�f�rence
	 * @return Valeur
	 */
	  public int getValeur(){
	    return valeur;
	  }
	  
	  public String toString(){
	    return this.name();
	  }
}
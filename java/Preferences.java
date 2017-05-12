/**
 * Enumération des préférence de séléction possibles.
 * @author lucas.LECOMTE
 *
 */
public enum Preferences {

	COORDONNEES(1),
	SELECTEUR(2);
	
	private int valeur;
	
	/**
	 * Constructeur
	 * @param valeur Valeur de l'énumération
	 */
	private Preferences(int valeur){
		this.valeur = valeur;		
	}
	
	/**
	 * Retourne la valeur de la préférence
	 * @return Valeur
	 */
	  public int getValeur(){
	    return valeur;
	  }
	  
	  public String toString(){
	    return this.name();
	  }
}
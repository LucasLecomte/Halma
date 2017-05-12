import java.util.*;

/**
 * Liste des joueurs de la partie et des IA si il y en a.
 * @author lucas
 *
 */
public class Joueurs {

	//Joueur[] Joueurs;
	private LinkedList<Joueur> Liste = new LinkedList<Joueur>();

	/**
	 * Ajoute un joueur j a la liste
	 * @param j un joueur
	 */
	public void ajouter(Joueur j){
		Liste.add(j);		
	}

	/**
	 * Remplis la liste avec des joueurs et/ou des IA par défaut
	 * @param nbJoueur nombre de joueur au total, IA compris (2 ou 4)
	 * @param nbIA nombre d'IA
	 */
	public void remplirVide(int nbJoueur, int nbIA){
		for(int i=0 ; i<nbJoueur - nbIA; i++)
			if(i+1 == 1)
				this.ajouter(new Joueur(Preferences.SELECTEUR, Couleur.BLEU,"défaut"+(i+1),i+1));
			else if(i+1 == 2)
				this.ajouter(new Joueur(Preferences.SELECTEUR, Couleur.ROUGE,"défaut"+(i+1),i+1));
			else if(i+1 == 3)
				this.ajouter(new Joueur(Preferences.SELECTEUR, Couleur.VERT,"défaut"+(i+1),i+1));
			else if(i+1 == 4)
				this.ajouter(new Joueur(Preferences.SELECTEUR, Couleur.JAUNE,"défaut"+(i+1),i+1));
		for(int i=nbJoueur - nbIA ; i< nbJoueur; i++)
			if(i+1 == 1)
				this.ajouter(new Joueur(Preferences.SELECTEUR, Couleur.CYAN,"npc"+(i+1),i+1));
			else if(i+1 == 2)
				this.ajouter(new Joueur(Preferences.SELECTEUR, Couleur.VIOLET,"npc"+(i+1),i+1));
			else if(i+1 == 3)
				this.ajouter(new Joueur(Preferences.SELECTEUR, Couleur.VERT,"npc"+(i+1),i+1));
			else if(i+1 == 4)
				this.ajouter(new Joueur(Preferences.SELECTEUR, Couleur.JAUNE,"npc"+(i+1),i+1));
		}

	/**
	 * Fait tourner l'ordre des joueurs. Le premier deviens le dernier et tout les autre montent d'une place.
	 * @return Retourne le dernier de la liste donc l'ancien premier.
	 */
	public Joueur cycle(){
		Liste.addLast(Liste.pop());
		return Liste.getLast();
	}

	@Override
	public String toString() {
		return  Liste + "";
	}

	/**
	 * Retourne la taille de la liste
	 */
	public int taille(){
		return Liste.size();
	}

	/**
	 * Retourne le premier joueur.
	 */
	public Joueur getPremier(){
		return Liste.getFirst();
	}


	/**
	 * Renvoie le joueur dont le numéro est passé en argument
	 * @param numero numéro du joueur recherché
	 * @return Retourne le joueur possédant le numéro recherché
	 */
	public Joueur getNumero(int numero){
		for(Joueur joueur : Liste) {
			if(joueur.getP() == numero)
				return joueur;
		}
		return null;
	}
}

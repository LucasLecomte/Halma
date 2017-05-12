import java.util.Scanner;
import java.util.Random;
/**
 * Simule une partie.
 * @author lucas.LECOMte
 *
 */
public class PartieConsole {

	private String rep;
	private boolean cbon = true;
	private boolean terminalCouleur = false;

	private Joueurs liste = new Joueurs();
	private Grille g = new Grille();
	private Scanner sc = new Scanner(System.in);
	private Random r = new Random();
	private Coordonnee debut;
	private Coordonnee fin;
	private Coordonnee positionInitiale;
	private Coordonnee temp;


	private Joueur gagnant = new Joueur(null,null,null,0);


	public PartieConsole(){}

	/**
	 * Méthode de séléction plus intuitive que les coordonnées
	 * @param sauterDeNouveau boolean indiquant si le joueur saute plus d'une fois
	 * @return Retourne des coordonnées
	 */
	public Coordonnee selecteur(boolean sauterDeNouveau){
		Coordonnee c = new Coordonnee(0,0);
		boolean finit = false;
		int x = 5;
		int y = 5;
		if(terminalCouleur)
			g.afficherEnCouleur(liste,x,y);
		else
			g.afficher(x,y);
		do{
			rep = sc.nextLine();
			switch(rep){
			case "1": if(x<9) x++; if(y>0) y--; break;
			case "2": if(x<9) x++; break;
			case "3": if(x<9) x++; if(y<9) y++; break;
			case "4": if(y>0) y--; break;
			case "6": if(y<9) y++; break;
			case "7": if(x>0) x--; if(y>0) y--; break;
			case "8": if(x>0) x--; break;
			case "9": if(x>0) x--; if(y<9) y++; break;
			case "0": finit = true; break;
			case "5": finit = true; break;
			}
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			if(terminalCouleur)
				g.afficherEnCouleur(liste,x,y);
			else
				g.afficher(x,y);
		}while(!finit);
		if(sauterDeNouveau && rep.equals("5")){
			c.setX(-1);
			c.setY(-1);
			return c;
		}
		c.setX(x);
		c.setY(y);
		return c;
	}

	/**
	 * Retourne des coordonnées entrées par l'utilisateur
	 */
	public Coordonnee inputCoord(){
		Coordonnee c = new Coordonnee(0,0);
		if(terminalCouleur)
			g.afficherEnCouleur(liste);
		else
			g.afficher();
		c.setX(sc.nextInt());
		c.setY(sc.nextInt());
		return c;	
	}

	/**
	 * Méthode principale où se déroule le jeu
	 */
	public void jouer(){
		boolean pasErreur = true;
		boolean finit=false;
		boolean tentativeRetourDebut = false;
		String nbJoueur;
		String nbIA;

		//Combien de joueur joueront cette partie
		System.out.println("Bonjour bienvenue dans Halma\n\nCombien de joueurs joueront cette partie ?");
		do{
			if(rep != null)
				System.out.println("Vous ne pouvez jouer qu'à deux ou à quatre.");
			rep = sc.nextLine();
		}while(!rep.equals("2") && !rep.equals("4"));
		nbJoueur = rep;
		g.remplir(Integer.parseInt(nbJoueur));
		rep = null;

		//Combien d'IA
		System.out.println("Combien d'ordinateurs joueront avec vous ?");
		do{
			if(rep != null)
				System.out.println("Entrez un chiffre entre 0 et 4.");
			rep = sc.nextLine();
		}while(!rep.equals("0") && !rep.equals("1") && !rep.equals("2") && !rep.equals("3") && !rep.equals("4"));
		nbIA = rep;
		liste.remplirVide(Integer.parseInt(nbJoueur),Integer.parseInt(nbIA));
		rep = null;

		//Qui va commencer + mis en ordre de la liste de Joueur
		System.out.println("\nQui désire commencer ?");
		System.out.println("	(Entrez le numero du joueur ou bien  a  pour choisir aléatoirement)");
		do{
			if(rep != null)
				System.out.println("Vous avez le choix entre 1, 2, 3, 4 et a");
			rep = sc.nextLine();
		}while(!rep.equals("1") && !rep.equals("2") && !rep.equals("3") && !rep.equals("4") && !rep.toUpperCase().equals("A"));


		if(rep.toUpperCase().equals("A") && liste.taille() == 4)
			rep=""+(r.nextInt(4 + 1 - 1) + 1);
		else if (rep.equals("a"))
			rep=""+(r.nextInt(2 + 1 - 1) + 1);
		System.out.println("C'est le joueur "+rep+" qui commence !\n");

		for(int i=0 ; i<Integer.parseInt(rep)-1; i++){
			liste.cycle();
		}
		rep = null;

		//Changer les preferences des joueurs
		System.out.println("Souhaitez-vous changer les préférences des joueurs ? \n	Oui/Non\n	(Les changer prends du temps)");
		do{
			if(rep != null)
				System.out.println("Oui ou Non");
			rep = sc.nextLine();
		}while(!rep.toUpperCase().equals("OUI") && !rep.toUpperCase().equals("NON"));

		if(rep.toUpperCase().equals("OUI")){
			for(int i=1 ; i<=liste.taille(); i++){
				if(!liste.getNumero(i).getNom().substring(0, 3).equals("npc")){

					//le nom
					System.out.println("Joueur "+i+" Pseudo :");
					rep = sc.nextLine();
					liste.getPremier().setNom(rep);
					rep = null;

					//la préférence de selection
					System.out.println("Joueur "+i+" Préférences (selecteur/coord) :");
					do{
						if(rep != null)
							System.out.println("Tapez une des trois propositions suivantes selecteur / coord");
						rep = sc.nextLine();
					}while(!rep.toUpperCase().equals("SELECTEUR") && !rep.toUpperCase().equals("COORD"));
					switch(rep.toUpperCase()){
					case "SELECTEUR" : liste.getPremier().setPref(Preferences.SELECTEUR); break;
					case "COORD" : liste.getPremier().setPref(Preferences.COORDONNEES); break;
					}
					rep = null;

					//la couleur
					System.out.println("Joueur "+i+" Couleur (bleu/rouge/vert/jaune/violet/cyan) :");
					do{
						if(rep != null)
							System.out.println("Seul les couleurs proposées ci dessus sont disponibles");
						rep = sc.nextLine();
					}while(Couleur.contient(rep));


					switch(rep.toUpperCase()){
					case "BLEU" : liste.getPremier().setC(Couleur.BLEU); break;
					case "ROUGE" : liste.getPremier().setC(Couleur.ROUGE); break;
					case "VERT" : liste.getPremier().setC(Couleur.VERT); break;
					case "JAUNE" : liste.getPremier().setC(Couleur.JAUNE); break;
					case "VIOLET" : liste.getPremier().setC(Couleur.VIOLET); break;
					case "CYAN" : liste.getPremier().setC(Couleur.CYAN); break;
					}
					rep = null;
				}
			}
		}

		System.out.println("\nJouez vous dans un terminal supportant les codes ANSI ? \n	Oui/Non\n	(Eclipse ne les supporte pas, la console Linux oui.)");
		do{
			if(rep != null)
				System.out.println("Oui ou Non");
			rep = sc.nextLine();
		}while(!rep.toUpperCase().equals("OUI") && !rep.toUpperCase().equals("NON"));
		if(rep.toUpperCase().equals("OUI"))
			terminalCouleur = true;

		//debut de partie
		while(cbon){
			if(!liste.getPremier().getNom().substring(0, 3).equals("npc")){
				System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
				System.out.println(liste.getPremier().getNom() +" c'est a vous de jouer");
				if(liste.getPremier().getPref() == Preferences.COORDONNEES){
					System.out.println("Quel pion souhaitez vous déplacer ?");
					debut = inputCoord();
					System.out.println("Où souhaitez vous déplacer votre pion");
					fin = inputCoord();
				}
				else{
					System.out.println("Quel pion souhaitez vous déplacer ?\n");
					System.out.println("	Utilisez le pavé numérique");
					System.out.println("	(1,2,3,4,6,7,8,9 pour la direction");
					System.out.println("	0 pour séléctionner)\n");
					debut = selecteur(false);
					System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
					System.out.println("Où souhaitez vous déplacer votre pion ?\n");
					fin = selecteur(false);

				}
				try{
					g.deplacer(debut.getX(), debut.getY(), fin.getX(), fin.getY(), liste.getPremier().getP());
				}catch(ExceptionValeurFausse e){pasErreur = false;}

				positionInitiale = debut;

				//si le joueur viens de sauter
				if(g.distance(debut.getX(), debut.getY(), fin.getX(), fin.getY()) == 2 
						|| (g.distance(debut.getX(), debut.getY(), fin.getX(), fin.getY()) == 3 
						&& g.getPion(fin.getX(), fin.getY()) instanceof PionDouble)){

					//alors il peux faire un deuxième saut
					while(g.peuxSauter(fin.getX(), fin.getY()) && !finit){
						debut = fin;
						System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
						if(tentativeRetourDebut){
							System.out.println("Vous ne pouvez pas retourner sur votre position initiale !\n");
							tentativeRetourDebut = false;
						}
						System.out.println("Vous pouvez sauter un autre pion ou vous arreter ici (5)");
						temp = fin;

						fin = selecteur(true);

						if(fin.getX() == -1)
							break;
						if(fin.getX() == positionInitiale.getX() && fin.getY() == positionInitiale.getY()){
							tentativeRetourDebut = true; 
							fin = temp;
						}
						if(g.distance(debut.getX(),debut.getY(),fin.getX(),fin.getY()) == 1)
							fin = temp;
						if(!tentativeRetourDebut && g.distance(debut.getX(),debut.getY(),fin.getX(),fin.getY()) != 1 )
							try{
								g.deplacer(debut.getX(), debut.getY(), fin.getX(), fin.getY(), liste.getPremier().getP());
							}catch(ExceptionValeurFausse e){}
					}
				}

			}
			else if(liste.getPremier().getNom().substring(0, 3).equals("npc")){
				if(!g.deplacerPionIA(liste.getPremier().getP()))
					g.debloquerIA(liste.getPremier().getP() , liste.taille());
				System.out.println("L'ordinateur joue. Appuyez sur la touche Entrée \n");
				sc.nextLine();
				if(terminalCouleur)
					g.afficherEnCouleur(liste);
				else
					g.afficher();
			}

			if(pasErreur)
				liste.cycle();
			pasErreur = true;

			switch(g.victoire(liste.taille())){
			case 1 : 
				cbon = false; 
				gagnant = liste.getNumero(1);
				break;
			case 2 : 
				cbon = false; 
				gagnant = liste.getNumero(2);
				break;
			case 3 : 
				cbon = false; 
				gagnant = liste.getNumero(3);
				break;
			case 4 : 
				cbon = false; 
				gagnant = liste.getNumero(4);
				break;
			default :
				break;
			}
		}
		System.out.println("Le gagnant est "+gagnant.getNom());
	}
}

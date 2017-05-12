//import java.util.LinkedList;

//import java.util.LinkedList;

//import java.util.Scanner;

public class test {

	public static void main(String[] args) {


		//TEST classe Pion, PionDouble
		/*	
		Pion p = new Pion(1);
		PionDouble d = new PionDouble(3);

		p.afficherEnCouleur(Couleur.CYAN);
		System.out.println(p.estLibre());
		System.out.println(p.getP());
		p.setP(0);
		System.out.println(p.estLibre());

		d.afficherEnCouleur(Couleur.CYAN);
		System.out.println(d.estLibre());
		System.out.println(d.getP());
		d.setP(0);
		System.out.println(d.estLibre());
		 */


		//TEST classe Joueur
		/*
		Joueur j = new Joueur(Preferences.COORDONNEES,Couleur.BLEU,"Alphonse Elric",1 );
		System.out.println(j.getP());
		System.out.println(j.getC());
		System.out.println(j.getNom());
		System.out.println(j.getPref());
		System.out.println(j);
		j.setC(Couleur.CYAN);
		j.setPref(Preferences.SELECTEUR);
		j.setNom("Edward Elric");
		j.setP(4);
		System.out.println(j);
		 */


		//TEST classe Joueurs
		/*
		Joueurs liste = new Joueurs();
		liste.ajouter(new Joueur(Preferences.COORDONNEES,Couleur.BLEU,"elPaquito",1 ));
		liste.ajouter(new Joueur(Preferences.SELECTEUR,Couleur.ROUGE,"Izuku",2 ));
		liste.ajouter(new Joueur(null,Couleur.NONE,"vinSmoke Sanji",3 ));
		liste.ajouter(new Joueur(null,Couleur.VERT,"Roronoa Zoro",4));
		System.out.println(liste);
		liste.cycle();
		System.out.println(liste);
		System.out.println(liste.taille());
		System.out.println(liste.getNumero(2));
		System.out.println(liste.getPremier());
		 */


		//TEST classe Coordonnee
		/*
		Coordonnee c = new Coordonnee(0, 0);
		System.out.println(c.getX());
		System.out.println(c.getY());
		c.setX(5);
		c.setY(5);
		System.out.println(c);
		 */


		//TEST classe Grille
		/*
		int nbJoueur = 4;
		Grille g = new Grille();
		LinkedList<Coordonnee> l = new LinkedList<Coordonnee>();
		g.remplir(nbJoueur);
		g.afficher();
		System.out.println("\n\n");
		g.afficher(5, 5);
		System.out.println("\n\n");
		//Enlevez le commentaire entourant le test des listes 
		//Les couleurs ne sont pas supportées sous Eclipse
		//g.afficherEnCouleur(liste);
		g.setPion(1, 3, 3);
		g.afficher();
		System.out.println("\n\n");
		System.out.println("Le centre est libre entre (1,1) et (3,3) "+g.centreLibre(1, 1, 3, 3));
		System.out.println("La distance de (0,0) à (5,5) est " + g.distance(0, 0, 5, 5));
		System.out.println("Le pion (0,0) est " + g.getPion(0, 0));

		try{
			g.deplacer(0, 1, 0, 4, 1);
		}catch(ExceptionValeurFausse e){System.out.println("erreur");}
		g.afficher();
		System.out.println("\n\n");

		try{
			g.deplacer(0, 0, 9, 9, 1);
		}catch(ExceptionValeurFausse e){System.out.println("erreur");}
		g.afficher();
		System.out.println("\n\n");
		System.out.println("Le pion (0,0) peut-il sauter ? " + g.peuxSauter(0, 0));


		l = g.listeDesDiagonalesVersBut(0, 0, 1);
		System.out.println(l);
		l = g.listeDesMouvementsVersBut(0, 0, 1);
		System.out.println(l);

		System.out.println("\n\n\n\n\n\n\n\n");
		g.deplacerPionIA(1);
		g.afficher();
		System.out.println("\n");
		g.debloquerIA(1);
		g.afficher();
		System.out.println("\n");

		System.out.println("\n\n\n\n\n\n\n\n");
		int coef = 0;
		if(nbJoueur == 4)
			coef = 1;


		for(int i = 0;i < 10;i++)
			for(int j = 0;j < 10;j++)

				if (i+j >= 14+coef && i+j < 17)
					g.getPion(i, j).setP(1); 
				else if (i+j > 16)
					g.getPion(i, j).setP(1);

		g.afficher();
		System.out.println("C'est le joueur " + g.victoire(nbJoueur) + " qui remporte la victoire");

		 */

		//TEST déplacement diagonal à travers la grille
		/*
		Grille g = new Grille();
		g.remplir(4);
		g.afficherEnCouleur(liste, 5, 5);
		System.out.println("\n\n\n\n\n\n\n\n");

		for( int i = 0 ; i < 10 ; i++ ) {
			for( int j = 0 ; j <= i ; j++ ) {
				int k = i - j;
				System.out.print( g.getPion(10 - k -1, j) + " " );
			}
			System.out.println();
		}

		for( int i = 10 - 2 ; i >= 0 ; i-- ) {
			for( int j = 0 ; j <= i ; j++ ) {
				int k = i - j;
				System.out.print(g.getPion(j, 10-k-1) + " " );
			}
			System.out.println();
		}
		 */


		PartieConsole pc = new PartieConsole();
		pc.jouer();
		 
	}
}

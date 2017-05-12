import java.util.ArrayList;
import java.util.LinkedList;


/**
 * Simule le plateau du jeu.
 * @author Lucas.LECOMTE
 */
public class Grille {

	public static final String RESET = "\u001B[0m";
	public static final String WHITE_BACKGROUND = "\u001B[47m";
	public static final String BLACK = "\u001B[30m";


	private Pion grille[][] = new Pion[10][10];

	/**
	 * 
	 * @param p Numéro du joueur
	 * @param x Première coordonnée du pion sur la grille
	 * @param y Deuxième coordonnée du pion sur la grille
	 */
	public void setPion(int p, int x,int y){
		grille[x][y] = new Pion(p);
	}

	/**
	 * 
	 * @param x Première coordonnée du pion sur la grille
	 * @param y Deuxième coordonnée du pion sur la grille
	 * @return Renvoie le pion qui se situe en (x,y) sur la grille
	 */
	public Pion getPion(int x,int y){
		return grille[x][y];		
	}

	/**
	 * 
	 * @param p Numéro du joueur
	 * @param x Première coordonnée du pionDouble sur la grille
	 * @param y Deuxième coordonnée du pionDouble sur la grille
	 */
	public void setPionDouble(int p, int x,int y){
		if (p==0)
			grille[x][y] = new Pion(p);
		grille[x][y] = new PionDouble(p);
	}

	/**
	 * Remplit la grille dans sa situation de base pour nbJoueur joueurs
	 * @param nbJoueur Nombre de joueurs dont on doit placer les pions (2 ou 4)
	 */
	public void remplir(int nbJoueur){
		int coef = 0;
		if(nbJoueur == 4)
			coef = 1;

		for(int i = 0;i < 10;i++)
			for(int j = 0;j < 10;j++)

				if (i+j >= 14+coef && i+j < 17)
					grille[i][j] = new Pion(2);
				else if (i+j > 16)
					grille[i][j] = new PionDouble(2);
				else if (i+j < 5-coef && i+j > 1)
					grille[i][j] = new Pion(1);
				else if (i+j <=1)
					grille[i][j] = new PionDouble(1);

				else if(nbJoueur == 4 && i-j < -5 && i-j >= -7)
					grille[i][j] = new Pion(3);
				else if(nbJoueur == 4 && i-j < -7)
					grille[i][j] = new PionDouble(3);

				else if(nbJoueur == 4 && i-j > 5 && i-j < 8)
					grille[i][j] = new Pion(4);
				else if(nbJoueur == 4 && i-j >= 8)
					grille[i][j] = new PionDouble(4);

				else
					grille[i][j] = new Pion(0);
	}

	/**
	 * Affiche la grille et les pions qui s'y trouvent
	 */
	public void afficher(){
		for(int i = 0;i < 10;i++){
			for(int j = 0;j < 10;j++)
				System.out.print(" "+grille[i][j]+" ");
			System.out.println("\n");		
		}
	}
	/**
	 * Affiche la grille et les pions qui s'y trouvent et surligne un pion de coordonnées (x,y)
	 * @param x Première coordonnée du pion a surligner
	 * @param y Deuxième coordonnée du pion a surligner
	 */
	public void afficher(int x, int y){
		for(int i = 0;i < 10;i++){
			for(int j = 0;j < 10;j++){
				if(i==x && j == y)
					System.out.print( "(" + grille[i][j] + ")" );
				else
					System.out.print(" " + grille[i][j] +" ");
			}
			System.out.println("\n");		
		}
	}

	/**
	 * Affiche la grille avec les couleurs de chaque joueurs
	 * @param liste Liste des joueurs
	 */
	public void afficherEnCouleur(Joueurs liste){
		for(int i = 0;i < 10;i++){
			for(int j = 0;j < 10;j++){	
				if(grille[i][j].getP()  != 0)
					System.out.print(grille[i][j].afficherEnCouleur( liste.getNumero( grille[i][j].getP() ).getC() ) );
				else
					System.out.print(" " + grille[i][j] +" ");

			}
			System.out.println("\n");		
		}
	}


	/**
	 * Affiche la grille en couleur avec des codes ANSI et surligne le pion (x, y)
	 * @param liste Liste des joueurs avec leur couleurs
	 * @param x Première coordonnee du pion à surligner
	 * @param y Deuxième coordonnee du pion à surligner
	 */
	public void afficherEnCouleur(Joueurs liste,int x, int y){
		for(int i = 0;i < 10;i++){
			for(int j = 0;j < 10;j++){
				if(i == x && j == y)
					System.out.print(WHITE_BACKGROUND + BLACK +grille[i][j] + RESET);
				else if(grille[i][j].getP() != 0)
					System.out.print(grille[i][j].afficherEnCouleur( liste.getNumero( grille[i][j].getP() ).getC() ) );
				else
					System.out.print(" " + grille[i][j] +" ");

			}
			System.out.println("\n");		
		}
	}

	/**
	 * Renvoie la distance a parcourir pour aller du pion (x,y) au pion (x2,y2)
	 * @param x	Première coordonnée du premier pion
	 * @param y Deuxième coordonnée du premier pion
	 * @param x2 Première coordonnée du deuxième pion
	 * @param y2 Deuxième coordonnée du deuxième pion
	 * @return retourne un nombre de case entier
	 */
	public int distance(double x,double y,double x2,double y2){
		if( Math.abs(y-y2) > Math.abs(x-x2) )
			return (int)Math.abs(y-y2);
		else 
			return (int)Math.abs(x-x2);
	}

	/**
	 * Retourne si le centre est libre ou non. Si il n'y a pas de centre, renvoie false.
	 * @param x	Première coordonnée du premier pion.
	 * @param y Deuxième coordonnée du premier pion.
	 * @param x2 Première coordonnée du deuxième pion.
	 * @param y2 Deuxième coordonnée du deuxième pion.
	 * @return retourne un booleen valant true si le centre est libre.
	 */
	public boolean centreLibre(double x,double y,double x2,double y2){
		if(distance(x,y,x2,y2) != 2 /*|| ((x+x2)/2)%1 == 0 || ((y+y2)/2)%1 == 0*/) // 2 & 3 permettent de vérifier si les case sont alignées 
			return false;
		return grille[(int)((x+x2)/2)][(int)((y+y2)/2)].estLibre();
	}

	/**
	 * 
	 * @param x	Première coordonnée du premier pion.
	 * @param y Deuxième coordonnée du premier pion.
	 * @param x2 Première coordonnée du deuxième pion.
	 * @param y2 Deuxième coordonnée du deuxième pion.
	 * @param p Valeur du pion qui doit être déplacé. Représente le joueur.
	 * @throws ExceptionValeurFausse Lève une exception si le déplacement est illégal
	 */
	public void deplacer(int x,int y,int x2,int y2, int p)throws ExceptionValeurFausse{
		Pion temp = grille[x][y];

		int coef = 0;
		if(grille[x][y] instanceof PionDouble)
			coef = 1;

		//déplacement d'une case pour Pion / une ou deux cases pour PionDouble 
		if(grille[x2][y2].estLibre() && (distance(x,y,x2,y2)==1 || distance(x,y,x2,y2)==2*coef) && grille[x][y].getP() == p){   
			grille[x][y] = grille[x2][y2];
			grille[x2][y2] = temp;
		}

		//saut 1 case Pion / 1 case PionDouble
		else if (grille[x2][y2].estLibre() && !this.centreLibre(x,y,x2,y2) && distance(x,y,x2,y2) == 2 && grille[x][y].getP() == p){
			grille[x][y] = grille[x2][y2];
			grille[x2][y2] = temp;
		}	

		//Saut 2 cases PionDouble
		else if(grille[x][y] instanceof PionDouble
				&& grille[x2][y2].estLibre() 
				&& distance(x,y,x2,y2) == 3 
				&& !this.centreLibre(Math.floor((x+x2)/2d),Math.floor((y+y2)/2d),x2,y2) 
				&& !this.centreLibre(Math.ceil((x+x2)/2d),Math.ceil((y+y2)/2d),x,y)
				&& grille[x][y].getP() == p){
			grille[x][y] = grille[x2][y2];
			grille[x2][y2] = temp;
		}
		else
			throw new ExceptionValeurFausse();
	}

	/**
	 * Indique si un joueur peux sauter un pion autour de lui.
	 * @param x Première coordonnée du premier point
	 * @param y Deuxième coordonnée du premier point
	 * @return Retourne un booleen si le pion peux sauter un pion autour de lui.
	 */
	public boolean peuxSauter(int x,int y){
		//Coordonnee c = new Coordonnee(0,0);
		ArrayList<Coordonnee> l = new ArrayList<Coordonnee>();
		for(int i = x-2 ; i <= x+2 ; i+=2 )
			for(int j = y-2 ; j <= y+2 ; j+=2 )
				if(i >= 0 && j >= 0 && i < 10 && j < 10)
					if(grille[i][j].estLibre() && !this.centreLibre(i,j,x,y))
						l.add(new Coordonnee(i,j));

		if(grille[x][y] instanceof PionDouble){
			for(int i = x-3 ; i <= x+3 ; i+=3 )
				for(int j = y-3 ; j <= y+3 ; j+=3 )
					if(i >= 0 && j >= 0 && i < 10 && j < 10)
						if(grille[i][j].estLibre() 
								&& !this.centreLibre(Math.floor((i+x)/2),Math.floor((j+y)/2),i,j) 
								&& !this.centreLibre(Math.ceil((i+x)/2),Math.ceil((j+y)/2),x,y))
							l.add(new Coordonnee(i,j));
		}
		if(!l.isEmpty())
			return true;
		return false;
	}

	/**
	 * Calcule si un joueur a gagné. Si oui renvoie le numéro du gagnant.
	 * @param nbJoueur Nombre de joueurs présents sur la grille.
	 * @return Retourne un entier indiquant le numéro du joueur qui a gagné.
	 */
	public int victoire(int nbJoueur){
		int cptPion1 = 0, cptDouble1 = 0,
				cptPion2 = 0, cptDouble2 = 0,
				cptPion3 = 0, cptDouble3 = 0,
				cptPion4 = 0, cptDouble4 = 0;

		int coef = 0;
		if(nbJoueur == 4)
			coef = 1;

		for(int i = 0;i < 10;i++)
			for(int j = 0;j < 10;j++)
			{
				if (i+j >= 14+coef && i+j < 17 && grille[i][j] instanceof Pion && grille[i][j].getP() == 1)
					cptPion1++;
				else if (i+j > 16 && grille[i][j] instanceof PionDouble && grille[i][j].getP() == 1)
					cptDouble1++;

				else if (i+j < 5-coef && i+j > 1 && grille[i][j] instanceof Pion && grille[i][j].getP() == 2)
					cptPion2++;
				else if (i+j <=1 && grille[i][j] instanceof PionDouble && grille[i][j].getP() == 2)
					cptDouble2++;

				else if(nbJoueur == 4 && i-j < -5 && i-j >= -7 && grille[i][j] instanceof Pion && grille[i][j].getP() == 4)
					cptPion4++;
				else if(nbJoueur == 4 && i-j < -7 && grille[i][j] instanceof PionDouble && grille[i][j].getP() == 4)
					cptDouble4++;

				else if(nbJoueur == 4 && i-j > 5 && i-j < 8 && grille[i][j] instanceof Pion && grille[i][j].getP() == 3)
					cptPion3++;
				else if(nbJoueur == 4 && i-j >= 8 && grille[i][j] instanceof PionDouble && grille[i][j].getP() == 3)
					cptDouble3++;
			}
		if(nbJoueur == 4){
			if(cptPion1 == 7 && cptDouble1 == 3)
				return 1;
			if(cptPion2 == 7 && cptDouble2 == 3)
				return 2;
			if(cptPion3 == 7 && cptDouble3 == 3)
				return 3;
			if(cptPion4 == 7 && cptDouble4 == 3)
				return 4;
			else 
				return 0;
		}
		else{
			if(cptPion1 == 12 && cptDouble1 == 3)
				return 1;
			if(cptPion2 == 12 && cptDouble2 == 3)
				return 2;
			else 
				return 0;
		}
	}
	/**
	 * Renvoie un liste des coordonnées où un pion (x,y) peux se rendre en avancant en diagonale et vers son objectif. Sert uniquement pour l'IA.
	 * @param x Première coordonnée du premier point
	 * @param y Deuxième coordonnée du premier point
	 * @param p Numéro du pion a tester.
	 * @return Retourne une LinkedList contenant des coordonnées
	 */
	public LinkedList<Coordonnee> listeDesDiagonalesVersBut(int x, int y, int p){
		LinkedList<Coordonnee> liste = new LinkedList<Coordonnee>();

		int coef = 0;
		if(grille[x][y] instanceof PionDouble)
			coef = 1;

		if(p == 1){
			if(x+1 <= 9 && y+1 <= 9 && grille[x+1][y+1].estLibre() && x+1+y+1 < 17 + 2*coef)
				liste.addFirst(new Coordonnee(x+1, y+1));
			if(x+2 <= 9 && y+2 <= 9 && grille[x+2][y+2].estLibre() && !grille[x+1][y+1].estLibre() && x+2+y+2 < 17+2*coef)
				liste.addFirst(new Coordonnee(x+2, y+2));

			if(grille[x][y] instanceof PionDouble){      
				if(x+3 < 9 && y+3 < 9 && grille[x+3][y+3].estLibre() && !grille[x+2][y+2].estLibre() && !grille[x+1][y+1].estLibre() && x+3+y+3 >= -9)
					liste.addFirst(new Coordonnee(x+3, y+3));
			}	
		}
		else if(p == 2){
			if(x-1 >= 0 && y-1 >= 0 && grille[x-1][y-1].estLibre() && x-1+y-1 >= 2-2*coef)
				liste.add(new Coordonnee(x-1, y-1));
			if(x-2 >= 0 && y-2 >= 0 && grille[x-2][y-2].estLibre() && !grille[x-1][y-1].estLibre() && x-2+y-2 >= 2-2*coef)
				liste.addFirst(new Coordonnee(x-2, y-2));

			if(grille[x][y] instanceof PionDouble){      
				if(x-3 >= 0 && y-3 >= 0 && grille[x-3][y-3].estLibre() && !grille[x-2][y-2].estLibre() && !grille[x-1][y-1].estLibre() && x-3+y-3 >= 0)
					liste.addFirst(new Coordonnee(x-3, y-3));
			}	
		}
		else if(p == 3){
			if(x+1 <= 9 && y-1 >= 0 && grille[x+1][y-1].estLibre() && x+1 - y-1 < 7+2*coef)
				liste.add(new Coordonnee(x+1, y-1));
			if(x+2 <= 9 && y-2 >= 0 && grille[x+2][y-2].estLibre() && !grille[x+1][y-1].estLibre() && x+2 - y-2 < 7+2*coef)
				liste.add(new Coordonnee(x+2, y-2));

			if(grille[x][y] instanceof PionDouble){      
				if(x+3 <= 9 && y-3 >= 0 && grille[x+3][y-3].estLibre() && !grille[x+2][y-2].estLibre() && !grille[x+1][y-1].estLibre() && x+3 - y-3 <= 9)
					liste.add(new Coordonnee(x+3, y-3));
			}	
		}
		else if(p == 4){
			if(x-1 >= 0 && y+1 <= 9 && grille[x-1][y+1].estLibre() && x-1 - y+1 > -7-2*coef)
				liste.add(new Coordonnee(x-1, y+1));
			if(x-2 >= 0 && y+2 <= 9 && grille[x-2][y+2].estLibre() && !grille[x-1][y+1].estLibre() && x-2 - y+2 > -7-2*coef)
				liste.add(new Coordonnee(x-2, y+2));

			if(grille[x][y] instanceof PionDouble){      
				if(x-3 >= 0 && y+3 <= 9 && grille[x-3][y+3].estLibre() && !grille[x-2][y+2].estLibre() && !grille[x-1][y+1].estLibre() && x-3 - y+3 >= -9)
					liste.add(new Coordonnee(x-3, y+3));
			}	
		}
		return liste;
	}

	/**
	 * Renvoie un liste des coordonnées où un pion (x,y) peux se rendre en avancant vers son objectif. Sert uniquement pour l'IA.
	 * @param x Première coordonnée du premier point
	 * @param y Deuxième coordonnée du premier point
	 * @param p Numéro du pion a tester.
	 * @return Retourne une LinkedList contenant des coordonnées
	 */
	public LinkedList<Coordonnee> listeDesMouvementsVersBut(int x, int y, int p){
		LinkedList<Coordonnee> liste = new LinkedList<Coordonnee>();

		int coef = 0;
		if(grille[x][y] instanceof PionDouble)
			coef = 1;

		if(p == 1){
			if(y+1 <= 9 && grille[x][y+1].estLibre() && x+y+1 < 17+2*coef)
				liste.add(new Coordonnee(x, y+ 1));
			if(x+1 <= 9 && grille[x+1][y].estLibre() && x+1+y < 17+2*coef)
				liste.add(new Coordonnee(x+1, y));
			if(y+2 <= 9 && grille[x][y+2].estLibre() && !grille[x][y+1].estLibre() && x+y+2 < 17+2*coef)
				liste.add(new Coordonnee(x, y+2));
			if(x+2 <= 9 && grille[x+2][y].estLibre() && !grille[x+1][y].estLibre() && x+2+y < 17+2*coef)
				liste.add(new Coordonnee(x+2, y));

			if(grille[x][y] instanceof PionDouble){      
				if(y+3 < 9 && grille[x][y+3].estLibre() && !grille[x][y+2].estLibre() && !grille[x][y+1].estLibre() && x+y+3 >= -9)
					liste.add(new Coordonnee(x, y+3));
				if(x+3 < 9 && grille[x+3][y].estLibre() && !grille[x+2][y].estLibre() && !grille[x+1][y].estLibre() && x+3+y >= -9)
					liste.add(new Coordonnee(x+3, y));
			}	
		}
		else if(p == 2){
			if(y-1 >= 0 && grille[x][y-1].estLibre() && x+y-1 >= 2-2*coef)
				liste.add(new Coordonnee(x, y-1));
			if(x-1 >= 0 && grille[x-1][y].estLibre() && x-1+y >= 2-2*coef)
				liste.add(new Coordonnee(x-1, y));
			if(y-2 >= 0 && grille[x][y-2].estLibre() && !grille[x][y-1].estLibre() && x+y-2 >= 2-2*coef)
				liste.add(new Coordonnee(x, y-2));
			if(x-2 >= 0 && grille[x-2][y].estLibre() && !grille[x-1][y].estLibre() && x-2+y >= 2-2*coef)
				liste.add(new Coordonnee(x-2, y));

			if(grille[x][y] instanceof PionDouble){      
				if(y-3 >= 0 && grille[x][y-3].estLibre() && !grille[x][y-2].estLibre() && !grille[x][y-1].estLibre() && x+y-3 >= 0)
					liste.add(new Coordonnee(x, y-3));
				if(x-3 >= 0 && grille[x-3][y].estLibre() && !grille[x-2][y].estLibre() && !grille[x-1][y].estLibre() && x+3+y >= 0)
					liste.add(new Coordonnee(x-3, y));
			}	
		}
		else if(p == 3){
			if(y-1 >= 0 && grille[x][y-1].estLibre() && x - y-1 < 7+2*coef)
				liste.add(new Coordonnee(x, y-1));
			if(x+1 <= 9 && grille[x+1][y].estLibre() && x+1 - y < 7+2*coef)
				liste.add(new Coordonnee(x+1, y));
			if(y-2 >= 0 && grille[x][y-2].estLibre() && !grille[x][y-1].estLibre() && x - y-2 < 7+2*coef)
				liste.add(new Coordonnee(x, y-2));
			if(x+2 <= 9 && grille[x+2][y].estLibre() && !grille[x+1][y].estLibre() && x+2 - y < 7+2*coef)
				liste.add(new Coordonnee(x+2, y));

			if(grille[x][y] instanceof PionDouble){      
				if(y-3 >= 0 && grille[x][y-3].estLibre() && !grille[x][y-2].estLibre() && !grille[x][y-1].estLibre() && x - y-3 <= 9)
					liste.add(new Coordonnee(x, y-3));
				if(x+3 <= 9 && grille[x+3][y].estLibre() && !grille[x+2][y].estLibre() && !grille[x+1][y].estLibre() && x+3 - y <= 9)
					liste.add(new Coordonnee(x+3, y));
			}	
		}
		else if(p == 4){
			if(y+1 <= 9 && grille[x][y+1].estLibre() && x - y+1 > -7-2*coef)
				liste.add(new Coordonnee(x, y+1));
			if(x-1 >= 0 && grille[x-1][y].estLibre() && x-1 - y > -7-2*coef)
				liste.add(new Coordonnee(x-1, y));
			if(y+2 <= 9 && grille[x][y+2].estLibre() && !grille[x][y+1].estLibre() && x - y+2 > -7-2*coef)
				liste.add(new Coordonnee(x, y+2));
			if(x-2 >= 0 && grille[x-2][y].estLibre() && !grille[x-1][y].estLibre() && x-2 - y > -7-2*coef)
				liste.add(new Coordonnee(x-2, y));

			if(grille[x][y] instanceof PionDouble){      
				if(y+3 <= 9 && grille[x][y+3].estLibre() && !grille[x][y+2].estLibre() && !grille[x][y+1].estLibre() && x - y+3 >= -9)
					liste.add(new Coordonnee(x, y+3));
				if(x-3 >= 0 && grille[x-3][y].estLibre() && !grille[x-2][y].estLibre() && !grille[x-1][y].estLibre() && x-3 - y >= -9)
					liste.add(new Coordonnee(x-3, y));
			}	
		}
		return liste;
	}

	/**
	 * Parcours la grille en diagonale depuis le camps du pion jusqu'au camps visé et déplace le pion le plus éloigné vers le camps.
	 * @param p Numéro du pion à vérifier.
	 * @return Retourne un boolean qui indique si un mouvement a été opéré.
	 */
	public boolean deplacerPionIA(int p){
		Coordonnee c = new Coordonnee(0,0);
		boolean deplacementFait = false;

		if(p == 1){

			for( int i = 0 ; i < 10 ; i++ ) {
				for( int j = 0 ; j <= i ; j++ ) {
					int k = i - j;

					if(grille[k][j].getP() == p && !deplacementFait){
						if(!this.listeDesDiagonalesVersBut(k,j,p).isEmpty()){
							c = this.listeDesDiagonalesVersBut(k,j,p).getFirst();
							try{
								this.deplacer(k, j, c.getX(), c.getY(), p);
							}catch(ExceptionValeurFausse e){System.out.println("erreur"+p);}
							deplacementFait = true;
						}
					}
				}
			}
			for( int i = 10 - 2 ; i >= 0 ; i-- ) {
				for( int j = 0 ; j <= i ; j++ ) {
					int k = i - j;
					int X = 10-j-1, Y = 10-k-1;

					if(grille[X][Y].getP() == p && !deplacementFait){
						if(!this.listeDesDiagonalesVersBut(X,Y,p).isEmpty()){
							c = this.listeDesDiagonalesVersBut(X,Y,p).getFirst();
							try{
								this.deplacer(X, Y, c.getX(), c.getY(), p);
							}catch(ExceptionValeurFausse e){System.out.println("erreur"+p);}
							deplacementFait = true;
						}
					}
				}
			}
		}
		else if(p==2){

			for( int i = 0 ; i < 10 ; i++ ) {
				for( int j = 0 ; j <= i ; j++ ) {
					int k = i - j;
					int X = 10-j-1, Y = 10-k-1;
					if(grille[X][Y].getP() == p && !deplacementFait){
						if(!this.listeDesDiagonalesVersBut(X,Y,p).isEmpty()){
							c = this.listeDesDiagonalesVersBut(X,Y,p).getFirst();
							try{
								this.deplacer(X, Y, c.getX(), c.getY(), p);
							}catch(ExceptionValeurFausse e){System.out.println("erreur"+p);}
							deplacementFait = true;
						}
					}
				}
			}
			for( int i = 10 - 2 ; i >= 0 ; i-- ) {
				for( int j = 0 ; j <= i ; j++ ) {
					int k = i - j;
					if(grille[k][j].getP() == p && !deplacementFait){
						if(!this.listeDesDiagonalesVersBut(k,j,p).isEmpty()){
							c = this.listeDesDiagonalesVersBut(k,j,p).getFirst();
							try{
								this.deplacer(k, j, c.getX(), c.getY(), p);
							}catch(ExceptionValeurFausse e){System.out.println("erreur"+p);}
							deplacementFait = true;
						}
					}
				}
			}
		}

		if(p == 3){
			for( int i = 0 ; i < 10 ; i++ ) {
				for( int j = 0 ; j <= i ; j++ ) {
					int k = i - j;
					int Y = 10-j-1;
					if(grille[k][Y].getP() == p && !deplacementFait){
						if(!this.listeDesDiagonalesVersBut(k,Y,p).isEmpty()){
							c = this.listeDesDiagonalesVersBut(k,Y,p).getFirst();
							try{
								this.deplacer(k, Y, c.getX(), c.getY(), p);
							}catch(ExceptionValeurFausse e){System.out.println("erreur"+p);}
							deplacementFait = true;
						}
					}			
				}
			}

			for( int i = 10 - 2 ; i >= 0 ; i-- ) {
				for( int j = 0 ; j <= i ; j++ ) {
					int k = i - j;
					int Y = 10-j-1;
					if(grille[Y][k].getP() == p && !deplacementFait){
						if(!this.listeDesDiagonalesVersBut(Y,k,p).isEmpty()){
							c = this.listeDesDiagonalesVersBut(Y,k,p).getFirst();
							try{
								this.deplacer(Y, k, c.getX(), c.getY(), p);
							}catch(ExceptionValeurFausse e){System.out.println("erreur"+p);}
							deplacementFait = true;
						}
					}	
				}
			}
		}

		if(p == 4){
			for( int i = 0 ; i < 10 ; i++ ) {
				for( int j = 0 ; j <= i ; j++ ) {
					int k = i - j;
					int Y = 10-k-1;
					if(grille[Y][j].getP() == p && !deplacementFait){
						if(!this.listeDesDiagonalesVersBut(Y,j,p).isEmpty()){
							c = this.listeDesDiagonalesVersBut(Y,j,p).getFirst();
							try{
								this.deplacer(Y, j, c.getX(), c.getY(), p);
							}catch(ExceptionValeurFausse e){System.out.println("erreur"+p);}
							deplacementFait = true;
						}
					}
				}
			}

			for( int i = 10 - 2 ; i >= 0 ; i-- ) {
				for( int j = 0 ; j <= i ; j++ ) {
					int k = i - j;
					int Y = 10-k-1;
					if(grille[j][Y].getP() == p && !deplacementFait){
						if(!this.listeDesDiagonalesVersBut(j,Y,p).isEmpty()){
							c = this.listeDesDiagonalesVersBut(j,Y,p).getFirst();
							try{
								this.deplacer(j, Y, c.getX(), c.getY(), p);
							}catch(ExceptionValeurFausse e){System.out.println("erreur"+p);}
							deplacementFait = true;
						}
					}		
				}
			}
		}
		return deplacementFait;
	}

	/**
	 * Indique si le pion se trouve a la bonne place 
	 * @param x Première Coordonnee du pion
	 * @param y Deuxième Coordonnee du pion
	 * @param nbJoueur Nombre de Joueurs sur la grille
	 * @return boolean
	 */
	public boolean estBonnePlace(int x, int y , int nbJoueur){
		boolean bonnePlace = false;

		int coef = 0;
		if(nbJoueur == 4)
			coef = 1;

		if (x+y >= 14+coef && x+y < 17 && this.getPion(x, y).getP() == 1 && this.getPion(x, y) instanceof Pion)
			bonnePlace = true;
		else if (x+y > 16 && this.getPion(x, y).getP() == 1 && this.getPion(x, y) instanceof PionDouble)
			bonnePlace = true;

		else if (x+y < 5-coef && x+y > 1&& this.getPion(x, y).getP() == 2 && this.getPion(x, y) instanceof Pion)
			bonnePlace = true;
		else if (x+y <=1 && this.getPion(x, y).getP() == 2 && this.getPion(x, y) instanceof PionDouble)
			bonnePlace = true;

		else if(nbJoueur == 4 && x-y < -5 && x-y >= -7 && this.getPion(x, y).getP() == 4 && this.getPion(x, y) instanceof Pion)
			bonnePlace = true;
		else if(nbJoueur == 4 && x-y < -7 && this.getPion(x, y).getP() == 4 && this.getPion(x, y) instanceof PionDouble)
			bonnePlace = true;

		else if(nbJoueur == 4 && x-y > 5 && x-y < 8&& this.getPion(x, y).getP() == 3 && this.getPion(x, y) instanceof Pion)
			bonnePlace = true;
		else if(nbJoueur == 4 && x-y >= 8 && this.getPion(x, y).getP() == 3 && this.getPion(x, y) instanceof PionDouble)
			bonnePlace = true;
		return bonnePlace;
	}

	/**
	 * Renvoie la liste de tous les mouvements qu'un pion peux faire
	 * @param x Première Coordonnee du pion
	 * @param y Deuxième Coordonnee du pion
	 * @param p Numéro du pion.
	 * @return LinkedList<Coordonnee>
	 */
	public LinkedList<Coordonnee> listeDesMouvements(int x, int y, int p){
		LinkedList<Coordonnee> l = new LinkedList<Coordonnee>();

		for(int i = x-1 ; i <= x+1 ; i++ )
			for(int j = y-1 ; j <= y+1 ; j++ )
				if(i >= 0 && j >= 0 && i < 10 && j < 10)
					if(grille[i][j].estLibre())
						l.addFirst(new Coordonnee(i,j));

		for(int i = x-2 ; i <= x+2 ; i+=2 )
			for(int j = y-2 ; j <= y+2 ; j+=2 )
				if(i >= 0 && j >= 0 && i < 10 && j < 10)
					if(grille[i][j].estLibre() && !this.centreLibre(i,j,x,y))
						l.addFirst(new Coordonnee(i,j));

		if(grille[x][y] instanceof PionDouble){
			for(int i = x-3 ; i <= x+3 ; i+=3 )
				for(int j = y-3 ; j <= y+3 ; j+=3 )
					if(i >= 0 && j >= 0 && i < 10 && j < 10)
						if(grille[i][j].estLibre() 
								&& !this.centreLibre(Math.floor((i+x)/2),Math.floor((j+y)/2),i,j) 
								&& !this.centreLibre(Math.ceil((i+x)/2),Math.ceil((j+y)/2),x,y))
							l.addFirst(new Coordonnee(i,j));
		}
		return l;

	}


	/**
	 * Débloque l'IA si elle ne peut plus avancer vers le but en diagonale.
	 * @param p Numéro du pion a débloquer.
	 * @return Retourne un boolean qui indique si un mouvement a été opéré.
	 */
	public boolean debloquerIA(int p, int nbJoueur) {

		Coordonnee c = new Coordonnee(0,0);
		boolean deplacementFait = false;

		for(int i = 0;i < 10;i++){
			for(int j = 0;j < 10;j++){
				if(grille[i][j].getP() == p && !deplacementFait){
					if(!this.listeDesMouvementsVersBut(i,j,p).isEmpty()){
						c = this.listeDesMouvementsVersBut(i,j,p).getFirst();
						try{
							this.deplacer(i, j, c.getX(), c.getY(), p);
						}catch(ExceptionValeurFausse e){System.out.println("erreur");}
						deplacementFait = true;
					}
				}
			}
		}
		if(!deplacementFait)
			for(int i = 0;i < 10;i++){
				for(int j = 0;j < 10;j++){
					if(grille[i][j].getP() == p && !estBonnePlace(i, j, nbJoueur))
						if(!this.listeDesMouvements(i,j,p).isEmpty()){
							c = this.listeDesMouvements(i,j,p).getFirst();
							try{
								this.deplacer(i, j, c.getX(), c.getY(), p);
							}catch(ExceptionValeurFausse e){System.out.println("erreur");}
							deplacementFait = true;
						}
				}
			}
		return deplacementFait;
	}



}


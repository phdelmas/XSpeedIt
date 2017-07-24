import java.util.*;

/**
 * @author pdelmas
 *
 * XspeedIt est une soci�t� d'import / export ayant robotis� toute sa cha�ne d'emballage de colis.
 * Elle souhaite trouver un algorithme permettant � ses robots d'optimiser le nombre de cartons d'emballage utilis�s.

 * Les articles � emballer sont de taille variable, repr�sent�e par un entier compris entre 1 et 9.
 * Chaque carton a une capacit� de contenance de 10.
 * Ainsi, un carton peut par exemple contenir un article de taille 3, un article de taille 1, et un article de taille 6.

 * La cha�ne d'articles � emballer est repr�sent�e par une suite de chiffres, chacun repr�sentant un article par sa taille.
 * Apr�s traitement par le robot d'emballage, la cha�ne est s�par�e par des "/" pour repr�senter les articles contenus dans un carton.
 * 
 * Impl�mentation ICI d'un algorithme permettant de maximiser le nombre d'articles par carton:
 */
public class XSpeedIt {
	
	private static int tailleCarton = 10;
	private static String chaineSeparateur = "/";

    public static void main(String[] args) {
    	
    	String chaineArticlesEmballes = emballer("163841689525773");
    	System.out.println(chaineArticlesEmballes);
    }
    
    private static String emballer(String chaineArticles) {
    	
    	/*
    	 * On transforme la chaine d'articles en un tableau tri� par ordre de taille
    	 */
    	char[] cArticles = chaineArticles.toCharArray();
    	int[] iArticles = new int[cArticles.length];
    	for (int i = 0; i < cArticles.length; i++) {
    		iArticles[i] = Character.getNumericValue(cArticles[i]);
    	}
    	Arrays.sort(iArticles);

    	/*
    	 * On initialise les tableaux cartons et tailleCartons:
    	 *   cartons: Chaque carton contiendra une chaine repr�sentant les articles qu'il contient
    	 *   tailleCartons : taille restant libre de chaque carton.
    	 */
       	String[] cartons = new String[iArticles.length];
        int[] tailleCartons = new int[iArticles.length];
        for (int i = 0; i < tailleCartons.length; i++) {
        	tailleCartons[i] = tailleCarton;
        	cartons[i] = "";
        }

        /*
         * Pour chaque article, en commencant par le plus grand, on parcourt les cartons et on le place
         * dans le 1er carton qui a assez de place et on r�duit la taille du carton.
         */
        for (int i=iArticles.length-1; i >= 0; i--) {
        	for (int j = 0; j < tailleCartons.length; j++) {
 				if (tailleCartons[j] >= iArticles[i]) {
					tailleCartons[j] -= iArticles[i];
					cartons[j] += String.valueOf(iArticles[i]);
				   	break;
				}
			}
        }
        
        /*
         * On construit la chaine d'articles emball�s.
         */
    	String chaineArticlesEmballes = "";
        boolean bFirst = true;
        for (String carton : cartons){
           	if (carton == "") break;
        	if (bFirst) {
        		bFirst = false;
        	} else {
        		chaineArticlesEmballes += chaineSeparateur;
        	}
        	chaineArticlesEmballes += carton;
        }
    	
        
        return chaineArticlesEmballes;

    }
    
}

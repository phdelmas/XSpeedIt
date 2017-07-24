import java.util.*;

/**
 * @author pdelmas
 *
 * XspeedIt est une société d'import / export ayant robotisé toute sa chaîne d'emballage de colis.
 * Elle souhaite trouver un algorithme permettant à ses robots d'optimiser le nombre de cartons d'emballage utilisés.

 * Les articles à emballer sont de taille variable, représentée par un entier compris entre 1 et 9.
 * Chaque carton a une capacité de contenance de 10.
 * Ainsi, un carton peut par exemple contenir un article de taille 3, un article de taille 1, et un article de taille 6.

 * La chaîne d'articles à emballer est représentée par une suite de chiffres, chacun représentant un article par sa taille.
 * Après traitement par le robot d'emballage, la chaîne est séparée par des "/" pour représenter les articles contenus dans un carton.
 * 
 * Implémentation ICI d'un algorithme permettant de maximiser le nombre d'articles par carton:
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
    	 * On transforme la chaine d'articles en un tableau trié par ordre de taille
    	 */
    	char[] cArticles = chaineArticles.toCharArray();
    	int[] iArticles = new int[cArticles.length];
    	for (int i = 0; i < cArticles.length; i++) {
    		iArticles[i] = Character.getNumericValue(cArticles[i]);
    	}
    	Arrays.sort(iArticles);

    	/*
    	 * On initialise les tableaux cartons et tailleCartons:
    	 *   cartons: Chaque carton contiendra une chaine représentant les articles qu'il contient
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
         * dans le 1er carton qui a assez de place et on réduit la taille du carton.
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
         * On construit la chaine d'articles emballés.
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

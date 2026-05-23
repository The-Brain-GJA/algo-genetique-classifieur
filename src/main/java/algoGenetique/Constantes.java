package algoGenetique;

import java.awt.Color;

public class Constantes {

	private Constantes() {
	}
	
	public static final Color[] listeCouleurs = { Color.BLUE, Color.CYAN, Color.RED, Color.GREEN, Color.GRAY };

	
	/**
	 * Renvoie une couleur en utilisant un modulo
	 */
	public static Color getCouleur(int i) {
		return listeCouleurs[i % listeCouleurs.length];
	}
	
	
}

package outils;

public class GenerationAleatoire {

	/**
	 * Renvoie un nombre entre -amplitude et +amplitude
	 */
	public static double random(double amplitude) {
		return amplitude * (2 * Math.random() - 1);
	}
	
}

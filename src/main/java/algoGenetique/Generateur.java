package algoGenetique;

import java.util.Arrays;
import java.util.function.ToDoubleFunction;

/**
 * Classe qui gère les calculs de valeurs
 */
public class Generateur {

	GraineEvaluable graines[];
	
	public Generateur(double[] graines, int nbEchantillons, int nbSimulations, ToDoubleFunction<Graine> evaluation) {
		this.graines = new GraineEvaluable[nbEchantillons];
		
		for (int i=0; i<this.graines.length; i++) {
			this.graines[i] = new GraineEvaluable(evaluation, graines);
		}
	}
	
	
	@Override
		public String toString() {
			return Arrays.toString(graines);
		}
	
}

package algoGenetique;

import java.util.Arrays;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;

/**
 * Classe qui gère les calculs de valeurs
 */
public class Generateur {

	GraineEvaluable graines[];
	ParametresGenerateur parametres;
	final int indiceDepart;

	public static final ToDoubleBiFunction<Graine, Double> POLYNOME =
			(g, x) -> g.get(0) * Math.pow(x, 3) + g.get(1) * Math.pow(x, 2) + g.get(2) * x + g.get(3); 
	
	private static double MIN_X = -15;
	private static double MIN_Y = -15;
	private static double MAX_X = 15;
	private static double MAX_Y = 20;
	
	public static Generateur getGenerateurParametresInitiaux(double[] valeurs, ParametresGenerateur parametre) {
		return new Generateur(valeurs, parametre, EVALUATION_PARAMETRES_INIT);
	}

	public static Generateur getGenerateur(double[] valeurs, ParametresGenerateur parametre, ToDoubleFunction<Graine> evaluation) {
		return new Generateur(valeurs, parametre, evaluation);
	}
	
	/**
	 * En début de simulation, le but est d'avoir F(MIN_X) = MIN_Y et F(MAX_X) = MAX_Y
	 * On additionne le carré des différences
	 */
	public static final ToDoubleFunction<Graine> EVALUATION_PARAMETRES_INIT = 
			g -> Math.pow(POLYNOME.applyAsDouble(g, MIN_X) - MIN_Y, 2)
				+ Math.pow(POLYNOME.applyAsDouble(g, MAX_X) - MAX_Y, 2);
			
	private Generateur(double[] graines, ParametresGenerateur parametres, ToDoubleFunction<Graine> evaluation) {
		this.graines = new GraineEvaluable[parametres.getNbGraines()];
		this.parametres = parametres;
		this.indiceDepart = parametres.getNbGraines() * parametres.getPourcentageGrainesConservees() / 100;

		for (int i=0; i<this.graines.length; i++) {
			this.graines[i] = new GraineEvaluable(evaluation, graines.clone());
		}
	}

	/**
	 * Lancement de toutes les itérations
	 */
	public GraineEvaluable simulation() {
		for(int i=0; i<parametres.getNbSimulations(); i++) {
			iteration();
		}
		return graines[0];
	}
	
	/**
	 * On garde les 10% les meilleures au cas où (les premières de la liste), et on fait évoluer les autres
	 */
	public void iteration() {
		for(int i=indiceDepart; i<graines.length; i++) {
			graines[i].secouer(parametres.getAmplitudeIteration());
		}
		Arrays.sort(graines);
	}
	
	@Override
		public String toString() {
			return Arrays.toString(graines);
		}
	
}

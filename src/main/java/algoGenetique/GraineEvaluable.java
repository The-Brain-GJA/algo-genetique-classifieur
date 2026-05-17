package algoGenetique;

import java.util.Arrays;
import java.util.function.ToDoubleFunction;

/**
 * Liste de paramètres à calculer
 */
public class GraineEvaluable extends Graine implements Comparable<GraineEvaluable> {

	private ToDoubleFunction<Graine> fonctionEvaluation;
	private double evaluation = Double.MAX_VALUE;
	
	public GraineEvaluable(ToDoubleFunction<Graine> fonctionEvaluation, double... valeurs) {
		super(valeurs);
		this.fonctionEvaluation = fonctionEvaluation;
		evaluer();
	}

	private void evaluer() {
		this.evaluation = fonctionEvaluation.applyAsDouble(this);
	}
	
	@Override
	public int compareTo(GraineEvaluable o) {
		return Double.valueOf(evaluation).compareTo(o.evaluation);
	}

	@Override
	public String toString() {
		return super.toString() + " : " + evaluation;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	//	public void setValeurs(double... valeurs) {
	//		if(valeurs.length != this.valeurs.length) {
	//			throw new IllegalArgumentException(String.format("valeurs.length = %d, param.length = %d" + this.valeurs.length, valeurs.length));
	//		}
	//		this.valeurs = valeurs;
	//	}

	public double getEvaluation() {
		return evaluation;
	}
	
	/**
	 * Uniquement pour les TU ?
	 */
	public void setEvaluation(double valeur) {
		this.evaluation = valeur;
	}

}

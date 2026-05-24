package algoGenetique;

import java.util.function.ToDoubleFunction;

import outils.GenerationAleatoire;

/**
 * Liste de paramètres avec une évaluation
 */
public class GraineEvaluable extends Graine implements Comparable<GraineEvaluable> {

	public static int nbGraines = 0;
	
	private ToDoubleFunction<Graine> fonctionEvaluation;
	private double evaluation = Double.MAX_VALUE;
	
	public GraineEvaluable(ToDoubleFunction<Graine> fonctionEvaluation, double... valeurs) {
		super(valeurs);
		this.fonctionEvaluation = fonctionEvaluation;
		evaluer();
		nbGraines++;
	}

	public GraineEvaluable(ToDoubleFunction<Graine> fonctionEvaluation, Graine graine) {
		this(fonctionEvaluation, graine.getValeurs());
	}

	public void secouer(double amplitude) {
		//double seuil = 0;
		for (int i=0; i<nbGraines(); i++) {
			double r = GenerationAleatoire.random(amplitude);
			//if(r < seuil) {
				modifierElement(i, getGraine()[i] + GenerationAleatoire.random(amplitude));
			//}
		}
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
	
	public double getEvaluation() {
		return evaluation;
	}
	
	public ToDoubleFunction<Graine> getFonctionEvaluation() {
		return fonctionEvaluation;
	}

	public void setFonctionEvaluation(ToDoubleFunction<Graine> fonctionEvaluation) {
		this.fonctionEvaluation = fonctionEvaluation;
		this.evaluer();
	}

	@Override
	public GraineEvaluable clone() {
		return new GraineEvaluable(fonctionEvaluation, getValeurs());
	}
	
	
}

package test;

import java.util.function.ToDoubleFunction;

import algoGenetique.Graine;
import algoGenetique.GraineEvaluable;

public class MockGraineEvaluable extends GraineEvaluable {

	public MockGraineEvaluable(ToDoubleFunction<Graine> fonctionEvaluation, double[] valeurs) {
		super(fonctionEvaluation, valeurs);
	}

	@Override
	public void secouer(double amplitude) {
	}
}

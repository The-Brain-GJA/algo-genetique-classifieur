package test;

import java.util.function.ToDoubleFunction;

import algoGenetique.Graine;
import algoGenetique.GraineEvaluable;
import algoGenetique.ParametresGenerateur;
import algoGenetique.Simulation;

public class MockSimulation extends Simulation {

	public MockSimulation(int origine, ParametresGenerateur parametres, Graine graineInitiale) {
		super(parametres, graineInitiale);
		
		ToDoubleFunction<Graine> f1 = (g) -> 1 + origine;
		ToDoubleFunction<Graine> f2 = (g) -> 0 + origine;
		ToDoubleFunction<Graine> f3 = (g) -> 3 + origine;
		ToDoubleFunction<Graine> f4 = (g) -> 0.5 + origine;

		GraineEvaluable g1 = new MockGraineEvaluable(f1, new double[] {0, 0, 0, 0});
		GraineEvaluable g2 = new MockGraineEvaluable(f2, new double[] {0, 0, 0, 0});
		GraineEvaluable g3 = new MockGraineEvaluable(f3, new double[] {0, 0, 0, 0});
		GraineEvaluable g4 = new MockGraineEvaluable(f4, new double[] {0, 0, 0, 0});
		
		setGraines(new GraineEvaluable[] { g1, g2, g3, g4 });
		
		setIndiceDepart(1);
	}
	
}

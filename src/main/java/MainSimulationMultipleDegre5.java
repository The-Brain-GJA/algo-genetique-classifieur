
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.function.ToDoubleBiFunction;

import algoGenetique.Graine;
import algoGenetique.ParametresGenerateur;
import algoGenetique.Simulation;
import algoGenetique.SimulationMultiGraines;
import outils.Timer;

public class MainSimulationMultipleDegre5 {
	
	/*
	 * set datafile separator comma
	 * plot 'file_15.csv' skip 1 using 1:2
	 * 
	 * plot [-20:20] 0.005247306472041999*x**3 + 0.009761759614494965*x**2 + 0.013858369778036842*x + 0.320430570024019
	 * 
	 */

	// Tester : equation du 5ème degré
	

	public static void main(String[] args) {
		
		Timer timer = Timer.getTimer("Algo génétique polynome degré 5");
		
		System.out.println("Test simulation");
		
		ParametresGenerateur parametres = new ParametresGenerateur();
		parametres.setNbSimulations(1);
		parametres.setNbGraines(100);
		parametres.setPourcentageGrainesConservees(50);
		parametres.setNbIterations(1_000);
		parametres.setAmplitudeIteration(0.01);
		parametres.setFrequenceAffichageIterations(20);
		parametres.setPointsX(new double[] {-15, 15});
		parametres.setPointsY(new double[] {-15, 20});
		parametres.setAffichage(true);
		
		parametres.setCouleurCourbe(Color.BLUE);
		parametres.setCouleurPoints(Color.GRAY);
		
		ToDoubleBiFunction<Graine, Double> courbe =
				(g, x) -> g.get(0) * Math.pow(x, 3) + g.get(1) * Math.pow(x, 2) + g.get(2) * x + g.get(3)
					+ g.get(4) * Math.pow(x, 4) + g.get(5) * Math.pow(x, 5); 

		parametres.setCourbe(courbe);

		
		double[] init1 = {0, 1, 1, 1, 0, 0};
		double[] init2 = {0, 0, 0, 1, 0, 0};
		double[] init3 = {1, 1, 1, 1, -0.01, -0.01};
		
		Graine[] init = new Graine[] {new Graine(init1), new Graine(init2), new Graine(init3)};
		
		SimulationMultiGraines simulation = new SimulationMultiGraines(parametres, init);
		
		timer.afficher();
		
		simulation.simulation();
		
		DecimalFormat df = new DecimalFormat("###,###,###");
		System.out.println("nbSimulations : " + df.format(Simulation.nbSimulations));
		System.out.println("Meilleure graine : " + simulation.graineEnTete());
		
		for(int i=0; i<init.length; i++) {
			System.out.println("Graine " + i + " : " + simulation.getSimulations()[i].graineEnTete().getEvaluation());
		}
//		
//		Generateur gen = Generateur.getGenerateurAvecParametresInitiaux(init, parametres);
//		GraineEvaluable graine = gen.simulation();
//		System.out.println(graine);
//
//		//Graine g = new Graine(init);
//		System.out.println("Init : " + parametres.getFonctionEvaluation().applyAsDouble(graine));
//		System.out.println("F(-20) = " + parametres.getCourbe().applyAsDouble(graine, -20.0));
//		System.out.println("F(20) = " + parametres.getCourbe().applyAsDouble(graine, 20.0));
//		
		timer.afficher();
	}
}

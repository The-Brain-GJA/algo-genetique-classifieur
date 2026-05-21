
import java.awt.Color;
import java.text.DecimalFormat;

import algoGenetique.Graine;
import algoGenetique.GraineEvaluable;
import algoGenetique.ParametresGenerateur;
import algoGenetique.Simulation;
import algoGenetique.SimulationMultiGraines;
import outils.Timer;

public class MainSimulationMultipleDegre3 {
	
	/*
	 * set datafile separator comma
	 * plot 'file_15.csv' skip 1 using 1:2
	 * 
	 * plot [-20:20] 0.005247306472041999*x**3 + 0.009761759614494965*x**2 + 0.013858369778036842*x + 0.320430570024019
	 * 
	 */

	// Tester : equation du 5ème degré
	

	public static void main(String[] args) {
		
		Timer timer = Timer.getTimer("Algo génétique");
		
		System.out.println("Test simulation");
		
		ParametresGenerateur parametres = new ParametresGenerateur();
		parametres.setNbSimulations(1);
		parametres.setNbGraines(100);
		parametres.setPourcentageGrainesConservees(50);
		parametres.setNbIterations(1_000);
		parametres.setAmplitudeIteration(0.10);
		parametres.setFrequenceAffichageIterations(20);
		parametres.setPointsX(new double[] {-15, 15});
		parametres.setPointsY(new double[] {-15, 20});
		parametres.setAffichage(true);
		
		parametres.setCouleurCourbe(Color.BLUE);
		parametres.setCouleurPoints(Color.GRAY);
		
//		ToDoubleBiFunction<Graine, Double> courbe =
//				(g, x) -> g.get(0) * Math.pow(x, 3) + g.get(1) * Math.pow(x, 2) + g.get(2) * x + g.get(3)
//					+ g.get(4) * Math.pow(x, 4) + g.get(5) * Math.pow(x, 5); 
//
//		ToDoubleFunction<Graine> fonctionEvaluation = 
//					g -> Math.pow(courbe.applyAsDouble(g, xmin) - ymin, 2)
//						+ Math.pow(courbe.applyAsDouble(g, xmax) - ymax, 2);
//
//		parametres.modifierFonction(courbe, fonctionEvaluation);

		
		Graine[] init = new Graine[] {
	    		new Graine(new double[] {10,  10, 10, 10}),
	    		new Graine(new double[] {1, 1, 1, 1}),
	    		new Graine(new double[] {0.5, 0.5, 0.5, 0.5}),
	    		new Graine(new double[] {0, 0, 0, 0})
			};
		
		SimulationMultiGraines simulation = new SimulationMultiGraines(parametres, init);
		
		simulation.simulation();
		
		var mg = simulation.graineEnTete();
		System.out.println("Meilleure graine " + mg.getX() + " " + mg.getY());
		//System.out.println("Graine initiale : " + mg.getY().);
		
		DecimalFormat df = new DecimalFormat("###,###,###");
		System.out.println("nbSimulations : " + df.format(Simulation.nbSimulations));
		System.out.println("nbGraines : " + df.format(GraineEvaluable.nbGraines));
		System.out.println("Meilleure graine : " + simulation.graineEnTete());

		for(int i=0; i<init.length; i++) {
			System.out.println("Graine " + i + " : " + simulation.getSimulations()[i].graineEnTete());
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

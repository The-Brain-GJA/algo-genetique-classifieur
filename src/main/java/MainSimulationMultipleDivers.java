
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.function.ToDoubleBiFunction;

import algoGenetique.Graine;
import algoGenetique.GraineEvaluable;
import algoGenetique.ParametresGenerateur;
import algoGenetique.Simulation;
import algoGenetique.SimulationMultiGraines;
import outils.Timer;

public class MainSimulationMultipleDivers {
	
	/*
	 * set datafile separator comma
	 * plot 'file_15.csv' skip 1 using 1:2
	 * 
	 * plot [-20:20] 0.005247306472041999*x**3 + 0.009761759614494965*x**2 + 0.013858369778036842*x + 0.320430570024019
	 * 
	 */

	// Tester : equation du 3ème degré
	

	public static void main(String[] args) {
		
		Timer timer = Timer.getTimer("Algo génétique");
		
		System.out.println("Test simulation");
		
		ParametresGenerateur parametres = new ParametresGenerateur();
		parametres.setNbSimulations(10);
		parametres.setNbGraines(10);
		parametres.setPourcentageGrainesConservees(20);
		parametres.setNbIterations(10_000);
		parametres.setAmplitudeIteration(1.10);
		parametres.setFrequenceAffichageIterations(20);
		parametres.setPointsX(new double[] {-15, 15});
		parametres.setPointsY(new double[] {-15, 30});
		parametres.setAffichage(true);
		
		parametres.setCouleurCourbe(Color.RED);
		parametres.setCouleurPoints(Color.BLUE);
		
		ToDoubleBiFunction<Graine, Double> courbe = (g, x) -> g.get(0) * x + g.get(1); 
//
//		ToDoubleFunction<Graine> fonctionEvaluation = 
//					g -> Math.pow(courbe.applyAsDouble(g, xmin) - ymin, 2)
//						+ Math.pow(courbe.applyAsDouble(g, xmax) - ymax, 2);
//
		parametres.setCourbe(courbe);

		//XXX
		// voir pourquoi ça marche moyen alors que c'est facile !
		
		Graine[] init = new Graine[] {
	    		new Graine(new double[] {-0.04, -0.58}),
//	    		new Graine(new double[] {-1, -1, -1, -1, -1, -1, -1}),
//	    		new Graine(new double[] {0.006, -0.2, -0.001, 0.03, -0.02, 0.10, 10.09}),
//	    		new Graine(new double[] {0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5}),
	    		new Graine(new double[] {0, 0})
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


import java.awt.Color;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.function.ToDoubleBiFunction;

import algoGenetique.Graine;
import algoGenetique.GraineEvaluable;
import algoGenetique.ParametresGenerateur;
import algoGenetique.Simulation;
import algoGenetique.SimulationGraine;
import algoGenetique.SimulationMultiGraines;
import outils.Timer;

public class MainSimulationMultipleCosinus {
	
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
		parametres.setNbGraines(1);
		parametres.setPourcentageGrainesConservees(20);
		parametres.setNbIterations(1_000);
		parametres.setAmplitudeIteration(0.1);
		parametres.setFrequenceAffichageIterations(20);
		double xmin = -15;
		double ymin = -15;
		double xmax = 15;
		double ymax = 20;
		parametres.setMinX(xmin);
		parametres.setMinY(ymin);
		parametres.setMaxX(xmax);
		parametres.setMaxY(ymax);
		parametres.setAffichage(true);
		
		parametres.setPasCourbe(0.5);
		parametres.setCouleurCourbe(Color.BLUE);
		parametres.setCouleurPoints(Color.GRAY);
		
		ToDoubleBiFunction<Graine, Double> courbe = (g, x) -> g.get(0) * Math.cos(g.get(1) + g.get(2) * x) + g.get(3); 

		parametres.setCourbe(courbe);


		Graine[] init = new Graine[] {
    		new Graine(new double[] {20, 20, 20, 20}),
    		new Graine(new double[] {10,  10, 10, 10}),
    		new Graine(new double[] {30, 0, 10, 10}),
    		new Graine(new double[] {10, 0, 20, 20})
		};
		
		
		SimulationMultiGraines simulation = new SimulationMultiGraines(parametres, init);
		
		simulation.simulation();
		
		var mg = simulation.graineEnTete();
		System.out.println("Meilleure graine " + mg.getX() + " " + mg.getY());
		//System.out.println("Graine initiale : " + mg.getY().);
		
		DecimalFormat df = new DecimalFormat("###,###,###");
		System.out.println("nbSimulations : " + df.format(Simulation.nbSimulations));
		System.out.println("nbGraines : " + df.format(GraineEvaluable.nbGraines));
		System.out.println("nbSimulationGraine : " + df.format(SimulationGraine.nbSimulationsGraine));
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

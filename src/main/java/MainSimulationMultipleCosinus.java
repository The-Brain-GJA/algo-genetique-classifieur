
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.function.ToDoubleBiFunction;

import algoGenetique.Graine;
import algoGenetique.GraineEvaluable;
import algoGenetique.ParametresGenerateur;
import outils.Timer;
import simulation.Simulation;
import simulation.SimulationGraine;
import simulation.SimulationMultiGraines;

public class MainSimulationMultipleCosinus {
	
	/*
	 * set datafile separator comma
	 * plot 'file_15.csv' skip 1 using 1:2
	 * 
	 * plot [-20:20] 0.005247306472041999*x**3 + 0.009761759614494965*x**2 + 0.013858369778036842*x + 0.320430570024019
	 * 
	 */

	public static void main(String[] args) {
		
		Timer timer = Timer.getTimer("Algo génétique");
		
		System.out.println("Test simulation");
		
		ParametresGenerateur parametres = new ParametresGenerateur();
		parametres.setNbSimulations(10);
		parametres.setNbGraines(10);
		parametres.setPourcentageGrainesConservees(20);
		parametres.setNbIterations(60_000);
		parametres.setAmplitudeIteration(0.001);
		parametres.setFrequenceAffichageIterations(20);
		parametres.setPointsX(new double[] {-15, -1, 15});
		parametres.setPointsY(new double[] {-15, 10, 20});
		parametres.setMinX(-40);
		parametres.setMinY(-40);
		parametres.setAffichage(true);
		parametres.setEchelle(5);
		parametres.setFrequenceAffichage(100);
		
		parametres.setPasCourbe(0.5);
		parametres.setCouleurCourbe(Color.BLUE);
		parametres.setCouleurPoints(Color.GRAY);
		
		ToDoubleBiFunction<Graine, Double> courbe = (g, x) -> g.get(0) * Math.cos(g.get(1) + 1 / g.get(2) * x)
				+ g.get(3); 

		parametres.setCourbe(courbe);

		Graine[] init = new Graine[] {
    		new Graine(new double[] {20, 20, 20, 20}),
    		new Graine(new double[] {10,  10, 10, 10}),
    		new Graine(new double[] {30, 0, 10, 10}),
    		new Graine(new double[] {10, 0, 20, 20})
		};
		
		Graine g = new Graine(new double[] {10, 0, 20, 20});
		System.out.println("F(graine) = " + courbe.applyAsDouble(g, 15.0));
		
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

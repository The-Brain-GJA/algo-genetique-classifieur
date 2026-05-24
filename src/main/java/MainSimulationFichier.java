
import java.awt.Color;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.text.DecimalFormat;
import java.util.function.ToDoubleBiFunction;

import algoGenetique.Graine;
import algoGenetique.GraineEvaluable;
import algoGenetique.ParametresGenerateur;
import outils.Timer;
import simulation.Simulation;
import simulation.SimulationCluster;
import simulation.SimulationGraine;

public class MainSimulationFichier {
	
	/*
	 * set datafile separator comma
	 * plot 'file_15.csv' skip 1 using 1:2
	 * 
	 * plot [-20:20] 0.005247306472041999*x**3 + 0.009761759614494965*x**2 + 0.013858369778036842*x + 0.320430570024019
	 * 
	 */

	// TODO 1 : faire sur plusieurs processeurs

	

	public static void main(String[] args) throws IOException {
		
		IO.println("Début de " + MethodHandles.lookup().lookupClass().getName());
		Timer timer = Timer.getTimer(MethodHandles.lookup().lookupClass().getName());
		
		ParametresGenerateur parametres = new ParametresGenerateur();
		parametres.setNbSimulations(10);
		parametres.setNbGraines(10);
		parametres.setPourcentageGrainesConservees(20);
		parametres.setNbIterations(1_000);
		parametres.setAmplitudeIteration(0.001);
		parametres.setFrequenceAffichageIterations(20);
		parametres.setMinX(-30);
		parametres.setMinY(-30);
		parametres.setAffichage(true);
		parametres.setEchelle(10);
		parametres.setFrequenceAffichage(100);
		
		parametres.setPasCourbe(0.5);
		parametres.setCouleurCourbe(Color.BLUE);
		parametres.setCouleurPoints(Color.GRAY);

		final String nomFichiercluster = "file_1.csv";

//		Graine 0 : 26.5, 24.28, 14.09, -5.93
//		Graine 1 : 19.6, 11.42, 8.44, 4.14
//		Graine 2 : 28.7, -1.33, 6.09, 7.81
//		Graine 3 : 22.5, -0.96, 11.86, -1.40

		ToDoubleBiFunction<Graine, Double> courbe = (g, x) -> g.get(0) * Math.cos(g.get(1) + 1 / g.get(2) * x)
				+ g.get(3); 

		parametres.setCourbe(courbe);
		
		
		Graine[] init = new Graine[] {
	    		new Graine(new double[] {20, 20, 20, 20}),
	    		new Graine(new double[] {10,  10, 10, 10}),
	    		new Graine(new double[] {30, 0, 10, 10}),
	    		new Graine(new double[] {10, 0, 20, 20})
			};


		SimulationCluster simulation = new SimulationCluster(parametres, nomFichiercluster, init);
		
		simulation.simulation();

		DecimalFormat df = new DecimalFormat("###,###,###");
		System.out.println("nbSimulations : " + df.format(Simulation.nbSimulations));
		System.out.println("nbGraines : " + df.format(GraineEvaluable.nbGraines));
		System.out.println("nbSimulationGraine : " + df.format(SimulationGraine.nbSimulationsGraine));
		System.out.println("Meilleure graine : " + simulation.graineEnTete());

		for(int i=0; i<init.length; i++) {
			System.out.println("Graine " + i + " : " + simulation.getSimulations()[i].graineEnTete());
		}

		timer.afficher();
	}
}

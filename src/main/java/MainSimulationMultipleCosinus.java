
import java.awt.Color;
import java.text.DecimalFormat;
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
		parametres.setNbSimulations(10);
		parametres.setNbGraines(10);
		parametres.setPourcentageGrainesConservees(20);
		parametres.setNbIterations(10_000);
		parametres.setAmplitudeIteration(0.01);
		parametres.setFrequenceAffichageIterations(20);
		parametres.setPointsX(new double[] {-15, 15});
		parametres.setPointsY(new double[] {-15, 20});
		parametres.setAffichage(true);
		parametres.setFrequenceAffichage(100);
		
		parametres.setPasCourbe(0.5);
		parametres.setCouleurCourbe(Color.BLUE);
		parametres.setCouleurPoints(Color.GRAY);
		
		ToDoubleBiFunction<Graine, Double> courbe = (g, x) -> g.get(0) * Math.cos(g.get(1) + 1 / g.get(2) * x) + g.get(3); 

		parametres.setCourbe(courbe);

		/*
		 Graine 0 : [28.192957899060378, 23.0187299685631, 18.491626179443973, 12.535398402380386] : 9.262036461542419E-20
		Graine 1 : [17.690366851062148, 10.872570803419617, 10.06438097038432, 2.6742979469337613] : 1.2524323971649263E-20
		Graine 2 : [30.141623863031587, -0.6535262973475092, 8.012220294667099, 9.602647491613164] : 2.303527994736714E-16
		Graine 3 : [23.46106474720827, -1.9434961366452423, 16.149913610612177, 7.61551709868336] : 9.537705458889097E-18
		Graine 4 : [30.237583547515978, -0.6519315947587457, 7.997247738288775, 9.71446930482905] : 1.1770735946365126E-17

		 */

		Graine[] init = new Graine[] {
    		new Graine(new double[] {20, 20, 20, 20}),
    		new Graine(new double[] {10,  10, 10, 10}),
    		new Graine(new double[] {30, 0, 10, 10}),
    		new Graine(new double[] {10, 0, 20, 20}),
    		new Graine(new double[] {30.245548044248075, -0.6516739049527546, 7.998569213396938, 9.709059371245282})
		};
		
		// Graine 2 : [29.946279236447847, -0.6577327043627059, 8.027119716996502, 9.4545855281383] : 1.8994155153878666E-6
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

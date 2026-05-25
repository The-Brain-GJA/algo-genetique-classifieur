
import java.awt.Color;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.text.DecimalFormat;
import java.util.function.ToDoubleBiFunction;

import algoGenetique.Graine;
import algoGenetique.GraineEvaluable;
import algoGenetique.ParametresGenerateur;
import mathematiques.Fonctions;
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
		parametres.setNbIterations(10_000);
		parametres.setFrequenceAffichageIterations(20);
		parametres.setMinX(-30);
		parametres.setMinY(-30);
		parametres.setAffichage(true);
		parametres.setEchelle(10);
		parametres.setFrequenceAffichage(100);
		parametres.setAmplitudeIteration(0.1);
		
		parametres.setPasCourbe(0.5);
		parametres.setCouleurCourbe(Color.BLUE);
		parametres.setCouleurPoints(Color.GRAY);

		final String nomFichiercluster = "file_1.csv";

		//ToDoubleBiFunction<Graine, Double> courbe = Fonctions.POLYNOME_COSINUS;
		ToDoubleBiFunction<Graine, Double> courbe = Fonctions.POLYNOME_2;

		parametres.setCourbe(courbe);
		
		
//		Graine[] init = new Graine[] {
//				new Graine(new double[] {0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}),
//				new Graine(new double[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}),
//				new Graine(new double[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}),
//				new Graine(new double[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}),
//				new Graine(new double[] {0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5}),
//				new Graine(new double[] {-0.5, -0.5, -0.5, -0.5, -0.5, -0.5, -0.5, -0.5, -0.5, -0.5, -0.5, -0.5, -0.5, -0.5})
//		};

		Graine[] init = new Graine[] {
				new Graine(new double[] {-0.885840105384148, -0.1291725741451275, 0.09861407326177973, -0.38865329125634385, -0.7139749986870063, -0.3037209983318325, -0.9482674423447375, 0.31849429577843824, 0.9143462449313358, 5.6100454186044235, 0.5496824689454471, 1.140017226070003, 0.6502955187138333, 3.597013518275369}),
				new Graine(new double[] {0.31983151509687846, 0.15891223659934206, 2.6078445161663746, -0.19062215589807097, -0.2517452634409072, -0.5782274836495602, 0.9027163531201603, -0.3634158663190634, 1.7339178009440304, 4.590893344243815, -0.9290800788531322, 4.161959080449979, 3.7249003188407888, 6.185216280392278}),
				new Graine(new double[] {0.9753314458424489, -0.08232894989655803, -0.6437208303596835, 0.09409566820069015, -0.05932669676919611, -0.31288564484858905, -0.9922020757467986, -0.043941940966547355, 2.3409574237437427, 7.90779739631747, -1.8247090599980609, 2.2554600763679997, 4.715107858203334, 4.215677622488605}),
				new Graine(new double[] {-0.9027437749503409, -0.006827886906118685, 0.015562503838889352, 0.3389363802467147, 3.7545290352505436, 0.17209997227561002, 0.7117410161884665, 0.42789038987662376, 0.7766387994576003, 4.596883182351451, -5.112348594623025, 3.4771943090452013, 0.4309564484922872, 0.559497408260448}),
				new Graine(new double[] {-0.017874889515331338, -0.07812399474688747, -2.4363574981155383, -0.037794731354894084, -1.4315879340689364, -0.06400043260798172, 0.07839304156225757, 0.0888689054155396, 1.4100732879496631, 2.2342628453675273, 4.472812756240176, 0.716601281145721, 0.4068892566011877, 4.463309553012776}),
				new Graine(new double[] {-0.0726951988830773, 0.1151101721299368, -0.6084269562955349, -0.03053322782167478, -0.08271943411819072, 0.29541370068775374, -1.6118335210624362, 0.13098755463990264, 1.3984831599832555, 5.535124499745167, 3.2836571544177815, -0.49781520911374244, -0.33400682686438343, 1.5306831500436986})
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

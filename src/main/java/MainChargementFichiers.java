
import java.awt.Color;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.List;

import affichage.DessinCluster;
import affichage.Ecran;
import algoGenetique.ParametresGenerateur;
import fichiers.LectureCluster;
import mathematiques.SimulationCluster;
import outils.Timer;

public class MainChargementFichiers {
	
	/*
	 * set datafile separator comma
	 * plot 'file_15.csv' skip 1 using 1:2
	 * 
	 * plot [-20:20] 0.005247306472041999*x**3 + 0.009761759614494965*x**2 + 0.013858369778036842*x + 0.320430570024019
	 * 
	 */

	

	public static void main(String[] args) throws IOException {
		
		IO.println("Début de " + MethodHandles.lookup().lookupClass().getName());
		Timer timer = Timer.getTimer(MethodHandles.lookup().lookupClass().getName());
		
		ParametresGenerateur parametres = new ParametresGenerateur();
		parametres.setNbSimulations(10);
		parametres.setNbGraines(10);
		parametres.setPourcentageGrainesConservees(20);
		parametres.setNbIterations(60_000);
		parametres.setAmplitudeIteration(0.001);
		parametres.setFrequenceAffichageIterations(20);
		parametres.setPointsX(new double[] {-15, -1, 15});
		parametres.setPointsY(new double[] {-15, 10, 20});
		parametres.setMinX(-30);
		parametres.setMinY(-30);
		parametres.setAffichage(true);
		parametres.setEchelle(10);
		parametres.setFrequenceAffichage(100);
		
		parametres.setPasCourbe(0.5);
		parametres.setCouleurCourbe(Color.BLUE);
		parametres.setCouleurPoints(Color.GRAY);

		final String nomFichiercluster = "file_1.csv";

		SimulationCluster simulation = new SimulationCluster(parametres, nomFichiercluster);
		
		
		//simulation.
		
		timer.afficher();
	}
}

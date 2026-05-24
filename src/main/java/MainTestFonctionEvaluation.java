
import java.awt.geom.Point2D;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.List;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;

import algoGenetique.Graine;
import algoGenetique.GraineEvaluable;
import algoGenetique.ParametresGenerateur;
import fichiers.LectureCluster;
import outils.Timer;
import simulation.Simulation;

public class MainTestFonctionEvaluation {
	
	public static void main(String[] args) throws IOException {
		
		Timer timer = Timer.getTimer(MethodHandles.lookup().lookupClass().getName());
		
		final String nomFichiercluster = "file_1.csv";


		ToDoubleBiFunction<Graine, Double> courbe = (g, x) -> g.get(0) * Math.cos(g.get(1) + 1 / g.get(2) * x)
				+ g.get(3); 

		LectureCluster lectureCluster = new LectureCluster();
		List<Point2D[]> listePoints = lectureCluster.lire(nomFichiercluster);


		ToDoubleFunction<Graine> fonctionEvaluation = 
				g -> {
					double valeur = 0;
					
					// Tous les points du cluster 1 doivent être au dessus de la courbe
					// Si points au dessus : 0
					// Si points en dessous : différence au carré
					for (Point2D point : listePoints.get(0)) {
						//valeur += parametres.getCourbe().applyAsDouble(g, point.getX()) <= point.getY() ? -1 : 1;
						double f_x = courbe.applyAsDouble(g, point.getX());
						if(point.getY() < f_x) {
							valeur += Math.pow(point.getY() - f_x, 2);
						}
					}

					// Tous les points du cluster 2 doivent être en dessous de la courbe
					for (Point2D point : listePoints.get(1)) {
						//valeur += parametres.getCourbe().applyAsDouble(g, point.getX()) >= point.getY() ? -1 : 1;
						double f_x = courbe.applyAsDouble(g, point.getX());
						if(point.getY() > f_x) {
							valeur += Math.pow(point.getY() - f_x, 2);
						}
					}

				return valeur;
		};

		Graine g = new Graine(new double[] {10,  10, 10, 10});
	
		ParametresGenerateur parametre = new ParametresGenerateur(2, 10, 1);
		
		Simulation s = new Simulation(parametre, g);
		s.majFonctionEvaluation(fonctionEvaluation);
		
		IO.println(Arrays.toString(s.getGraines()));

		for(int i=0; i<10; i++) {
			s.iteration();
			//IO.println(s.getGraines()[0]);
			IO.println(Arrays.toString(s.getGraines()));
		}
		
		System.out.println("nbGraines : " + GraineEvaluable.nbGraines);

		timer.afficher();
	}
}

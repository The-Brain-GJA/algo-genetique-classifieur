package simulation;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleUnaryOperator;
import java.util.function.ToDoubleFunction;

import org.apache.commons.lang3.ArrayUtils;

import affichage.DessinCluster;
import affichage.DessinCourbe;
import affichage.DessinRepere;
import affichage.ObjetDessin;
import algoGenetique.Constantes;
import algoGenetique.Graine;
import algoGenetique.ParametresGenerateur;
import fichiers.LectureCluster;

public class SimulationCluster extends SimulationMultiGraines {

	List<Point2D[]> listePoints;
	
	public SimulationCluster(ParametresGenerateur parametres, String nomFichier,  Graine... graines) throws IOException {
		super(parametres, graines);
		LectureCluster lectureCluster = new LectureCluster();
		listePoints = lectureCluster.lire(nomFichier);
		// XXX à changer ?
		if(listePoints.size() > parametres.getNbClustersMax()) {
			// fusionnerListes(0, 2); // OK
			fusionnerListes(1, 2); // OK si évaluation inversée
			//fusionnerListes(0, 1); // OK si évaluation inversée
		}
		majFonctionEvaluation();
	}

	// XXX pour le cas avec n > 2 classes : pour chaque classe, considérer tous les autres tableaux comme une seule classe
	private void majFonctionEvaluation() {
		ToDoubleFunction<Graine> fonctionEvaluation = 
				g -> {
					double valeur = 0;
					
					// Tous les points du cluster 1 doivent être au dessus de la courbe
					// Si points au dessus : 0
					// Si points en dessous : différence au carré
					for (Point2D point : listePoints.get(0)) {
						//valeur += parametres.getCourbe().applyAsDouble(g, point.getX()) <= point.getY() ? -1 : 1;
						double f_x = parametres.getCourbe().applyAsDouble(g, point.getX());
						if(point.getY() > f_x) {
							valeur += Math.pow(point.getY() - f_x, 2);
						}
					}

					// Tous les points du cluster 2 doivent être en dessous de la courbe
					for (Point2D point : listePoints.get(1)) {
						//valeur += parametres.getCourbe().applyAsDouble(g, point.getX()) >= point.getY() ? -1 : 1;
						double f_x = parametres.getCourbe().applyAsDouble(g, point.getX());
						if(point.getY() <= f_x) {
							valeur += Math.pow(point.getY() - f_x, 2);
						}
					}

					return valeur;
				};
		parametres.setFonctionEvaluation(fonctionEvaluation);
		
		IO.println("Début de la MAJ de la fonction");
		for (SimulationGraine simulation : simulationsMultiGraines) {
			simulation.majFonctionEvaluation(fonctionEvaluation);
		}
		IO.println("Fin de la MAJ de la fonction");
		
		// XXX descendre en cascade sur toutes les graines ?
		// XXX ou remonter la fonction au dessus des graines ?
	}
		
	/**
	 * Déplacement de la liste de l'indice2 dans celle de l'indice 2
	 */
	private void fusionnerListes(int indice1, int indice2) {
		Point2D[] liste1 = listePoints.get(indice1);
		Point2D[] liste2 = listePoints.get(indice2);
		listePoints.remove(indice1);
		listePoints.remove(indice2-1);
		listePoints.add(ArrayUtils.addAll(liste1, liste2));
	}
	
	@Override
	void dessinerCourbes() {
		if(!parametres.isAffichage()) {
			return;
		}
		
		DoubleUnaryOperator[] fonctions = new DoubleUnaryOperator[simulationsMultiGraines.length];
		
		for(int i=0; i<fonctions.length; i++) {
			Graine g = simulationsMultiGraines[i].graineEnTete();
			fonctions[i] = x -> parametres.getCourbe().applyAsDouble(g, x);
		}

		List<ObjetDessin> dessins = new ArrayList<>();
		
		dessins.add(new DessinRepere(ecran, parametres.getCouleurRepere()));

		if(listePoints != null) {
			dessins.addAll(DessinCluster.getDessinCluster(ecran, listePoints));
		}

		
		dessins.add(new DessinRepere(ecran, parametres.getCouleurRepere()));
		
		for(int i=0; i<fonctions.length; i++) {
			dessins.add(new DessinCourbe(ecran, fonctions[i], Constantes.getCouleur(i), parametres.getPasCourbe()));
		}
		
		ecran.dessiner(dessins);
	}
	
	
}

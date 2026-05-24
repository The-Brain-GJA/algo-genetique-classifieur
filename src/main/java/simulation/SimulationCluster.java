package simulation;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleUnaryOperator;

import org.apache.commons.lang3.ArrayUtils;

import affichage.DessinCluster;
import affichage.DessinCourbe;
import affichage.DessinRepere;
import affichage.Ecran;
import affichage.ObjetDessin;
import algoGenetique.Constantes;
import algoGenetique.Graine;
import algoGenetique.ParametresGenerateur;
import fichiers.LectureCluster;

public class SimulationCluster {

	ParametresGenerateur parametres;
	SimulationGraine simulationsMultiGraines[];
	Ecran ecran;
	List<Point2D[]> listePoints;
	
	public SimulationCluster(ParametresGenerateur parametres, String nomFichier,  Graine... graines) throws IOException {
		this.parametres = parametres;
		LectureCluster lectureCluster = new LectureCluster();
		listePoints = lectureCluster.lire(nomFichier);
		simulationsMultiGraines = new SimulationGraine[graines.length];
		for(int i=0; i<graines.length; i++) {
			simulationsMultiGraines[i] = new SimulationGraine(parametres, graines[i], i);
		}
		// XXX à changer ?
		if(listePoints.size() > parametres.getNbClustersMax()) {
			fusionnerListes(0, 2);
		}
		if(parametres.isAffichage()) {
			this.ecran = new Ecran(parametres.getLargeurEcran(), parametres.getHauteurEcran(), parametres.getMinX(), parametres.getMinY(), parametres.getEchelle());
			dessinerCourbes();
		}
	}

	public void simulation() {
		int nbIterations = parametres.getNbIterations();
		for(int i=0; i<nbIterations; i++) {
			for(int j=0; j<simulationsMultiGraines.length; j++) {
				simulationsMultiGraines[j].iteration();
				if(i % parametres.getFrequenceAffichage() == 0) {
					dessinerCourbes();
				}
			}
			if(i > 0 && i % (nbIterations * parametres.getFrequenceAffichageIterations() / 100) == 0) {
				System.out.println("Traitement de l'itération " + i + " / " + nbIterations);
			}
		}
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
		dessins.addAll(DessinCluster.getDessinCluster(ecran, listePoints));

		
		dessins.add(new DessinRepere(ecran, parametres.getCouleurRepere()));
		for(int i=0; i<fonctions.length; i++) {
			dessins.add(new DessinCourbe(ecran, fonctions[i], Constantes.getCouleur(i), parametres.getPasCourbe()));
		}
		ecran.dessiner(dessins);
	}
	
	
}

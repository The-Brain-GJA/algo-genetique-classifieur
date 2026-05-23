package mathematiques;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import affichage.DessinCluster;
import affichage.DessinRepere;
import affichage.Ecran;
import affichage.ObjetDessin;
import algoGenetique.ParametresGenerateur;
import fichiers.LectureCluster;

public class SimulationCluster {

	ParametresGenerateur parametres;
	Ecran ecran;
	List<double[]> listePoints;
	
	public SimulationCluster(ParametresGenerateur parametres, String nomFichier) throws IOException {
		this.parametres = parametres;
		LectureCluster lectureCluster = new LectureCluster();
		listePoints = lectureCluster.lire(nomFichier);
		if(parametres.isAffichage()) {
			this.ecran = new Ecran(parametres.getLargeurEcran(), parametres.getHauteurEcran(), parametres.getMinX(), parametres.getMinY(), parametres.getEchelle());
			dessiner();
		}
	}
	
	private void dessiner() {
		if(!parametres.isAffichage()) {
			return;
		}
		
//		DoubleUnaryOperator[] fonctions = new DoubleUnaryOperator[simulationsMultiGraines.length];
//		
//		for(int i=0; i<fonctions.length; i++) {
//			Graine g = simulationsMultiGraines[i].graineEnTete();
//			fonctions[i] = x -> parametres.getCourbe().applyAsDouble(g, x);
//		}

		Point2D[] points = new Point2D[parametres.getPointsX().length];
		for(int i=0; i<parametres.getPointsX().length; i++) {
			points[i] = new Point2D.Double(parametres.getPointsX()[i], parametres.getPointsY()[i]);
		}

		
		List<ObjetDessin> dessins = new ArrayList<>();
		dessins.add(new DessinRepere(ecran, parametres.getCouleurRepere()));
		dessins.addAll(DessinCluster.getDessinCluster(ecran, listePoints));
//		for(int i=0; i<fonctions.length; i++) {
//			dessins.add(new Courbe(ecran, fonctions[i], listeCouleurs[i % listeCouleurs.length], parametres.getPasCourbe()));
//		}
		ecran.dessiner(dessins);
	}
	
	
	
}

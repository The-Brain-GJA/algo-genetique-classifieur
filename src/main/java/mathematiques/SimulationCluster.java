package mathematiques;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import affichage.DessinCluster;
import affichage.DessinRepere;
import affichage.Ecran;
import affichage.ObjetDessin;
import algoGenetique.ParametresGenerateur;
import fichiers.LectureCluster;

public class SimulationCluster {

	ParametresGenerateur parametres;
	Ecran ecran;
	List<Point2D[]> listePoints;
	
	public SimulationCluster(ParametresGenerateur parametres, String nomFichier) throws IOException {
		this.parametres = parametres;
		LectureCluster lectureCluster = new LectureCluster();
		listePoints = lectureCluster.lire(nomFichier);
		// XXX à changer
		if(listePoints.size() > parametres.getNbClustersMax()) {
			fusionnerListes(0, 2);
		}
		IO.println(listePoints.size());
		if(parametres.isAffichage()) {
			this.ecran = new Ecran(parametres.getLargeurEcran(), parametres.getHauteurEcran(), parametres.getMinX(), parametres.getMinY(), parametres.getEchelle());
			dessiner();
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
	
	private void dessiner() {
		if(!parametres.isAffichage()) {
			return;
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

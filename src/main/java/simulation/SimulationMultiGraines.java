package simulation;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.DoubleUnaryOperator;

import affichage.DessinCluster;
import affichage.DessinCourbe;
import affichage.DessinRepere;
import affichage.Ecran;
import affichage.ObjetDessin;
import algoGenetique.Graine;
import algoGenetique.GraineEvaluable;
import algoGenetique.ParametresGenerateur;
import outils.Pair;

/**
 * Gestion d'une simulation à partir de plusieurs graines
 */
public class SimulationMultiGraines {

	protected SimulationGraine simulationsMultiGraines[];
	protected ParametresGenerateur parametres;
	protected Ecran ecran;
	protected Color[] listeCouleurs;
	
	public SimulationMultiGraines(ParametresGenerateur parametres, Graine... graines) {
		this.parametres = parametres;
		simulationsMultiGraines = new SimulationGraine[graines.length];
		for(int i=0; i<graines.length; i++) {
			simulationsMultiGraines[i] = new SimulationGraine(parametres, graines[i], i);
		}
		if(parametres.isAffichage()) {
			this.ecran = new Ecran(parametres.getLargeurEcran(), parametres.getHauteurEcran(), parametres.getMinX(), parametres.getMinY(), parametres.getEchelle());
			listeCouleurs = parametres.getListeCouleurs();
			dessinerCourbes();
		}
		// XXX
		//executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		System.out.println("(multi) Création de " + simulationsMultiGraines.length + " simulations sur " + Runtime.getRuntime().availableProcessors() + " processeurs pour " + graines.length + " graines");
	}
	
	// XXX une graine par processeur, avec l'écran en paramètre
	// XXX arrêter si calcul < 0.1
	
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

	public Pair<Integer,GraineEvaluable> graineEnTete() {
		SimulationGraine meilleureSimulation = List.of(simulationsMultiGraines)
				.stream()
				.sorted(Comparator.comparing(SimulationGraine::graineEnTete))
				.findFirst().orElse(null);
		return Pair.of(meilleureSimulation.getNumero(), meilleureSimulation.graineEnTete());
	}
	
	/**
	 * Dessine une courbe par simulation
	 */
	void dessinerCourbes() {
		if(!parametres.isAffichage()) {
			return;
		}
		
		DoubleUnaryOperator[] fonctions = new DoubleUnaryOperator[simulationsMultiGraines.length];
		
		for(int i=0; i<fonctions.length; i++) {
			Graine g = simulationsMultiGraines[i].graineEnTete();
			fonctions[i] = x -> parametres.getCourbe().applyAsDouble(g, x);
		}

		Point2D[] points = new Point2D[parametres.getPointsX().length];
		for(int i=0; i<parametres.getPointsX().length; i++) {
			points[i] = new Point2D.Double(parametres.getPointsX()[i], parametres.getPointsY()[i]);
		}

		
		List<ObjetDessin> dessins = new ArrayList<>();
		dessins.add(new DessinRepere(ecran, parametres.getCouleurRepere()));
		dessins.add(new DessinCluster(ecran, points, parametres.getCouleurPoints()));
		for(int i=0; i<fonctions.length; i++) {
			dessins.add(new DessinCourbe(ecran, fonctions[i], listeCouleurs[i % listeCouleurs.length], parametres.getPasCourbe()));
		}
		ecran.dessiner(dessins);
	}

	public SimulationGraine[] getSimulations() {
		return simulationsMultiGraines;
	}
	
	
}

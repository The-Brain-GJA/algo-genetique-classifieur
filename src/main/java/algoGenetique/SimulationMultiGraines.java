package algoGenetique;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.DoubleUnaryOperator;

import affichage.Cluster;
import affichage.Courbe;
import affichage.Ecran;
import affichage.ObjetDessin;
import affichage.Repere;
import outils.Pair;

/**
 * Gestion d'une simulation à partir de plusieurs graines
 */
public class SimulationMultiGraines {

	SimulationGraine simulations[];
	ParametresGenerateur parametres;
	Ecran ecran;
	
	public SimulationMultiGraines(ParametresGenerateur parametres, Graine... graines) {
		this.parametres = parametres;
		simulations = new SimulationGraine[graines.length];
		for(int i=0; i<graines.length; i++) {
			simulations[i] = new SimulationGraine(parametres, graines[i], i);
		}
		if(parametres.isAffichage()) {
			this.ecran = new Ecran(parametres.getLargeurEcran(), parametres.getHauteurEcran(), parametres.getMinX(), parametres.getMinY(), parametres.getEchelle());
			dessinerCourbes();
		}
		// XXX
		System.out.println("(multi) Création de " + simulations.length + " simulations");
	}
	
	// XXX une graine par processeur
	// XXX arrêter si calcul < 0.1
	
	public void simulation() {
		for(int i=0; i<parametres.getNbSimulations(); i++) {
			for(int j=0; j<simulations.length; j++) {
				simulations[j].iteration();
				dessinerCourbes();
			}
		}
	}

	public Pair<Integer,GraineEvaluable> graineEnTete() {
		SimulationGraine meilleureSimulation = List.of(simulations)
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

		final Color[] listeCouleurs = { Color.BLUE, Color.CYAN, Color.GREEN, Color.RED, Color.GRAY }; 
		
		DoubleUnaryOperator[] fonctions = new DoubleUnaryOperator[simulations.length];
		
		for(int i=0; i<fonctions.length; i++) {
			Graine g = simulations[i].graineEnTete();
			fonctions[i] = x -> parametres.getCourbe().applyAsDouble(g, x);
		}
		 
		Point2D[] points = {
				new Point2D.Double(parametres.getMinX(), parametres.getMinY()),
				new Point2D.Double(parametres.getMaxX(), parametres.getMaxY())
		};
		List<ObjetDessin> dessins = new ArrayList<>();
		dessins.add(new Repere(ecran, parametres.getCouleurRepere()));
		dessins.add(new Cluster(ecran, points, parametres.getCouleurPoints()));
		for(int i=0; i<fonctions.length; i++) {
			dessins.add(new Courbe(ecran, fonctions[i], listeCouleurs[i % listeCouleurs.length]));
		}
		ecran.dessiner(dessins);
	}

	public SimulationGraine[] getSimulations() {
		return simulations;
	}
	
	
}

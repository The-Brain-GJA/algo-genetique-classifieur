package algoGenetique;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.List;
import java.util.function.DoubleUnaryOperator;
import java.util.function.ToDoubleFunction;

import affichage.Cluster;
import affichage.Courbe;
import affichage.Ecran;
import affichage.ObjetDessin;
import affichage.Repere;

/**
 * Classe qui gère les calculs de valeurs
 */
public class Generateur {

	GraineEvaluable graines[];
	ParametresGenerateur parametres;
	final int indiceDepart;
	Ecran ecran;
	
	public static Generateur getGenerateurAvecParametresInitiaux(double[] valeurs, ParametresGenerateur parametre) {
		return new Generateur(valeurs, parametre, parametre.getFonctionEvaluation());
	}

	public static Generateur getGenerateur(double[] valeurs, ParametresGenerateur parametre, ToDoubleFunction<Graine> evaluation) {
		return new Generateur(valeurs, parametre, evaluation);
	}

	private Generateur(double[] graines, ParametresGenerateur parametres, ToDoubleFunction<Graine> evaluation) {
		this.graines = new GraineEvaluable[parametres.getNbGraines()];
		this.parametres = parametres;
		this.indiceDepart = parametres.getNbGraines() * parametres.getPourcentageGrainesConservees() / 100;

		for (int i=0; i<this.graines.length; i++) {
			this.graines[i] = new GraineEvaluable(evaluation, graines);
		}
		
		// Affichage
		if(parametres.isAffichage()) {
			this.ecran = new Ecran(parametres.getLargeurEcran(), parametres.getHauteurEcran(), parametres.getMinX(), parametres.getMinY(), parametres.getEchelle());
			dessinerCourbe();
		}
	}

	void dessinerCourbe() {
		if(!parametres.isAffichage()) {
			return;
		}

		DoubleUnaryOperator fonction = x -> parametres.getCourbe().applyAsDouble(graines[0], x);
		Point2D[] points = {
				new Point2D.Double(parametres.getMinX(), parametres.getMinY()),
				new Point2D.Double(parametres.getMaxX(), parametres.getMaxY())
		};
		List<ObjetDessin> dessins = List.of(
				new Repere(ecran, parametres.getCouleurRepere()),
				new Courbe(ecran, fonction, parametres.getCouleurCourbe(), parametres.getPasCourbe()),
				new Cluster(ecran, points, parametres.getCouleurPoints()) 
		);
		ecran.dessiner(dessins);
	}
	
	/**
	 * Lancement de toutes les itérations
	 */
	public GraineEvaluable simulation() {
		for(int i=0; i<parametres.getNbIterations(); i++) {
			iteration();
			dessinerCourbe();
		}
		return graines[0];
	}
	
	/**
	 * On garde les 10% les meilleures au cas où (les premières de la liste), et on fait évoluer les autres
	 */
	public void iteration() {
		for(int i=indiceDepart; i<graines.length; i++) {
			graines[i].secouer(parametres.getAmplitudeIteration());
		}
		Arrays.sort(graines);
	}
	
	@Override
		public String toString() {
			return Arrays.toString(graines);
		}
	
}

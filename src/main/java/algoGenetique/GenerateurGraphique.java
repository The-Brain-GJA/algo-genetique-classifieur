package algoGenetique;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.List;
import java.util.function.DoubleUnaryOperator;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;

import affichage.Cluster;
import affichage.Courbe;
import affichage.Ecran;
import affichage.ObjetDessin;
import affichage.Repere;
import affichage.TypePointCluster;

/**
 * Classe qui gère les calculs de valeurs
 */
public class GenerateurGraphique {

	private static final int LARGEUR_ECRAN = 800;
	private static final int HAUTEUR_ECRAN = 600;
	
	GraineEvaluable graines[];
	ParametresGenerateurGraphique parametres;
	final int indiceDepart;
	Ecran ecran;
	
	//public final static double[] INIT_POLYNOME = {0.005247306472041999, 0.009761759614494965, -0.013858369778036842, 0.320430570024019};
			
	public static GenerateurGraphique getGenerateurAvecParametresInitiaux(double[] valeurs, ParametresGenerateurGraphique parametre) {
		return new GenerateurGraphique(valeurs, parametre, parametre.getFonctionEvaluation());
	}

	public static GenerateurGraphique getGenerateur(double[] valeurs, ParametresGenerateurGraphique parametre, ToDoubleFunction<Graine> evaluation) {
		return new GenerateurGraphique(valeurs, parametre, evaluation);
	}
				
	private GenerateurGraphique(double[] graines, ParametresGenerateurGraphique parametres, ToDoubleFunction<Graine> evaluation) {
		this.graines = new GraineEvaluable[parametres.getNbGraines()];
		this.parametres = parametres;
		this.indiceDepart = parametres.getNbGraines() * parametres.getPourcentageGrainesConservees() / 100;

		for (int i=0; i<this.graines.length; i++) {
			this.graines[i] = new GraineEvaluable(evaluation, graines.clone());
		}
		
		// Affichage
		this.ecran = new Ecran(LARGEUR_ECRAN, HAUTEUR_ECRAN, parametres.getMinX(), parametres.getMinY(), 10);
		dessinerCourbe();
	}

	void dessinerCourbe() {
		// XXX fonction à mettre dans les paramètres
		DoubleUnaryOperator fonction = x -> Generateur.POLYNOME.applyAsDouble(graines[0], x);
		Point2D[] points = {
				new Point2D.Double(parametres.getMinX(), parametres.getMinY()),
				new Point2D.Double(parametres.getMaxX(), parametres.getMaxY())
		};
		List<ObjetDessin> dessins = List.of(
				new Repere(ecran),
				new Courbe(ecran, fonction),
				new Cluster(ecran, points, Color.BLUE) 
		);
		ecran.dessiner(dessins);
	}
	
	/**
	 * Lancement de toutes les itérations
	 */
	public GraineEvaluable simulation() {
		for(int i=0; i<parametres.getNbSimulations(); i++) {
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

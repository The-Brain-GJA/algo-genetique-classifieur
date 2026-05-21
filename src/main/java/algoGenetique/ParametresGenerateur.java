package algoGenetique;

import java.awt.Color;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;

import outils.Pair;

public class ParametresGenerateur {

	private double amplitudeIteration = 1.0;
	private int nbGraines = 100; // Nombre de graines pour une simulation
	private int nbSimulations = 100;
	private int pourcentageGrainesConservees = 10;
	private int nbIterations = 10_000;

	private int frequenceAffichageIterations = 10; // En pourcentage
	
	// Ecran
	private boolean affichage = false;
	private int largeurEcran = 800;
	private final int hauteurEcran = 600;
	private int echelle = 10;
	private double pointsX[] = {-15, 0, 15};
	private double pointsY[] = {-15, 10, 20};
	
	
	private Color couleurCourbe = Color.RED;
	private Color couleurPoints = Color.GRAY;
	private Color couleurRepere = Color.BLACK;
	private Color[] listeCouleurs = { Color.BLUE, Color.CYAN, Color.RED, Color.GREEN, Color.GRAY };
	public double pasCourbe = 1;
	private int frequenceAffichage = 10; // Une image sur 10

	
	// Fonction à trouver
	private ToDoubleBiFunction<Graine, Double> courbe =
			(g, x) -> g.get(0) * Math.pow(x, 3) + g.get(1) * Math.pow(x, 2) + g.get(2) * x + g.get(3); 

	 // En début de simulation, le but est d'avoir F(MIN_X) = MIN_Y et F(MAX_X) = MAX_Y
	 // On additionne le carré des différences
//	private ToDoubleFunction<Graine> fonctionEvaluation = 
//				g -> Math.pow(courbe.applyAsDouble(g, minX[0]) - minY[0], 2)
//					+ Math.pow(courbe.applyAsDouble(g, minX[1]) - minX[1], 2);

	private ToDoubleFunction<Graine> fonctionEvaluation = 
				g -> {
					double valeur = 0;
					for(int i=0; i<pointsX.length; i++) {
						valeur += Math.pow(courbe.applyAsDouble(g, pointsX[i]) - pointsY[i], 2);
					}
					return valeur;
				};

				
	public ParametresGenerateur() {
	}

	public ParametresGenerateur(int nbGraines, int nbSimulations, double amplitudeIteration) {
		this.nbGraines = nbGraines;
		this.nbSimulations = nbSimulations;
		this.amplitudeIteration = amplitudeIteration;
	}

	public Pair<Double, Double> minMaxX() {
        DoubleSummaryStatistics stat = Arrays.stream(pointsX).summaryStatistics();
        return Pair.of(stat.getMin(), stat.getMax());
	}

	public Pair<Double, Double> minMaxY() {
        DoubleSummaryStatistics stat = Arrays.stream(pointsX).summaryStatistics();
        return Pair.of(stat.getMin(), stat.getMax());
	}

	public double getAmplitudeIteration() {
		return amplitudeIteration;
	}

	public void setAmplitudeIteration(double amplitudeIteration) {
		this.amplitudeIteration = amplitudeIteration;
	}

	public int getNbGraines() {
		return nbGraines;
	}

	public void setNbGraines(int nbGraines) {
		this.nbGraines = nbGraines;
	}

	public int getNbSimulations() {
		return nbSimulations;
	}

	public void setNbSimulations(int nbSimulations) {
		this.nbSimulations = nbSimulations;
	}

	public int getPourcentageGrainesConservees() {
		return pourcentageGrainesConservees;
	}

	public void setPourcentageGrainesConservees(int pourcentageGrainesConservees) {
		this.pourcentageGrainesConservees = pourcentageGrainesConservees;
	}


	public ToDoubleBiFunction<Graine, Double> getCourbe() {
		return courbe;
	}
	
	public void setCourbe(ToDoubleBiFunction<Graine, Double> courbe) {
		this.courbe = courbe;
	}
	
	public ToDoubleFunction<Graine> getFonctionEvaluation() {
		return fonctionEvaluation;
	}
	
	public void setFonctionEvaluation(ToDoubleFunction<Graine> fonctionEvaluation) {
		this.fonctionEvaluation = fonctionEvaluation;
	}

	public boolean isAffichage() {
		return affichage;
	}

	public void setAffichage(boolean affichage) {
		this.affichage = affichage;
	}

	public Color getCouleurCourbe() {
		return couleurCourbe;
	}

	public void setCouleurCourbe(Color couleurCourbe) {
		this.couleurCourbe = couleurCourbe;
	}

	public Color getCouleurPoints() {
		return couleurPoints;
	}

	public void setCouleurPoints(Color couleurPoints) {
		this.couleurPoints = couleurPoints;
	}

	public Color getCouleurRepere() {
		return couleurRepere;
	}

	public void setCouleurRepere(Color couleurRepere) {
		this.couleurRepere = couleurRepere;
	}

	public int getLargeurEcran() {
		return largeurEcran;
	}

	public void setLargeurEcran(int largeurEcran) {
		this.largeurEcran = largeurEcran;
	}

	public int getEchelle() {
		return echelle;
	}

	public void setEchelle(int echelle) {
		this.echelle = echelle;
	}

	public int getHauteurEcran() {
		return hauteurEcran;
	}

	public int getNbIterations() {
		return nbIterations;
	}

	public void setNbIterations(int nbIterations) {
		this.nbIterations = nbIterations;
	}

	public int getFrequenceAffichageIterations() {
		return frequenceAffichageIterations;
	}

	public void setFrequenceAffichageIterations(int frequenceAffichageIterations) {
		this.frequenceAffichageIterations = frequenceAffichageIterations;
	}

	public Color[] getListeCouleurs() {
		return listeCouleurs;
	}

	public void setListeCouleurs(Color[] listeCouleurs) {
		this.listeCouleurs = listeCouleurs;
	}

	public double getPasCourbe() {
		return pasCourbe;
	}

	public void setPasCourbe(double pasCourbe) {
		this.pasCourbe = pasCourbe;
	}

	public int getFrequenceAffichage() {
		return frequenceAffichage;
	}

	public void setFrequenceAffichage(int frequenceAffichage) {
		this.frequenceAffichage = frequenceAffichage;
	}

	public double[] getPointsX() {
		return pointsX;
	}

	public double getPointsX(int i) {
		return pointsX[i];
	}

	public void setPointsX(double[] pointsX) {
		this.pointsX = pointsX;
	}

	public double[] getPointsY() {
		return pointsY;
	}

	public double getPointsY(int i) {
		return pointsY[i];
	}

	public void setPointsY(double[] pointsY) {
		this.pointsY = pointsY;
	}

	
}

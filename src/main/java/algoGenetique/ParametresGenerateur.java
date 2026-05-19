package algoGenetique;

import java.awt.Color;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;

public class ParametresGenerateur {

	private double amplitudeIteration = 1.0;
	private int nbGraines = 100; // Nombre de graines pour une simulation
	private int nbSimulations = 1000;
	private int pourcentageGrainesConservees = 10;

	// Ecran
	private boolean affichage = false;
	private int largeurEcran = 800;
	private final int hauteurEcran = 600;
	private int echelle = 10;
	private double minX = -15;
	private double minY = -15;
	private double maxX = 15;
	private double maxY = 20;
	
	private Color couleurCourbe = Color.RED;
	private Color couleurPoints = Color.BLUE;
	private Color couleurRepere = Color.BLACK;
	
	
	// Fonction d'évaluation
	private ToDoubleBiFunction<Graine, Double> courbe =
			(g, x) -> g.get(0) * Math.pow(x, 3) + g.get(1) * Math.pow(x, 2) + g.get(2) * x + g.get(3); 

	 // En début de simulation, le but est d'avoir F(MIN_X) = MIN_Y et F(MAX_X) = MAX_Y
	 // On additionne le carré des différences
	private ToDoubleFunction<Graine> fonctionEvaluation = 
				g -> Math.pow(courbe.applyAsDouble(g, minX) - minY, 2)
					+ Math.pow(courbe.applyAsDouble(g, maxX) - maxY, 2);

				
	public ParametresGenerateur() {
	}

	public ParametresGenerateur(int nbGraines, int nbSimulations, double amplitudeIteration) {
		this.nbGraines = nbGraines;
		this.nbSimulations = nbSimulations;
		this.amplitudeIteration = amplitudeIteration;
	}

	public void modifierFonction(ToDoubleBiFunction<Graine, Double> courbe, ToDoubleFunction<Graine> fonctionEvaluation) {
		this.courbe = courbe;
		this.fonctionEvaluation = fonctionEvaluation;
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

	public double getMinX() {
		return minX;
	}

	public void setMinX(double minX) {
		this.minX = minX;
	}

	public double getMinY() {
		return minY;
	}

	public void setMinY(double minY) {
		this.minY = minY;
	}

	public double getMaxX() {
		return maxX;
	}

	public void setMaxX(double maxX) {
		this.maxX = maxX;
	}

	public double getMaxY() {
		return maxY;
	}

	public void setMaxY(double maxY) {
		this.maxY = maxY;
	}

	public ToDoubleBiFunction<Graine, Double> getCourbe() {
		return courbe;
	}

	public ToDoubleFunction<Graine> getFonctionEvaluation() {
		return fonctionEvaluation;
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

}

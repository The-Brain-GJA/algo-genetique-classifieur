package algoGenetique;

public class ParametresGenerateur {

	double amplitudeIteration = 1.0;
	int nbGraines = 100;
	int nbSimulations = 1000;
	int pourcentageGrainesConservees = 10;
	
	public ParametresGenerateur() {
	}

	public ParametresGenerateur(int nbGraines, int nbSimulations, double amplitudeIteration) {
		this.nbGraines = nbGraines;
		this.nbSimulations = nbSimulations;
		this.amplitudeIteration = amplitudeIteration;
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

}

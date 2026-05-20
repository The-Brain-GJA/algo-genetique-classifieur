package algoGenetique;

import java.util.Arrays;

/**
 * Gestion d'une simulation à partir d'une seule graine.
 * Cette graine est recopiée plusieurs fois, pour l'algorithme génétique
 */
public class Simulation implements Comparable<Simulation> {
	
	public static int nbSimulations = 0;
	
	private GraineEvaluable graines[];
	private ParametresGenerateur parametres;
	private int indiceDepart;
	
	public Simulation(ParametresGenerateur parametres, Graine graineInitiale) {
		this.graines = new GraineEvaluable[parametres.getNbGraines()];
		this.parametres = parametres;
		for(int i=0; i<graines.length; i++) {
			graines[i] = new GraineEvaluable(parametres.getFonctionEvaluation(), graineInitiale);
		}
		this.indiceDepart = parametres.getNbGraines() * parametres.getPourcentageGrainesConservees() / 100;
		nbSimulations++;
	}
	
	/**
	 * On garde les meilleures au cas où (les premières de la liste), et on fait évoluer les autres
	 */
	public void iteration() {
		for(int i=indiceDepart; i<graines.length; i++) {
			graines[i].secouer(parametres.getAmplitudeIteration());
		}
		Arrays.sort(graines);
	}

	@Override
	public int compareTo(Simulation o) {
		return graines[0].compareTo(o.graines[0]);
	}

	public GraineEvaluable graineEnTete() {
		return graines[0];
	}
	
	public GraineEvaluable[] getGraines() {
		return graines;
	}

	protected void setGraines(GraineEvaluable graines[]) {
		this.graines = graines;
	}
	
	protected void setIndiceDepart(int indice) {
		this.indiceDepart = indice;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(graines);
	}
}

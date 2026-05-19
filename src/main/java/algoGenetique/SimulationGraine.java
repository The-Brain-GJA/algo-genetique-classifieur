package algoGenetique;

import java.util.Arrays;

/**
 * Gestion de plusieurs simulations indépendantes à partir d'une seule graine
 */
public class SimulationGraine {

	private Simulation[] simulations;
	private int numero;
	private Graine graineInitiale;

	public SimulationGraine(ParametresGenerateur parametres, Graine graineInitiale, int numero) {
		this.simulations = new Simulation[parametres.getNbSimulations()];
		this.numero = numero;
		this.graineInitiale = graineInitiale;
		for(int i=0; i<simulations.length; i++) {
			this.simulations[i] = new Simulation(parametres, graineInitiale);
		}
		// XXX
		System.out.println("(sim-graine) Création de " + this.simulations.length + " simulations");
	}
	
	public void iteration() {
		for(int i=0; i<simulations.length; i++) {
			simulations[i].iteration();
		}
		Arrays.sort(simulations);
	}

	public GraineEvaluable graineEnTete() {
		return simulations[0].graineEnTete();
	}

	public Simulation[] getSimulations() {
		return simulations;
	}

	protected void setSimulations(Simulation[] simulations) {
		this.simulations = simulations;
	}
	
	public int getNumero() {
		return numero;
	}

	public Graine getGraineInitiale() {
		return graineInitiale;
	}

	@Override
	public String toString() {
		return Arrays.toString(simulations);
	}
	
}

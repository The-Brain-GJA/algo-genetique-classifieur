package simulation;

import java.util.Arrays;

import algoGenetique.Graine;
import algoGenetique.GraineEvaluable;
import algoGenetique.ParametresGenerateur;

/**
 * Gestion de plusieurs simulations indépendantes à partir d'une seule graine
 */
public class SimulationGraine {

	private Simulation[] simulationsMonoGraine;
	private int numero;
	private Graine graineInitiale;
	public static int nbSimulationsGraine = 0; 

	public SimulationGraine(ParametresGenerateur parametres, Graine graineInitiale, int numero) {
		this.simulationsMonoGraine = new Simulation[parametres.getNbSimulations()];
		this.numero = numero;
		this.graineInitiale = graineInitiale;
		for(int i=0; i<simulationsMonoGraine.length; i++) {
			this.simulationsMonoGraine[i] = new Simulation(parametres, graineInitiale);
		}
		nbSimulationsGraine++;
		// XXX
		System.out.println("(sim-graine) Création de " + this.simulationsMonoGraine.length + " simulations");
	}
	
	public void iteration() {
		for(int i=0; i<simulationsMonoGraine.length; i++) {
			simulationsMonoGraine[i].iteration();
		}
		Arrays.sort(simulationsMonoGraine);
	}

	public GraineEvaluable graineEnTete() {
		return simulationsMonoGraine[0].graineEnTete();
	}

	public Simulation[] getSimulations() {
		return simulationsMonoGraine;
	}

	protected void setSimulations(Simulation[] simulations) {
		this.simulationsMonoGraine = simulations;
	}
	
	public int getNumero() {
		return numero;
	}

	public Graine getGraineInitiale() {
		return graineInitiale;
	}

	@Override
	public String toString() {
		return Arrays.toString(simulationsMonoGraine);
	}
	
}

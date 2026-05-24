package test;

import algoGenetique.Graine;
import algoGenetique.ParametresGenerateur;
import simulation.Simulation;
import simulation.SimulationGraine;

/**
 * Gestion de plusieurs simulations indépendantes à partir d'une seule graine
 */
public class MockSimulationGraine extends SimulationGraine {

	public MockSimulationGraine(ParametresGenerateur parametres, Graine graineInitiale, int numero) {
		super(parametres, graineInitiale, numero);
		
		final int PAS = 10;
		int origine = 100+PAS;
		setSimulations(new Simulation[] {
				new MockSimulation(origine-=PAS, parametres, graineInitiale),
				new MockSimulation(origine-=PAS, parametres, graineInitiale),
				new MockSimulation(origine-=PAS, parametres, graineInitiale),
				new MockSimulation(origine-=PAS, parametres, graineInitiale)
		});
	}

}

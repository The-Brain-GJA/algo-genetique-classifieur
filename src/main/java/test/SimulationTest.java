package test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import algoGenetique.Graine;
import algoGenetique.ParametresGenerateur;
import algoGenetique.Simulation;

class SimulationTest {

	@Test
	void testSort() {

		Graine g = new Graine(0, 0, 0, 0);
		Simulation simulation = new MockSimulation(0, new ParametresGenerateur(), g);

		assertEquals(1, simulation.graineEnTete().getEvaluation());
		assertEquals(0, simulation.getGraines()[1].getEvaluation());
		assertEquals(3, simulation.getGraines()[2].getEvaluation());
		assertEquals(0.5, simulation.getGraines()[3].getEvaluation());
		
		simulation.iteration();

		assertEquals(0, simulation.graineEnTete().getEvaluation());
		assertEquals(0.5, simulation.getGraines()[1].getEvaluation());
		assertEquals(1, simulation.getGraines()[2].getEvaluation());
		assertEquals(3, simulation.getGraines()[3].getEvaluation());
	}
	
}

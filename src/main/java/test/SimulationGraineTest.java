package test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import algoGenetique.Graine;
import algoGenetique.ParametresGenerateur;
import algoGenetique.SimulationGraine;

class SimulationGraineTest {

	@Test
	void testSort() {

		Graine g = new Graine(0, 0, 0, 0);

		SimulationGraine simulation = new MockSimulationGraine(new ParametresGenerateur(), g, 0);
		
		assertEquals(101, simulation.graineEnTete().getEvaluation());
		assertEquals(100, simulation.getSimulations()[0].getGraines()[1].getEvaluation());
		assertEquals(103, simulation.getSimulations()[0].getGraines()[2].getEvaluation());
		assertEquals(100.5, simulation.getSimulations()[0].getGraines()[3].getEvaluation());

		assertEquals(91, simulation.getSimulations()[1].getGraines()[0].getEvaluation());
		assertEquals(90, simulation.getSimulations()[1].getGraines()[1].getEvaluation());
		assertEquals(93, simulation.getSimulations()[1].getGraines()[2].getEvaluation());
		assertEquals(90.5, simulation.getSimulations()[1].getGraines()[3].getEvaluation());

		assertEquals(81, simulation.getSimulations()[2].getGraines()[0].getEvaluation());
		assertEquals(80, simulation.getSimulations()[2].getGraines()[1].getEvaluation());
		assertEquals(83, simulation.getSimulations()[2].getGraines()[2].getEvaluation());
		assertEquals(80.5, simulation.getSimulations()[2].getGraines()[3].getEvaluation());

		assertEquals(71, simulation.getSimulations()[3].getGraines()[0].getEvaluation());
		assertEquals(70, simulation.getSimulations()[3].getGraines()[1].getEvaluation());
		assertEquals(73, simulation.getSimulations()[3].getGraines()[2].getEvaluation());
		assertEquals(70.5, simulation.getSimulations()[3].getGraines()[3].getEvaluation());
		
		simulation.iteration();

		assertEquals(70, simulation.graineEnTete().getEvaluation());
		assertEquals(70.5, simulation.getSimulations()[0].getGraines()[1].getEvaluation());
		assertEquals(71, simulation.getSimulations()[0].getGraines()[2].getEvaluation());
		assertEquals(73, simulation.getSimulations()[0].getGraines()[3].getEvaluation());

		assertEquals(80, simulation.getSimulations()[1].getGraines()[0].getEvaluation());
		assertEquals(80.5, simulation.getSimulations()[1].getGraines()[1].getEvaluation());
		assertEquals(81, simulation.getSimulations()[1].getGraines()[2].getEvaluation());
		assertEquals(83, simulation.getSimulations()[1].getGraines()[3].getEvaluation());

		assertEquals(90, simulation.getSimulations()[2].getGraines()[0].getEvaluation());
		assertEquals(90.5, simulation.getSimulations()[2].getGraines()[1].getEvaluation());
		assertEquals(91, simulation.getSimulations()[2].getGraines()[2].getEvaluation());
		assertEquals(93, simulation.getSimulations()[2].getGraines()[3].getEvaluation());

		assertEquals(100, simulation.getSimulations()[3].getGraines()[0].getEvaluation());
		assertEquals(100.5, simulation.getSimulations()[3].getGraines()[1].getEvaluation());
		assertEquals(101, simulation.getSimulations()[3].getGraines()[2].getEvaluation());
		assertEquals(103, simulation.getSimulations()[3].getGraines()[3].getEvaluation());

	}

	@Test
	void testIndiceDepart() {
		// XXX à faire
	}

}

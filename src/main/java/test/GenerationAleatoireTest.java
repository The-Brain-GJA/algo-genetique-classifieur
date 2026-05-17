package test;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import algoGenetique.Generateur;
import outils.GenerationAleatoire;

class GenerationAleatoireTest {

	// En théorie, les tests peuvent échouer. On suppose qu'on fait suffisamment de tirages aléatoires pour que ça marche 
	final int NB_TESTS = 100_000;

	@Test
	void testRandomAmplitude_3_5() {
		testAmplitude(3.5, 0.01);
	}

	@Test
	void testRandomAmplitude_10() {
		testAmplitude(10, 0.01);
	}

	/**
	 *  On compte le nb de générations < -MAX + epsilon et > MAX - epsilon, pour vérifier qu'on atteint bien les limites
	 */
	private void testAmplitude(final double max, final double epsilon) {
		final double LIMITE_TEST = max - epsilon;
		int nbInf = 0;
		int nbSup = 0;
		for(int i=0; i<NB_TESTS; i++) {
			double d = GenerationAleatoire.random(max);
			if(d < -LIMITE_TEST) {
				nbInf++;
			} else if(d > LIMITE_TEST) {
				nbSup++;
			}
			assertTrue(d > -max);
			assertTrue(d < max);
		}
		assertTrue(nbInf > 0);
		assertTrue(nbSup > 0);
	}
	
}

package test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import algoGenetique.Generateur;
import algoGenetique.GraineEvaluable;
import algoGenetique.ParametresGenerateur;

class GenerateurTest {

	@Test
	void testToString() {
		ParametresGenerateur parametres = new ParametresGenerateur(2, 100, 0.1);
		Generateur gen = Generateur.getGenerateur(new double[] {1, 2, 3}, parametres, g -> g.get(0) + g.get(1) + g.get(2));
		assertEquals("[[1.0, 2.0, 3.0] : 6.0, [1.0, 2.0, 3.0] : 6.0]", gen.toString());
	}

	@Test
	void testIteration() {
	}

	
}

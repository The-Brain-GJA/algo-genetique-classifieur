package test;
import org.junit.jupiter.api.Test;

import algoGenetique.Generateur;

class GenerateurTest {

	@Test
	void testGrainesString() {
	
		Generateur gen = new Generateur(new double[] {1, 2, 3}, 3, 100, g -> g.get(0) + g.get(1) + g.get(2));
	
		
		
		System.out.println(gen);
		
	}

	
}

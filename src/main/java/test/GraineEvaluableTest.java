package test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import algoGenetique.GraineEvaluable;

class GraineEvaluableTest {

	@Test
	void testToString() {
		GraineEvaluable g1 = new GraineEvaluable(g -> 0, 1, 1, 1);
		assertEquals("[1.0, 1.0, 1.0] : 0.0", g1.toString());
	}

	@Test
	void testEvaluation() {
		GraineEvaluable g = new GraineEvaluable(gr -> gr.get(0) * 1000 + gr.get(1) * 10 + gr.get(2), 1, 2, 3);
		assertEquals(1023, g.getEvaluation());
	}

	@Test
	void testEquals() {
		GraineEvaluable g1 = new GraineEvaluable(g -> 0, 1, 1, 1);
		GraineEvaluable g2 = new GraineEvaluable(g -> 1, 1, 1, 1);
		assertEquals(g1, g2);
		
		g1 = new GraineEvaluable(g -> 0, 1, 1, 1);
		g2 = new GraineEvaluable(g -> 1, 1, 1, 2);
		assertNotEquals(g1, g2);
	}
	
	@Test
	void testSort() {
		
		GraineEvaluable g0 = new GraineEvaluable(g -> 0, 0);
		GraineEvaluable g1 = new GraineEvaluable(g -> 1, 1, 1);
		GraineEvaluable g2 = new GraineEvaluable(g -> 2, 2, 2, 2);
		GraineEvaluable g3 = new GraineEvaluable(g -> 3, 3, 3);
		GraineEvaluable g4 = new GraineEvaluable(g -> 4, 4, 4);
		GraineEvaluable[] t = {g2, g1, g4, g1, g3, g0};

		assertEquals(g2, t[0]);
		assertEquals(g1, t[1]);
		assertEquals(g4, t[2]);
		assertEquals(g1, t[3]);
		assertEquals(g3, t[4]);
		assertEquals(g0, t[5]);

		Arrays.sort(t);

		assertEquals(g0, t[0]);
		assertEquals(g1, t[1]);
		assertEquals(g1, t[2]);
		assertEquals(g2, t[3]);
		assertEquals(g3, t[4]);
		assertEquals(g4, t[5]);
	}

}

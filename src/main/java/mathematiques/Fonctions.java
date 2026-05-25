package mathematiques;

import java.util.function.ToDoubleBiFunction;

import algoGenetique.Graine;

public class Fonctions {

	public static ToDoubleBiFunction<Graine, Double> COSINUS = (g, x) -> g.get(0) * Math.cos(g.get(1) + 1 / g.get(2) * x)
	+ g.get(3); 

	
	public static ToDoubleBiFunction<Graine, Double> POLYNOME_COSINUS =
		(g, x) ->
			// 1) polynome de degré 5
			g.get(0) * Math.pow(g.get(1) * x, 5)
				+ g.get(2) * Math.pow(g.get(3) * x, 4)
				+ g.get(4) * Math.pow(g.get(5) * x, 3)
				+ g.get(6) * Math.pow(g.get(7) * x, 2)
				+ g.get(8) * x
				+ g.get(9)
			//  2) cosinus
			+ g.get(10) * Math.cos(g.get(11) + g.get(12) * x) + g.get(13)
	;

	public static ToDoubleBiFunction<Graine, Double> POLYNOME_2 =
		(g, x) ->
				g.get(0) * Math.pow(g.get(1) * (x - g.get(2)), 2)
				+ g.get(3) * (x - g.get(4))
				+ g.get(5)
	;

				
				

}

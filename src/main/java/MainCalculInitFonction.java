
import algoGenetique.Generateur;
import algoGenetique.Graine;
import algoGenetique.GraineEvaluable;
import algoGenetique.ParametresGenerateur;
import outils.Timer;

public class MainCalculInitFonction {
	
	/*
	 * set datafile separator comma
	 * plot 'file_15.csv' skip 1 using 1:2
	 * 
	 * plot [-20:20] 0.005247306472041999*x**3 + 0.009761759614494965*x**2 + 0.013858369778036842*x + 0.320430570024019
	 * 
	 */

	// Tester : equation du 5ème degré
	

	public static void main(String[] args) {
		
		Timer timer = Timer.getTimer("Algo génétique");
		
		System.out.println("Test");
		
		ParametresGenerateur parametres = new ParametresGenerateur();
		parametres.setNbGraines(100);
		parametres.setNbSimulations(100_000);
		parametres.setPourcentageGrainesConservees(20);
		parametres.setAmplitudeIteration(0.0001);
		
//		double[] init = {0.005247306472041999, 0.009761759614494965, -0.013858369778036842, 0.320430570024019};
		double[] init = {0, 0, 0, 0};
			
		Generateur gen = Generateur.getGenerateurAvecParametresInitiaux(init, parametres);
		GraineEvaluable graine = gen.simulation();
		System.out.println(graine);

		//Graine g = new Graine(init);
		System.out.println("Init : " + Generateur.EVALUATION_PARAMETRES_INIT.applyAsDouble(graine));
		System.out.println("F(-15) = " + Generateur.POLYNOME.applyAsDouble(graine, -15.0));
		System.out.println("F(15) = " + Generateur.POLYNOME.applyAsDouble(graine, 15.0));
		
		timer.afficher();
	}
}

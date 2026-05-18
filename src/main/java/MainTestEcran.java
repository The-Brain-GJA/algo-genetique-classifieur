import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.function.DoubleUnaryOperator;

import affichage.Cluster;
import affichage.Courbe;
import affichage.Ecran;
import affichage.ObjetDessin;
import affichage.Repere;
import affichage.TypePointCluster;
import algoGenetique.Generateur;
import algoGenetique.Graine;
import outils.Timer;

public class MainTestEcran {

	public static void main(String[] args) {

		System.out.println("Test écran");
		Timer t = Timer.getTimer("Affichage écran");

		Ecran ecran = new Ecran(800, 400, -16, -10, 10);
		DoubleUnaryOperator fonction = x -> Generateur.POLYNOME.applyAsDouble(new Graine(Generateur.INIT_POLYNOME), x);

		Point2D[] pointsCercle = {
				new Point2D.Double(0, 0),
				new Point2D.Double(0, 10),
				new Point2D.Double(-15, 0)
		};

		Point2D[] point1 = { new Point2D.Double(0, 0) };
		Point2D[] point2 = { new Point2D.Double(0, 10) };
		Point2D[] point3 = { new Point2D.Double(-15, 0) };
		Point2D[] point4 = { new Point2D.Double(0, -20) };

		List<ObjetDessin> dessins = List.of(new Repere(ecran),
				//new Courbe(ecran, fonction),
				new Cluster(ecran, point1, TypePointCluster.ROND, Color.RED), // 0, 0
				new Cluster(ecran, point2, TypePointCluster.ROND, Color.YELLOW), // 0, 10
				new Cluster(ecran, point3, TypePointCluster.ROND, Color.BLUE), // -15, 0
				new Cluster(ecran, point4, TypePointCluster.ROND, Color.CYAN) // 0, -20
//				new Cluster(ecran, pointsCercle, TypePointCluster.ROND, Color.BLUE)
		);

		ecran.dessiner(dessins);

		t.afficher();
	}

}

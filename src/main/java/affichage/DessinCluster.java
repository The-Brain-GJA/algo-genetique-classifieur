package affichage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import algoGenetique.Constantes;

public class DessinCluster extends ObjetDessin {

	private static final int RAYON_CERCLE = 5;
	
	Point2D[] points;
	TypePointCluster typePoint;
	
	public DessinCluster(Ecran ecran, Point2D[] points, Color couleur) {
		this(ecran, points, TypePointCluster.ROND, couleur);
	}

	public DessinCluster(Ecran ecran, Point2D[] points, TypePointCluster typePoint, Color couleur) {
		super(ecran);
		this.points = points;
		this.typePoint = typePoint;
		this.couleur = couleur;
	}

	/**
	 * Prend en entrée une liste de points avec 3 valeurs : X, Y et classe
	 * Renvoie un objet par classe, avec une couleur diférente
	 */
	public static List<DessinCluster> getDessinCluster(Ecran ecran, List<double[]> valeurs) {
		Map<Double, List<double[]>> map = valeurs.stream().collect(Collectors.groupingBy(d -> d[2]));
		List<DessinCluster> dessins = new ArrayList<>(map.size());
		AtomicInteger j = new AtomicInteger(0);
		map.
			forEach((classe, points) -> {
				Point2D[] points2D = new Point2D[points.size()];
				for(int i=0; i<points.size(); i++) {
					points2D[i] = new Point2D.Double(points.get(i)[0], points.get(i)[1]);
				}
				dessins.add(new DessinCluster(ecran, points2D, Constantes.getCouleur(j.getAndIncrement())));
			});
		return dessins;
	}
	
	@Override
	public void dessiner(Graphics2D g) {
		g.setColor(couleur);
		BiConsumer<Double, Double> dessinerPoint = 
			switch (typePoint) {
			case CROIX -> (x,y) -> dessinerCroix(g, x, y);
			case CARRE -> (x,y) -> dessinerCarre(g, x, y);
			default -> (x,y) -> dessinerCercle(g, x, y);
		};				
		
		for (Point2D point : points) {
			dessinerPoint.accept(point.getX(), point.getY());
		}
		g.setColor(COULEUR_DEFAUT);
	}

	public void dessinerCercle(Graphics2D g, double x, double y) {
		  int diametre = RAYON_CERCLE * 2;
		  double x2 = ecran.transformationX(x) - RAYON_CERCLE;
		  double y2 = ecran.transformationY(y) - RAYON_CERCLE;
		  Shape circle = new Ellipse2D.Double(x2, y2, diametre, diametre);
		  g.fill(circle);
		}

	public void dessinerCarre(Graphics2D g, double x, double y) {
		// XXX à faire et à tester
		}

	public void dessinerCroix(Graphics2D g, double x, double y) {
		// XXX à faire et à tester
	}

}

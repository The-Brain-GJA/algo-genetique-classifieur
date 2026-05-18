package affichage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.function.BiConsumer;

public class Cluster extends ObjetDessin {

	private static final int RAYON_CERCLE = 5;
	
	Point2D[] points;
	TypePointCluster typePoint;
	
	public Cluster(Ecran ecran, Point2D[] points, Color couleur) {
		this(ecran, points, TypePointCluster.ROND, couleur);
	}

	public Cluster(Ecran ecran, Point2D[] points, TypePointCluster typePoint, Color couleur) {
		super(ecran);
		this.points = points;
		this.typePoint = typePoint;
		this.couleur = couleur;
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

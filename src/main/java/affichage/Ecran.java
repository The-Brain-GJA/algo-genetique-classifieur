package affichage;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.DoubleUnaryOperator;
import java.util.function.ToDoubleFunction;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import algoGenetique.Generateur;
import algoGenetique.Graine;
import outils.Timer;

// TODO : mise à l'échelle

/**
 * Affichage de nuages de points et d'itérations de graines
 */
public class Ecran {

	private final static int DECALAGE_W = 2;
	private final static int DECALAGE_H = 36;

	private int largeur;
	private int hauteur;
	private double xmin;
	private double ymin;
	private double echelle;
	
	JFrame frame;
	
	public static void main(String[] args) throws InterruptedException {
		
		System.out.println("Test écran");
		Timer t = Timer.getTimer("Affichage écran");

		Ecran ecran = new Ecran(800, 400, -16, -20, 10);
		DoubleUnaryOperator fonction = x -> Generateur.POLYNOME.applyAsDouble(new Graine(Generateur.INIT_POLYNOME), x);
		
		List<ObjetDessin> dessins = List.of(new Repere(ecran),
				new Courbe(ecran, fonction),
				new Cluster(ecran, new Point2D[] {new Point2D.Double(200, 100)}));
		
		ecran.dessiner(dessins);

		t.afficher();
	}
	

	public Ecran(int width, int height, double xmin, double ymin) {
		this(width, height, xmin, ymin, 1);
	}
	
	public Ecran(int width, int height, double xmin, double ymin, double echelle) {
		this.hauteur = height;
		this.largeur = width;
		this.xmin = xmin;
		this.ymin = ymin;
		this.echelle = echelle;

		frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(largeur+DECALAGE_W,hauteur+DECALAGE_H);
	}

	public void dessiner(List<ObjetDessin> dessins) {
        frame.add(new PanelEcran(this, dessins));
        frame.setVisible(true);
        //f.setLocation(200,200);
	}

	public double getXmin() {
		return xmin;
	}

	public double getYmin() {
		return ymin;
	}


	public int getLargeur() {
		return largeur;
	}


	public int getHauteur() {
		return hauteur;
	}


	public double getEchelle() {
		return echelle;
	}

	protected Point2D pointRepere2D(double x, double y) {
		return new Point2D.Double(transformationX(x), transformationY(y));
	}
	
	protected Line2D ligneRepere2D(double x1, double y1, double x2, double y2) {
		return new Line2D.Double(transformationX(x1), transformationY(y1),
				transformationX(x2), transformationY(y2));
	}
	
	private double transformationX(double x) {
		return (x - xmin) * echelle;
	}

	private double transformationY(double y) {
		return hauteur + (- y + ymin) * echelle;
	}

}

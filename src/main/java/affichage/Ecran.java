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

//	protected Point2D pointRepere2D(double x, double y) {
//		return new Point2D.Double(transformationX(x), transformationY(y));
//	}
	
	protected Line2D ligneRepere2D(double x1, double y1, double x2, double y2) {
		System.out.println("--- Ligne ---");
		System.out.println(String.format("x1,x2,y1,y2 : %f %f %f %f", x1, y1, x2, y2));
		System.out.println(String.format("tx1,tx2,ty1,ty2 : %f %f %f %f", transformationX(x1), transformationY(y1), transformationX(x2), transformationY(y2)));
		return new Line2D.Double(transformationX(x1), transformationY(y1),
				transformationX(x2), transformationY(y2));
	}
	
	protected double transformationX(double x) {
		return (x - xmin) * echelle;
	}

	protected double transformationY(double y) {
		return hauteur + (- y + ymin) * echelle;
	}

}

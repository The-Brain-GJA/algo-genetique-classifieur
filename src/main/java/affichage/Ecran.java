package affichage;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.time.Duration;
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
	private int xmin;
	private int ymin;
	private double echelle;
	
	JFrame frame;
	
	public static void main(String[] args) throws InterruptedException {
		
		System.out.println("Test écran");
		Timer t = Timer.getTimer("Affichage écran");

		Ecran ecran = new Ecran(800, 400, -20, -20, 1);
		ecran.dessiner();

		t.afficher();

	}
	
	private Point2D pointRepere2D(double x, double y) {
		return new Point2D.Double(transformationX(x), transformationY(y));
	}
	
	private Line2D ligneRepere2D(double x1, double y1, double x2, double y2) {
		return new Line2D.Double(transformationX(x1), transformationY(y1),
				transformationX(x2), transformationY(y2));
	}
	
	private double transformationX(double x) {
		return x - xmin;
	}

	private double transformationY(double y) {
		return hauteur - y + ymin;
	}

	public Ecran(int width, int height, int xmin, int ymin, double echelle) {
		this.hauteur = height;
		this.largeur = width;
		this.xmin = xmin;
		this.ymin = ymin;
		this.echelle = echelle;

		frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(largeur+DECALAGE_W,hauteur+DECALAGE_H);
        frame.add(new PanelRepereEtCourbe(
        		x -> Generateur.POLYNOME.applyAsDouble(new Graine(Generateur.INIT_POLYNOME), x)));
	}

	public void dessiner() {
        frame.setVisible(true);
        //f.setLocation(200,200);
	}
	
	private class PanelRepereEtCourbe extends PanelEcran {
		
		final DoubleUnaryOperator fonctionCourbe;
		
		PanelRepereEtCourbe(DoubleUnaryOperator fonction) {
			fonctionCourbe = fonction;
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			tracerRepere(g2);
			tracerCourbe(g2, fonctionCourbe);
		}
	}

	private class PanelEcran extends JPanel {
		
		protected void tracerRepere(Graphics2D g) {
			g.draw(ligneRepere2D(xmin, 0, getWidth(), 0)); // axe des abscisses
			g.draw(ligneRepere2D(0, ymin, 0, getHeight())); // axe des ordonnées
		}
		
		protected void tracerCourbe(Graphics2D g, DoubleUnaryOperator fonction) {
			int h = getHeight();
			int w = getWidth();

			double xi1 = xmin;
			double yi1 = fonction.applyAsDouble(xi1);
			
			final double pas = 1; // XXX tester 0.5
			for(double d=xmin+pas; d<w; d+=pas) {
				double xi2 = d;
				double yi2 = fonction.applyAsDouble(d);
				Line2D segment = ligneRepere2D(xi1, yi1, xi2, yi2);
				g.draw(segment);
				xi1 = xi2;
				yi1 = yi2;
			}
		}

	}

	
	
}

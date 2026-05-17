package affichage;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
	
	
	public static void main(String[] args) {
		
		System.out.println("Test écran");
			 
		new Ecran(800, 400, -20, -20, 1).dessiner();


	}
	
	Line2D ligneRepere2D(double x1, double y1, double x2, double y2) {
		return new Line2D.Double(transformationX(x1), transformationY(y1),
				transformationX(x2), transformationY(y2));
	}
	
	private double transformationX(double x) {
		return x - xmin;
	}

	private double transformationY(double y) {
		return hauteur - y + ymin;
	}

	Point2D pointRepere2D(double x, double y) {
		return new Point2D.Double(transformationX(x), transformationY(y));
	}
	
	public Ecran(int width, int height, int xmin, int ymin, double echelle) {
		this.hauteur = height;
		this.largeur = width;
		this.xmin = xmin;
		this.ymin = ymin;
		this.echelle = echelle;
	}

	public void dessiner() {
		JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(largeur+DECALAGE_W,hauteur+DECALAGE_H);
        f.add(new Graphe());
        f.setVisible(true);
        //f.setLocation(200,200);
	}
	
	private class Graphe extends JPanel {
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			
			int h = getHeight();
			int w = getWidth();

			// Repère (0,0)
			g2.draw(ligneRepere2D(xmin, 0, w, 0)); // axe des abscisses
			g2.draw(ligneRepere2D(0, ymin, 0, h)); // axe des ordonnées

			// Test
			int UNIT = 20;
			Point2D P1 = new Point2D.Double(0,0);
			Point2D P2 = new Point2D.Double((w/2)+ UNIT,(h/2)+ 2*UNIT);
			Point2D T1 = pointRepere2D(0,0);
			Point2D T2 = pointRepere2D((w/2)+ UNIT,(h/2)+ 2*UNIT);
			g2.draw(new Line2D.Double(P1,P2));
			g2.draw(new Line2D.Double(T1,T2));
		}
	}
}

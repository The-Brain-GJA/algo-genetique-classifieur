package affichage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.function.DoubleUnaryOperator;

public class Courbe extends ObjetDessin {

	DoubleUnaryOperator fonction;
	Color couleur;
	
	public Courbe(Ecran ecran, DoubleUnaryOperator fonction) {
		this(ecran, fonction, COULEUR_DEFAUT);
	}

	public Courbe(Ecran ecran, DoubleUnaryOperator fonction, Color couleur) {
		super(ecran);
		this.fonction = fonction;
		this.couleur = couleur;
	}

	@Override
	public void dessiner(Graphics2D g) {

		int h = ecran.getHauteur();
		int w = ecran.getLargeur();

		double xi1 = ecran.getXmin();
		double yi1 = fonction.applyAsDouble(xi1);
		
		g.setColor(couleur);

		final double pas = 1; // XXX tester 0.5
		for(double d=ecran.getXmin()+pas; d<w; d+=pas) {
			double xi2 = d;
			double yi2 = fonction.applyAsDouble(d);
			Line2D segment = ecran.ligneRepere2D(xi1, yi1, xi2, yi2);
			g.draw(segment);
			xi1 = xi2;
			yi1 = yi2;
		}
		
		g.setColor(COULEUR_DEFAUT);
	}

}

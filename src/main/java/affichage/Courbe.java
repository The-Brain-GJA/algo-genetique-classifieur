package affichage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.function.DoubleUnaryOperator;

public class Courbe extends ObjetDessin {

	DoubleUnaryOperator fonction;
	Color couleur;
	double pas;
	
	public Courbe(Ecran ecran, DoubleUnaryOperator fonction, double pas) {
		this(ecran, fonction, COULEUR_DEFAUT, pas);
	}

	public Courbe(Ecran ecran, DoubleUnaryOperator fonction, Color couleur, double pas) {
		super(ecran);
		this.fonction = fonction;
		this.couleur = couleur;
		this.pas = pas;
	}

	@Override
	public void dessiner(Graphics2D g) {

		int h = ecran.getHauteur();
		int w = ecran.getLargeur();

		double xi1 = ecran.getXmin();
		double yi1 = fonction.applyAsDouble(xi1);
		
		g.setColor(couleur);

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

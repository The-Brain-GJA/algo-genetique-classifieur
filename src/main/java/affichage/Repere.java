package affichage;

import java.awt.Color;
import java.awt.Graphics2D;

public class Repere extends ObjetDessin {

	public Repere(Ecran ecran) {
		this(ecran, COULEUR_DEFAUT);
	}

	public Repere(Ecran ecran, Color couleur) {
		super(ecran);
		this.couleur = couleur;
	}

	@Override
	public void dessiner(Graphics2D g) {
		g.setColor(couleur);
		g.draw(ecran.ligneRepere2D(ecran.getXmin(), 0, (ecran.getLargeur() + ecran.getXmin()) / ecran.getEchelle(), 0)); // axe des abscisses
		g.draw(ecran.ligneRepere2D(0, ecran.getYmin(), 0, (ecran.getHauteur() + ecran.getYmin()) / ecran.getEchelle())); // axe des ordonnées
		g.setColor(COULEUR_DEFAUT);
	}
}

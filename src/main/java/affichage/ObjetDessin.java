package affichage;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Objet à dessiner dans un écran
 */
public abstract class ObjetDessin {
	
	public static final Color COULEUR_DEFAUT = Color.BLACK;
	
	protected Ecran ecran;
	protected Color couleur;

	public ObjetDessin(Ecran ecran) {
		this.ecran = ecran;
	}
	
	public abstract void dessiner(Graphics2D g);

	
	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}
	
}

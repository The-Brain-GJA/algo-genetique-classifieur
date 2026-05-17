package affichage;

import java.awt.Graphics2D;

/**
 * Objet à dessiner dans un écran
 */
public abstract class ObjetDessin {
	
	Ecran ecran;
	
	public ObjetDessin(Ecran ecran) {
		this.ecran = ecran;
	}
	
	public abstract void dessiner(Graphics2D g);
	
}

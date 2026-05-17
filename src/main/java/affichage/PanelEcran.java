package affichage;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.function.DoubleUnaryOperator;

import javax.swing.JPanel;

public class PanelEcran extends JPanel {
	
	Ecran ecran;
	List<ObjetDessin> dessins;
	
	PanelEcran(Ecran ecran, List<ObjetDessin> dessins) {
		this.ecran = ecran;
		this.dessins = dessins;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		for (ObjetDessin objetDessin : dessins) {
			objetDessin.dessiner(g2);
		}
	}

}
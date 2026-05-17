package algoGenetique;

import java.util.Arrays;
import java.util.function.ToDoubleFunction;

/**
 * Liste de paramètres à calculer
 */
public class Graine {

	private double[] valeurs;
	
	public Graine(double... valeurs) {
		this.valeurs = valeurs;
	}

	public double[] getGraine() {
		return valeurs;
	}

	public double get(int i) {
		return valeurs[i];
	}

	@Override
	public String toString() {
		return Arrays.toString(valeurs);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Graine g && Arrays.equals(valeurs, g.valeurs);
	}

	//	public void setValeurs(double... valeurs) {
	//		if(valeurs.length != this.valeurs.length) {
	//			throw new IllegalArgumentException(String.format("valeurs.length = %d, param.length = %d" + this.valeurs.length, valeurs.length));
	//		}
	//		this.valeurs = valeurs;
	//	}

}

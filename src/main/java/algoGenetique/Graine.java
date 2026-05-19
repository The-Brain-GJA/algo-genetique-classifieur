package algoGenetique;

import java.util.Arrays;

public class Graine {

	private double[] valeurs;
	
	public Graine(double... valeurs) {
		this.valeurs = valeurs;
	}

	public double[] getGraine() {
		return valeurs;
	}

	public int nbGraines() {
		return valeurs.length;
	}
	
	protected void modifierElement(int i, double valeur) {
		valeurs[i] = valeur;
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

	public double[] getValeurs() {
		return valeurs;
	}
	
	@Override
	protected Graine clone() {
		return new Graine(valeurs.clone());
	}


}

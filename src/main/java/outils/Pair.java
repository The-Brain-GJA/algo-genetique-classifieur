package outils;

import java.util.Locale;

public class Pair<A,B> {
	private A x;
	private B y;
	
	public static <X,Y> Pair <X,Y> of(X x, Y y) {
		return new Pair<>(x, y);
	}
	
	private Pair(A x, B y) {
		this.x = x;
		this.y = y;
	}
	
	public A getX() {
		return x;
	}
	public B getY() {
		return y;
	}
	
	@Override
	public String toString() {
		return String.format(Locale.ROOT, "(%s,%s)", x, y);
	}
	
}

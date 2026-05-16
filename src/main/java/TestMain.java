import outils.Timer;

public class TestMain {
	
	/*
	 * set datafile separator comma
	 * plot 'file_15.csv' skip 1 using 1:2
	 * 
	 */
	
	public static void main(String[] args) {
		
		Timer timer = Timer.getTimer("Main");
		
		System.out.println("Test");
		
		timer.afficher();
	}
}

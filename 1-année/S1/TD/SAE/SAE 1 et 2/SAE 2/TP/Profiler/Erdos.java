/******************************************************************************
 *  Compilation:  javac Erdos.java
 *  Execution:    java Erdos N p
 *  Dependences: StdDraw.java
 *
 *  Trace le cercle d'ERdos à N points et les segments avec la proba p
 *
 ******************************************************************************/

/**
 * Version sans tableaux
 */
public class Erdos {

    static int nbPoints;

	public static void main(String[] args) {
		//Profiler.init();
		// pour centrer la fenêtre en (0,0)
		StdDraw.setXscale(-1.2, 1.2);
		StdDraw.setYscale(-1.2, 1.2);

		final double penR = 0.02;
        nbPoints = Integer.parseInt(args[0]);
		double proba = Double.parseDouble(args[1]);

		// des gros points noirs
		StdDraw.setPenRadius(penR);
		tracePoints(nbPoints);

		// des segments fins en gris
		StdDraw.setPenRadius();
		StdDraw.setPenColor(StdDraw.GRAY);
		double ratio = traceSegments(proba);
		System.out.println("Ratio (segments tracés)/(segements traçables) = " + ratio);
		//Profiler.getCallCount();
		//Profiler.getTotalTime();
	}

	/**
	* Trace n points régulièrement répartis sur le cercle trigo
	*
	* @param n le nombre de points
	*/
	public static void tracePoints(int n) {
		final double ANGLE_STEP = 2 * Math.PI / n;
		for (int i = 0; i < n; i++) {
			StdDraw.point(Math.cos(i * ANGLE_STEP), Math.sin(i * ANGLE_STEP));
		}
		StdDraw.show();
	}

	/**
	* Effectue un tirage vrai/faux avec la probabilité p de tirer vrai.
	*
	* @param p proba de tirer vrai
	* @return le résultat du tirage
	*/
	public static boolean tireProba(double p) {
		return  (Math.random() < p) ;
	}

  /**
	* Effectue le tracé des segments.
	* Tous les segments sont évalués, mais un tirage est fait avant le tracé.
	* Le segment n'est effectivement tracé que si le tirage donne 'vrai'
	*
	* @param p la proba de tirer vrai
	* @return le ratio réel (nb de segments tracés / nb de segments traçables)
	*/
	public static double traceSegments(double p) {
		final double ANGLE_STEP = 2 * Math.PI / nbPoints;
		int cptTotal = 0;
		int cptTrace = 0;
		for (int i = 0; i < nbPoints; i++) {
			for (int j = i + 1; j < nbPoints; j++) {
				cptTotal++;
				if (tireProba(p)){
					StdDraw.line(Math.cos(i * ANGLE_STEP), Math.sin(i * ANGLE_STEP),
						Math.cos(j * ANGLE_STEP), Math.sin(j * ANGLE_STEP));
					cptTrace++;
				}
			}
		}
		StdDraw.show();
		return ((double)cptTrace/cptTotal);
	}
}

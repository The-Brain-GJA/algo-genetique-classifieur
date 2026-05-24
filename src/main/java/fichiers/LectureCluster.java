package fichiers;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import affichage.DessinCluster;
import algoGenetique.Constantes;

public class LectureCluster {

	private static final String NOM_REPERTOIRE = "/home/gael/dev/nuages/";
	private int nbLignesSautees = 1;

	private String nomRepertoire;

	public LectureCluster() {
		this(NOM_REPERTOIRE);
	}

	public LectureCluster(String nomRepertoire) {
		this.nomRepertoire = nomRepertoire;
	}

	public List<Point2D[]> lire(String nomFichier) throws IOException {
		List<double[]> valeurs = lireValeurs(nomFichier);
		Map<Double, List<double[]>> map = valeurs.stream().collect(Collectors.groupingBy(d -> d[2]));

		List<Point2D[]> listePoints = new ArrayList<>();
		map.
		forEach((classe, points) -> {
			Point2D[] points2D = new Point2D[points.size()];
			for(int i=0; i<points.size(); i++) {
				points2D[i] = new Point2D.Double(points.get(i)[0], points.get(i)[1]);
			}
			listePoints.add(points2D);
		});
		return listePoints;
	}

	private List<double[]> lireValeurs(String nomFichier) throws IOException {
		Path path = Paths.get(nomRepertoire + nomFichier);
		try(Stream<String> lignes = Files.lines(path)) {
			return lignes
					.skip(nbLignesSautees)
					.map(ligne -> ligne.split(","))
					.map(Arrays::stream)
					.map(x -> x.mapToDouble(Double::valueOf))
					.map(DoubleStream::toArray)
					.toList();
		}
	}



}

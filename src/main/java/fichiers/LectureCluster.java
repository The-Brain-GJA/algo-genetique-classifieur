package fichiers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

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

	public List<double[]> lire(String nomFichier) throws IOException {
		
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

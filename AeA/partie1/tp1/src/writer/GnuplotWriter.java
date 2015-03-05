package writer;

import java.io.IOException;
import java.util.List;

import pattern.Genome;
import algorithms.util.StrandOccurences;

public class GnuplotWriter {

	private GnuplotFileBuilder gnuplotFileBuilder;

	public GnuplotWriter() {
		gnuplotFileBuilder = new GnuplotFileBuilder();
	}

	public void generateDotplot(String gnuplotFilename, String dataFilename,
			String outputFilename, Genome genome,
			List<StrandOccurences> occurences) throws IOException {
		gnuplotFileBuilder.buildDataFile(dataFilename, genome, occurences);
		gnuplotFileBuilder.buildGnuplotFile(gnuplotFilename, dataFilename,
				outputFilename, genome.getSize());
		final Runtime rt = Runtime.getRuntime();
		System.out.println("Generation du dotplot...");
		rt.exec("gnuplot " + gnuplotFilename);
		System.out.println("Dotplot genere avec succes (fichier dotplot.jpg)");
	}

}

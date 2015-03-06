package writer;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logger.LoggerFactory;
import pattern.Genome;
import algorithms.util.StrandOccurences;

public class GnuplotWriter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GnuplotWriter.class);
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
		LOGGER.log(Level.INFO, "Generation du dotplot...");
		rt.exec("gnuplot " + gnuplotFilename);
		LOGGER.log(Level.INFO, "Dotplot genere avec succes (fichier dotplot.jpg)");
	}

}

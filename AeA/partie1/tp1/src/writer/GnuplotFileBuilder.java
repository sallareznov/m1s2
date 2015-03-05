package writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import pattern.Genome;
import algorithms.util.StrandOccurences;

public class GnuplotFileBuilder {

	public void buildGnuplotFile(String gnuplotFilename, String dataFilename,
			String outputFilename, int genomeSize) throws IOException {
		BufferedWriter gnuplotWriter = new BufferedWriter(new FileWriter(
				gnuplotFilename));
		gnuplotWriter.write("set term jpeg;\n");
		gnuplotWriter.write("set nokey;\n");
		gnuplotWriter.write("set output '" + outputFilename + "';\n");
		gnuplotWriter.write("set xrange[0:" + (genomeSize) + "];\n");
		gnuplotWriter.write("set yrange[0:" + (genomeSize) + "];\n");
		gnuplotWriter.write("plot '" + dataFilename
				+ "' with points pointtype 0;\n");
		gnuplotWriter.close();
	}

	public void buildDataFile(String dataFilename, Genome genome,
			List<StrandOccurences> occurences) throws IOException {
		final BufferedWriter dataWriter = new BufferedWriter(new FileWriter(
				dataFilename));
		final Character[] bases = genome.getContent();
		for (final StrandOccurences strandOccurence : occurences) {
			final List<Integer> indexes = strandOccurence.getOccurences();
			for (final int index : indexes) {
				for (final int index2 : indexes) {
					if (bases[index] == bases[index2]) {
						dataWriter.write(index + " " + index2 + "\n");
					}
				}
			}
		}
		dataWriter.close();
	}

}

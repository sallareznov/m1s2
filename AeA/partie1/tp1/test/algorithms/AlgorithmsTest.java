package algorithms;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import patterns.Alphabet;
import patterns.Genome;
import patterns.Strand;
import algorithms.util.StrandOccurences;

@RunWith(Parameterized.class)
public class AlgorithmsTest {

	private Algorithm testedAlgorithm;
	private List<Strand> strandsToLookFor;

	public AlgorithmsTest(Algorithm algorithm) {
		this.testedAlgorithm = algorithm;
	}

	@Parameters
	public static Collection<Object[]> data() {
		final Character[] letters = { 'A', 'C', 'G', 'T' };
		final Alphabet alphabet = new Alphabet(letters);
		final String motif = "CTACTATATATC";
		final Genome genome = new Genome(motif, alphabet);
		final Object[][] data = { { new BruteForceAlgorithm(genome) },
				{ new ShiftOrAlgorithm(genome) },
				{ new KarpRabinAlgorithm(genome) } };
		return Arrays.asList(data);
	}

	@Before
	public void setUp() {
		final Strand mainStrand = new Strand("TATA");
		final Strand complementaryStrand = mainStrand.getComplementary();
		final Strand reverseStrand = mainStrand.getReverse();
		final Strand reverseComplementaryStrand = mainStrand
				.getReverseComplementary();
		strandsToLookFor = Arrays.asList(mainStrand, complementaryStrand,
				reverseStrand, reverseComplementaryStrand);
	}

	@Test
	public void testAlgorithms() {
		final List<StrandOccurences> expectedOccurences = new ArrayList<StrandOccurences>();
		final StrandOccurences mainAndReverseComplementaryStrandsOccurences = new StrandOccurences();
		mainAndReverseComplementaryStrandsOccurences.addIndex(4);
		mainAndReverseComplementaryStrandsOccurences.addIndex(6);
		final StrandOccurences complementaryAndReverseStrandsOccurences = new StrandOccurences();
		complementaryAndReverseStrandsOccurences.addIndex(5);
		complementaryAndReverseStrandsOccurences.addIndex(7);
		expectedOccurences.add(mainAndReverseComplementaryStrandsOccurences);
		expectedOccurences.add(complementaryAndReverseStrandsOccurences);
		expectedOccurences.add(complementaryAndReverseStrandsOccurences);
		expectedOccurences.add(mainAndReverseComplementaryStrandsOccurences);
		final List<StrandOccurences> actualOccurences = testedAlgorithm
				.findRepetitiveStrands(strandsToLookFor);
		assertEquals(expectedOccurences, actualOccurences);
	}
}

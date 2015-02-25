package algorithms;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import patterns.Genome;
import patterns.Strand;
import algorithms.util.StrandOccurences;
import bases.util.Alphabet;
import bases.util.NonExistentPairingException;
import bases.util.Pairing;
import bases.util.PairingsManager;

@RunWith(Parameterized.class)
public class AlgorithmsTest {

	private Algorithm testedAlgorithm;
	private Genome genome;
	private List<Strand> strandsToLookFor;
	private PairingsManager pairingsManager;
	private Alphabet alphabet;

	public AlgorithmsTest(Algorithm algorithm) {
		testedAlgorithm = algorithm;
		alphabet = new Alphabet();
		alphabet.addBase('A');
		alphabet.addBase('C');
		alphabet.addBase('G');
		alphabet.addBase('T');
		pairingsManager = new PairingsManager();
		pairingsManager.addPairing(new Pairing('A', 'T', true));
		pairingsManager.addPairing(new Pairing('C', 'G', true));
		final String motif = "CTACTATATATC";
		genome = new Genome(motif, pairingsManager);
	}

	@Parameters
	public static Collection<Object[]> data() {
		final Object[][] data = { { new BruteForceAlgorithm() },
				{ new ShiftOrAlgorithm() }, { new KarpRabinAlgorithm() },
				{ new KMPAlgorithm() }, { new BoyerMooreAlgorithm() } };
		return Arrays.asList(data);
	}

	@Before
	public void setUp() throws NonExistentPairingException {
		final Strand mainStrand = new Strand("TATA", pairingsManager);
		final Strand complementaryStrand = mainStrand.getComplementary();
		final Strand reverseStrand = mainStrand.getReverse();
		final Strand reverseComplementaryStrand = mainStrand
				.getReverseComplementary();
		strandsToLookFor = Arrays.asList(mainStrand, complementaryStrand,
				reverseStrand, reverseComplementaryStrand);
	}

	@Test
	public void testAlgorithms() {
		final List<StrandOccurences> expectedOccurences = new LinkedList<StrandOccurences>();
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
				.findRepetitiveStrands(genome, strandsToLookFor, alphabet);
		System.out.println(expectedOccurences.get(0).equals(
				actualOccurences.get(0)));
		System.out.println(expectedOccurences);
		System.out.println(actualOccurences);
		assertEquals(expectedOccurences, actualOccurences);
	}
}

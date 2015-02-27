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
import org.mockito.Mockito;

import patterns.Genome;
import patterns.Strand;
import algorithms.util.StrandOccurences;
import bases.Alphabet;
import bases.PairingsManager;
import bases.util.NonExistentPairingException;

@RunWith(Parameterized.class)
public class AlgorithmsTest {

	private Algorithm testedAlgorithm;
	private Genome genome;
	private List<Strand> strandsToLookFor;
	private PairingsManager pairingsManager;
	private Alphabet alphabet;

	public AlgorithmsTest(Algorithm algorithm) throws NonExistentPairingException {
		testedAlgorithm = algorithm;
		alphabet = Mockito.mock(Alphabet.class);
		Mockito.when(alphabet.size()).thenReturn(4);
		final Character[] bases = {'A', 'C', 'G', 'T'};
		Mockito.when(alphabet.getBases()).thenReturn(bases);
		pairingsManager = Mockito.mock(PairingsManager.class);
		Mockito.when(pairingsManager.getComplementaryOf('A')).thenReturn('T');
		Mockito.when(pairingsManager.getComplementaryOf('C')).thenReturn('G');
		Mockito.when(pairingsManager.getComplementaryOf('G')).thenReturn('C');
		Mockito.when(pairingsManager.getComplementaryOf('T')).thenReturn('A');
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
		assertEquals(expectedOccurences, actualOccurences);
	}
}

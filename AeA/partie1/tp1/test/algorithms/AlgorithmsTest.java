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

<<<<<<< HEAD
import patterns.Genome;
=======
import bases.util.NonExistentPairingException;
import bases.util.Pairing;
import bases.util.PairingsManager;
>>>>>>> 83c7654eb9831a9011ba94acf72b11a0b64cfbbd
import patterns.Strand;
import patterns.Genome;
import algorithms.util.StrandOccurences;
import bases.util.NonExistentPairingException;
import bases.util.Pairing;
import bases.util.PairingsManager;

@RunWith(Parameterized.class)
public class AlgorithmsTest
{

	private Algorithm testedAlgorithm;
	private Genome genome;
	private List<Strand> strandsToLookFor;
	private PairingsManager pairingsManager;

	public AlgorithmsTest(Algorithm algorithm)
	{
		testedAlgorithm = algorithm;
<<<<<<< HEAD
		pairingsManager = new PairingsManager();
		pairingsManager.addPairing(new Pairing('A', 'T', true));
		pairingsManager.addPairing(new Pairing('C', 'G', true));
		final Character[] letters = { 'A', 'C', 'G', 'T' };
		//final Alphabet alphabet = new Alphabet(letters);
		final String motif = "CTACTATATATC";
		genome = new Genome(motif);
	}

	@Parameters
	public static Collection<Object[]> data() {
		final Object[][] data = {
				{ new BruteForceAlgorithm() }/*,
				{ new ShiftOrAlgorithm() },
				{ new KarpRabinAlgorithm() },
				{ new KMPAlgorithm() },
				{ new BoyerMooreAlgorithm() }*/
		};
=======
		final String motif = "CTACTATATATC";
		final PairingsManager manager = new PairingsManager();
		manager.addPairing(new Pairing('A', 'T', true));
		manager.addPairing(new Pairing('C', 'G', true));
		this.genome = new Genome(motif, manager);
	}

	@Parameters
	public static Collection<Object[]> data()
	{
		final Object[][] data = { { new BruteForceAlgorithm() },
				{ new ShiftOrAlgorithm() }, { new KarpRabinAlgorithm() },
				{ new KMPAlgorithm() }, { new BoyerMooreAlgorithm() } };
>>>>>>> 83c7654eb9831a9011ba94acf72b11a0b64cfbbd
		return Arrays.asList(data);
	}

	@Before
<<<<<<< HEAD
	public void setUp() throws NonExistentPairingException {
		final Strand mainStrand = new Strand("TATA");
=======
	public void setUp() throws NonExistentPairingException
	{
		final Strand mainStrand = new Strand("TATA", this.genome.getManager());
>>>>>>> 83c7654eb9831a9011ba94acf72b11a0b64cfbbd
		final Strand complementaryStrand = mainStrand.getComplementary();
		final Strand reverseStrand = mainStrand.getReverse();
		final Strand reverseComplementaryStrand = mainStrand
				.getReverseComplementary();
		strandsToLookFor = Arrays.asList(mainStrand, complementaryStrand,
				reverseStrand, reverseComplementaryStrand);
	}

	@Test
	public void testAlgorithms()
	{
		final List<StrandOccurences> expectedOccurences = new LinkedList<StrandOccurences>();
		final StrandOccurences mainAndReverseComplementaryStrandsOccurences = new StrandOccurences();
		mainAndReverseComplementaryStrandsOccurences.addIndex(4);
		mainAndReverseComplementaryStrandsOccurences.addIndex(6);
		final StrandOccurences complementaryAndReverseStrandsOccurences = new StrandOccurences();
		complementaryAndReverseStrandsOccurences.addIndex(5);
		complementaryAndReverseStrandsOccurences.addIndex(7);
		expectedOccurences.add(mainAndReverseComplementaryStrandsOccurences);
		expectedOccurences.add(complementaryAndReverseStrandsOccurences);
		expectedOccurences.add(mainAndReverseComplementaryStrandsOccurences);
		expectedOccurences.add(complementaryAndReverseStrandsOccurences);
		final List<StrandOccurences> actualOccurences = testedAlgorithm
				.findRepetitiveStrands(genome, strandsToLookFor, pairingsManager);
		assertEquals(expectedOccurences, actualOccurences);
	}
}

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

import bases.util.NonExistentPairingException;
import bases.util.Pairing;
import bases.util.PairingsManager;
import patterns.Strand;
import patterns.Genome;
import algorithms.util.StrandOccurences;

@RunWith(Parameterized.class)
public class AlgorithmsTest
{

	private Algorithm testedAlgorithm;
	private Genome genome;
	private List<Strand> strandsToLookFor;

	public AlgorithmsTest(Algorithm algorithm)
	{
		testedAlgorithm = algorithm;
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
		return Arrays.asList(data);
	}

	@Before
	public void setUp() throws NonExistentPairingException
	{
		final Strand mainStrand = new Strand("TATA", this.genome.getManager());
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
				.findRepetitiveStrands(genome, strandsToLookFor);
		assertEquals(expectedOccurences, actualOccurences);
	}
}

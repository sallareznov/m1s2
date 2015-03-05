package patterns;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import pattern.Strand;
import bases.PairingsManager;
import bases.util.NonExistentPairingException;

/**
 * Classe de test de la classe Strand
 */
public class StrandTest {

	private Strand testedStrand;
	private PairingsManager pairingsManager;

	@Before
	public void setUp() throws NonExistentPairingException {
		final Character[] bases = { 'G', 'A', 'T', 'A', 'C', 'A' };
		pairingsManager = Mockito.mock(PairingsManager.class);
		Mockito.when(pairingsManager.getComplementaryOf('A')).thenReturn('T');
		Mockito.when(pairingsManager.getComplementaryOf('C')).thenReturn('G');
		Mockito.when(pairingsManager.getComplementaryOf('G')).thenReturn('C');
		Mockito.when(pairingsManager.getComplementaryOf('T')).thenReturn('A');
		testedStrand = new Strand(bases, pairingsManager);
	}

	@Test
	public void testComplementary() throws NonExistentPairingException {
		final Strand complementaryStrand = testedStrand.getComplementary();
		assertEquals("Le brin complementaire est-il correct ?", "CTATGT",
				complementaryStrand.toString());
	}

	@Test
	public void testReverse() {
		final Strand reverseStrand = testedStrand.getReverse();
		assertEquals("Le brin reverse est-il correct ?", "ACATAG",
				reverseStrand.toString());
	}

	@Test
	public void testReverseComplementary() throws NonExistentPairingException {
		final Strand reverseComplementaryStrand = testedStrand
				.getReverseComplementary();
		assertEquals("Le brin reverse-complementaire est-il correct ?",
				"TGTATC", reverseComplementaryStrand.toString());
	}

	@Test
	public void testPrefix() {
		testedStrand = new Strand("TACTAGA", pairingsManager);
		Strand prefix = testedStrand.getPrefix(6);
		assertEquals("TACTAG", prefix.toString());
		assertTrue(testedStrand.isPrefix(prefix));
		prefix = testedStrand.getPrefix(5);
		assertEquals("TACTA", prefix.toString());
		assertTrue(testedStrand.isPrefix(prefix));
		prefix = testedStrand.getPrefix(4);
		assertEquals("TACT", prefix.toString());
		assertTrue(testedStrand.isPrefix(prefix));
		prefix = testedStrand.getPrefix(3);
		assertEquals("TAC", prefix.toString());
		assertTrue(testedStrand.isPrefix(prefix));
		prefix = testedStrand.getPrefix(2);
		assertEquals("TA", prefix.toString());
		assertTrue(testedStrand.isPrefix(prefix));
		prefix = testedStrand.getPrefix(1);
		assertEquals("T", prefix.toString());
		assertTrue(testedStrand.isPrefix(prefix));
		prefix = testedStrand.getPrefix(0);
		assertEquals("", prefix.toString());
		assertFalse(testedStrand.isPrefix(prefix));
	}

	@Test
	public void testSuffix() {
		testedStrand = new Strand("TACTAGA", pairingsManager);
		Strand suffix = testedStrand.getSuffix(6);
		assertEquals("ACTAGA", suffix.toString());
		assertTrue(testedStrand.isSuffix(suffix));
		suffix = testedStrand.getSuffix(5);
		assertEquals("CTAGA", suffix.toString());
		assertTrue(testedStrand.isSuffix(suffix));
		suffix = testedStrand.getSuffix(4);
		assertEquals("TAGA", suffix.toString());
		assertTrue(testedStrand.isSuffix(suffix));
		suffix = testedStrand.getSuffix(3);
		assertEquals("AGA", suffix.toString());
		assertTrue(testedStrand.isSuffix(suffix));
		suffix = testedStrand.getSuffix(2);
		assertEquals("GA", suffix.toString());
		assertTrue(testedStrand.isSuffix(suffix));
		suffix = testedStrand.getSuffix(1);
		assertEquals("A", suffix.toString());
		assertTrue(testedStrand.isSuffix(suffix));
		suffix = testedStrand.getSuffix(0);
		assertEquals("", suffix.toString());
		assertFalse(testedStrand.isSuffix(suffix));
	}

	@Test
	public void testEdge() {
		testedStrand = new Strand("TACAGTA", pairingsManager);
		Strand edge = new Strand("TA", pairingsManager);
		assertTrue(testedStrand.isEdge(edge));
		assertFalse(testedStrand.isEdge(new Strand("T", pairingsManager)));
		assertFalse(testedStrand.isEdge(new Strand("A", pairingsManager)));
		testedStrand = new Strand("TACGAGATAC", pairingsManager);
		edge = testedStrand.getLongestEdge();
		assertEquals("TAC", edge.toString());
		testedStrand = new Strand("TACGAGA", pairingsManager);
		edge = testedStrand.getLongestEdge();
		assertEquals("", edge.toString());
	}

}

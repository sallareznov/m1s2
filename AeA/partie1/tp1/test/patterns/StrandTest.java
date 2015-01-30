package patterns;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import bases.Base;
import bases.BaseFlyweightFactory;

/**
 * Classe de test de la classe Strand
 */
public class StrandTest {

	private Strand testedStrand;

	@Before
	public void setUp() {
		final BaseFlyweightFactory baseFactory = new BaseFlyweightFactory(Alphabet.DEFAULT_ALPHABET);
		final Base[] bases = { baseFactory.createGBase(),
				baseFactory.createABase(), baseFactory.createTBase(),
				baseFactory.createABase(), baseFactory.createCBase(),
				baseFactory.createABase() };
		testedStrand = new ConcreteStrand(bases);
	}

	@Test
	public void testComplementary() {
		final Strand complementaryStrand = testedStrand.getComplementary();
		assertEquals("Le brin complementaire est-il correct ?", "CTATGT", complementaryStrand.toString());
	}
	
	@Test
	public void testReverse() {
		final Strand reverseStrand = testedStrand.getReverse();
		assertEquals("Le brin reverse est-il correct ?", "ACATAG", reverseStrand.toString());
	}
	
	@Test
	public void testReverseComplementary() {
		final Strand reverseComplementaryStrand = testedStrand.getReverseComplementary();
		assertEquals("Le brin reverse-complementaire est-il correct ?", "TGTATC", reverseComplementaryStrand.toString());
	}
	
	@Test
	public void testPrefix() {
		testedStrand = new ConcreteStrand("TACTAGA", Alphabet.DEFAULT_ALPHABET);
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
		testedStrand = new ConcreteStrand("TACTAGA", Alphabet.DEFAULT_ALPHABET);
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
		testedStrand = new ConcreteStrand("TACAGTA", Alphabet.DEFAULT_ALPHABET);
		Strand edge = new ConcreteStrand("TA",Alphabet.DEFAULT_ALPHABET);
		assertTrue(testedStrand.isEdge(edge));
		assertFalse(testedStrand.isEdge(new ConcreteStrand("T", Alphabet.DEFAULT_ALPHABET)));
		assertFalse(testedStrand.isEdge(new ConcreteStrand("A", Alphabet.DEFAULT_ALPHABET)));
		testedStrand = new ConcreteStrand("TACGAGATAC", Alphabet.DEFAULT_ALPHABET);
		edge = testedStrand.getLongestEdge();
		assertEquals("TAC", edge.toString());
		testedStrand = new ConcreteStrand("TACGAGA", Alphabet.DEFAULT_ALPHABET);
		edge = testedStrand.getLongestEdge();
		assertEquals("", edge.toString());
	}

}

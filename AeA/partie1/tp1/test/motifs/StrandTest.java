package motifs;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Classe de test de la classe Strand
 */
public class StrandTest {

	private Strand testedStrand;

	@Before
	public void setUp() {
		final BaseFactory baseFactory = new BaseFactory();
		final Base[] bases = { baseFactory.createGBase(),
				baseFactory.createABase(), baseFactory.createTBase(),
				baseFactory.createABase(), baseFactory.createCBase(),
				baseFactory.createABase() };
		testedStrand = new Strand(bases);
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

}

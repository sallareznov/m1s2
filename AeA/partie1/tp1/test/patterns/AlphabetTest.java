package patterns;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import bases.Alphabet;

public class AlphabetTest {

	private Alphabet testedAlphabet;

	@Before
	public void setUp() {
		testedAlphabet = new Alphabet();
		testedAlphabet.addBase('A');
		testedAlphabet.addBase('C');
		testedAlphabet.addBase('G');
		testedAlphabet.addBase('T');
	}

	@Test
	public void testContainsWord() {
		final char letter1 = 'C';
		final char letter2 = 'P';
		final char letter3 = 'A';
		assertTrue("La lettre C est-elle contenue dans l'alphabet ?",
				testedAlphabet.contains(letter1));
		assertFalse("La lettre P est-elle contenue dans l'alphabet ?",
				testedAlphabet.contains(letter2));
		assertTrue("La lettre A est-elle contenue dans l'alphabet ?",
				testedAlphabet.contains(letter3));
	}

}

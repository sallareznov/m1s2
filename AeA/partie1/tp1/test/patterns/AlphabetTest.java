package patterns;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import patterns.Alphabet;

public class AlphabetTest {

    private Alphabet testedAlphabet;

    @Before
    public void setUp() {
	final Character[] letters = { 'A', 'C', 'G', 'T' };
	testedAlphabet = new Alphabet(letters);
    }

    @Test
    public void testContainsWord() {
	final char letter1 = 'C';
	final char letter2 = 'P';
	final char letter3 = 'A';
	assertTrue("La lettre C est-elle contenue dans l'alphabet ?", testedAlphabet.containsLetter(letter1));
	assertFalse("La lettre P est-elle contenue dans l'alphabet ?", testedAlphabet.containsLetter(letter2));
	assertTrue("La lettre A est-elle contenue dans l'alphabet ?", testedAlphabet.containsLetter(letter3));
    }

}

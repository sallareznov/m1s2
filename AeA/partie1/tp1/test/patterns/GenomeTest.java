package patterns;

import org.junit.Test;

public class GenomeTest {
	
	@SuppressWarnings("unused")
	private Genome testedGenome;
	
	@Test
	public void testConstructorWithoutException() {
		final char[] letters = { 'A', 'C', 'G', 'T' };
		final Alphabet alphabet = new Alphabet(letters);
		final String motif = "CTACTATATATC";
		testedGenome = new Genome(motif, alphabet);
		return;
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorWithException() {
		final char[] letters = { 'A', 'C', 'G', 'T' };
		final Alphabet alphabet = new Alphabet(letters);
		final String motif = "CTACTATATUTC";
		testedGenome = new Genome(motif, alphabet);
		return;
	}

}

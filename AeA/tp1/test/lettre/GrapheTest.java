package lettre;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class GrapheTest {

	private Graphe graphe;

	@Before
	public void setUp() throws LongueursMotsDifferentesException {
		final String[] lesMots = { "lion", "pion", "paon", "lion" };
		graphe = new Graphe(lesMots);
	}

	@Test
	public void testDiffUneLettre() throws LongueursMotsDifferentesException {
		final MotGraphe mot1 = graphe.getMot(0);
		final MotGraphe mot2 = graphe.getMot(1);
		final MotGraphe mot3 = graphe.getMot(2);
		final MotGraphe mot4 = graphe.getMot(3);
		assertTrue(Graphe.diffUneLettre(mot1.getLibelle(), mot2.getLibelle()));
		assertFalse(Graphe.diffUneLettre(mot1.getLibelle(), mot3.getLibelle()));
		assertTrue(Graphe.diffUneLettre(mot2.getLibelle(), mot3.getLibelle()));
		assertFalse(Graphe.diffUneLettre(mot1.getLibelle(), mot4.getLibelle()));
	}
	
	@Test(expected=LongueursMotsDifferentesException.class)
	public void testDiffUneLettreException() throws LongueursMotsDifferentesException {
		final String mot1 = "algorithme";
		final String mot2 = "application";
		assertTrue(Graphe.diffUneLettre(mot1, mot2));
	}

	// @Test
	// public void testInitListeSuccesseursMot() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testInitListeSuccesseursTousMots() {
	// fail("Not yet implemented");
	// }

}

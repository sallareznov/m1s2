package bases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import bases.util.NonExistentPairingException;

public class PairingsManagerTest {
	
	private PairingsManager testedPairingsManager;
	
	@Before
	public void setUp() {
		testedPairingsManager = new PairingsManager();
		testedPairingsManager.addPairing(new Pairing('A', 'T', true));
		testedPairingsManager.addPairing(new Pairing('C', 'G', true));
	}
	
	@Test
	public void testGetComplementaryOfWithoutException() throws NonExistentPairingException {
		assertEquals('T', testedPairingsManager.getComplementaryOf('A'));
		assertEquals('G', testedPairingsManager.getComplementaryOf('C'));
		assertEquals('C', testedPairingsManager.getComplementaryOf('G'));
		assertEquals('A', testedPairingsManager.getComplementaryOf('T'));
	}
	
	@Test
	public void testContains() {
		assertTrue(testedPairingsManager.contains('C'));
		assertTrue(testedPairingsManager.contains('G'));
		assertTrue(testedPairingsManager.contains('A'));
		assertTrue(testedPairingsManager.contains('T'));
		assertFalse(testedPairingsManager.contains('U'));
		assertFalse(testedPairingsManager.contains('N'));
	}

}

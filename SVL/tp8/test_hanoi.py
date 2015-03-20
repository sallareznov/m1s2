from mockito import *
import unittest
from hanoi import *

class TestDisque(unittest.TestCase):

	def test_disque_diameter(self):
		disque = Disque(10)
		self.assertEqual(disque.getDiametre(),10)

	def test_disque_est_plus_petit_d_un_autre(self):
		disque1 = Disque(10)
		disque2 = Disque(20)
		disque3 = Disque(5)
		self.assertTrue(disque1.estPlusPetitQue(disque2))
		self.assertFalse(disque1.estPlusPetitQue(disque3))
		self.assertTrue(disque1.estPlusPetitQue(None))

class TestTour(unittest.TestCase):

	def test_empile(self):
		tour = Tour(4)
		disque = mock()
		when(disque).getDiametre().thenReturn(4)
		when(disque).estPlusPetitQue(any()).thenReturn(True)
		tour.empile(disque)
		self.assertEqual(tour.getNbreDisques(), 1)
		self.assertEqual(tour.disqueAuSommet().getDiametre(), 4)

	def test_depile(self):
		tour = Tour(4)
		self.assertEqual(tour.depile(), None)
		disque = mock()
		when(disque).getDiametre().thenReturn(4)
		tour.empile(disque)
		tour.depile()
		self.assertEqual(tour.getNbreDisques(), 0)

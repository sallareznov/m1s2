# Salla DIAGNE
# Contrôle TP SVL

import unittest
from shut_the_box import *

class TestClapet(unittest.TestCase):

	def setUp(self):
		self.clapet = Clapet()

	def test_fermer_un_clapet(self):
		self.clapet.fermer()
		self.assertEqual(False, self.clapet.estOuvert())

	def test_ouvrir_un_clapet(self):
		self.clapet.ouvrir()
		self.assertEqual(True, self.clapet.estOuvert())

class TestBoite(unittest.TestCase):

	def setUp(self):
		self.clapet1 = Clapet()
		self.clapet2 = Clapet()
		self.boite = Boite([self.clapet1, self.clapet2])

	def test_ouvrir_la_boite_ouvre_tous_ses_clapets(self):
		self.boite.ouvrir()
		self.assertEqual(True, self.clapet1.estOuvert())
		self.assertEqual(True, self.clapet2.estOuvert())

	def test_fermer_un_clapet_de_la_boite(self):
		self.boite.fermer(2)
		self.assertEqual(True, self.clapet1.estOuvert())
		self.assertEqual(False, self.clapet2.estOuvert())

	def test_fermer_le_dernier_clapet_ouvert_renvoie_une_exception(self):
		self.boite.fermer(2)
		self.assertRaises(ShutTheBoxException, self.boite.fermer, 1)

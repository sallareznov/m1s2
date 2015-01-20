import unittest
from banque_common import CompteCommon
from banque_common import CreditNegatifError
from banque_common import DebitNegatifError
from banque_v3 import CompteV3
from banque_v3 import Credit
from banque_v3 import Debit

class TestCompteBancaire(unittest.TestCase):
	
	def setUp(self):
		self.compte = CompteV3()

	def test_initialiser_un_compte_bancaire(self) :
		self.assertEqual(self.compte.listeOperations, [])

	def test_vider_la_liste_des_operations(self):
		self.compte.viderListeOperations()
		self.assertEqual(self.compte.listeOperations, [])		

	def test_crediter_un_compte_affecte_son_solde(self):
		self.compte.viderListeOperations()
		self.compte.crediter(42.0)
		self.assertEqual(self.compte.solde(), 42.0)

	def test_debiter_un_compte_affecte_son_solde(self):
		self.compte.viderListeOperations()
		self.compte.crediter(80.0)
		self.compte.debiter(20.0)
		self.assertEqual(self.compte.solde(), 60.0)

	def test_on_ne_peut_pas_crediter_un_montant_negatif(self):
		self.compte.viderListeOperations()
		self.assertRaises(CreditNegatifError, self.compte.crediter, -45.0)

	# def test_on_ne_peut_pas_debiter_un_montant_negatif(self):
	# 	self.compte.viderListeOperations()
	# 	self.assertRaises(DebitNegatifError, self.compte.debiter, -45.0)
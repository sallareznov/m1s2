import unittest
from mockito import *
from transformateur_log import *
from io import StringIO

class TestAnalyseurLog(unittest.TestCase):

	def setUp(self):
		self.fabriqueMessage = mock()
		self.analyseur = AnalyseurLog(self.fabriqueMessage)

	def test_get_message_suivant(self):
		fichier = StringIO("2010-02-25, 5, error in database\n" + "2010-02-25, 10 , system crash\n" + "2010-02-26, 2, error")
		self.analyseur.getMessageSuivant(fichier)
		verify(self.fabriqueMessage).create("2010-02-25", "5", "error in database")

	def test_get_message_incorrect(self):
		fichier = StringIO("2010-02-25, 5")
		self.assertRaises(MessageIncorrectError, self.analyseur.getMessageSuivant, fichier)

	def test_fin_du_fichier(self):
		fichier = StringIO("2010-02-25, 5, error in database\n")
		self.analyseur.getMessageSuivant(fichier)
		self.assertEqual(self.analyseur.getMessageSuivant(fichier), None)

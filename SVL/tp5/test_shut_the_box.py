#TP5 jeu Shut the box

import unittest
from shut_the_box import *
from mockito import *


class TestJeu(unittest.TestCase):

	def setUp(self):
		self.plateau = mock()
		self.joueur1 = mock()
		self.joueur2 = mock()
		self.jeu = Jeu(self.plateau,self.joueur1,self.joueur2)

	def test_joue_tour(self):
		self.jeu.setJoueurCourant(self.joueur1)
		self.jeu.joueTour()
		verify(self.joueur1).lanceDes()
		verify(self.joueur1).choisitCombinaison(any(), any())
		verify(self.plateau).fermer(any())


class TestJoueur(unittest.TestCase):
	
	def setUp(self):
		self.des = mock()
		self.joueur = Joueur(self.des)

	def test_lance_des(self):
		when(self.des).getValue().thenReturn(5)
		self.assertEqual(self.joueur.lanceDes(),5)

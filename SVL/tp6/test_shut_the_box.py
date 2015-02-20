import unittest
from mockito import *
from shut_the_box import *

# scénarios à tester pour un joueur:
# - lancer les dés et fermer des clapets
#   *cas possible lancer 1 seul dé
# - lancer les dés et être bloqué
# - lancer les dés et fermer la boîte



class TestShutTheBox(unittest.TestCase):

	def setUp(self):
		self.joueur = mock()
		self.boite = mock()
		self.jeu = ShutTheBox(self.boite, 10)

	def test_lancer_les_des_et_fermer_des_clapets(self):
		when(self.joueur).lancer_des().thenReturn(12)
		when(self.joueur).proposer_clapets(12).thenReturn([4, 8])
		when(self.boite).est_ouvert([4, 8]).thenReturn(True)

		self.jeu.jouer(self.joueur)
		verify(self.boite).est_ouvert([4, 8])
		verify(self.boite).fermer_clapets([4, 8])


	def test_lancer_des_et_etre_bloque(self):
		when(self.joueur).lancer_des().thenReturn(12)
		when(self.joueur).proposer_clapets(12).thenReturn([])
		when(self.boite).est_ouvert([]).thenReturn(False)

		self.jeu.jouer(self.joueur)
		verify(self.boite).calcule_les_points()


	def test_lancer_les_des_et_fermer_la_boite(self):
		when(self.joueur).lancer_des().thenReturn(12)
		when(self.joueur).proposer_clapets(12).thenReturn([])
		when(self.boite).est_ouvert([]).thenReturn(False)
		when(self.boite).est_fermee().thenReturn(True)
		self.jeu.jouer(self.joueur)	
		self.assertEqual(True, self.jeu.termine)

	def test_jouer_10_fois_sans_fermer_tous_les_clapets(self):
		when(self.joueur).lancer_des().thenReturn(12)
		when(self.joueur).proposer_clapets(12).thenReturn([4, 8])
		when(self.boite).est_ouvert([4, 8]).thenReturn(True)

		self.jeu.jouer_tout(self.joueur)
		verify(self.boite, times = 10).fermer_clapets([4, 8])



class TestClapet(unittest.TestCase):

	def test_etat_clapet(self):
		clapet = Clapet(10, False)
		clapet.ouvrir()
		self.assertEqual(True, clapet.est_ouvert())
		clapet.fermer()
		self.assertEqual(False, clapet.est_ouvert())


class TestJoueur(unittest.TestCase):

	def setUp(self):
		self.des = mock()
		self.joueur = Joueur(self.des)

	def test_lance_des(self):
		when(self.des).getValue().thenReturn(5)
		self.assertEqual(self.joueur.lancer_des(),5)


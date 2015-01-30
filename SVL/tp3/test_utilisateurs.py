import unittest

from mockito import *
from utilisateurs import *

class TestGestionnaire(unittest.TestCase):

	def setUp(self):
		self.baseLogins = mock()
		self.gestionnaire = GestionnaireLogins(self.baseLogins)
		self.nom = mock()
		self.prenom = mock()

	def test_creer_un_login_l_ajoute_a_la_base(self):
		login = mock()
		when(self.gestionnaire).genereLogin(self.nom, self.prenom).thenReturn(login)
		self.gestionnaire.sauvegardeLogin(self.nom, self.prenom)
		verify(self.gestionnaire).genereLogin(self.nom, self.prenom)
		verify(self.baseLogins).ajouterLogin(login)

	def test_ajouter_un_login_qui_existe(self):
		when(self.gestionnaire).genereLogin(self.nom, self.prenom).thenRaise(LoginExistantError())
		self.assertRaises(LoginExistantError, self.gestionnaire.sauvegardeLogin, self.nom, self.prenom)



	# def test_ajouter_un_login(self):
	# 	login = mock()
	# 	baseLogins = BaseLogins()
	# 	baseLogins.ajouterLogin(login)
	# 	verify(baseLogins).getContenu()
	# 	assertEqual(resultat,)


	
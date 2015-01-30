"""

"""
class GestionnaireLogins:

	def __init__(self,base):
		self.baseLogins = base

	def genereLogin(self, nom, prenom):
		if (self.baseLogins.contientLogin(nom)):
			if (self.baseLogins.contientLogin(prenom[0] + nom[:7])):
				raise LoginExistantError()
			else:
				return (prenom[0] + nom[:7])
		else:
			return nom

	def sauvegardeLogin(self, nom, prenom):
		login = self.genereLogin(nom, prenom)
		self.baseLogins.ajouterLogin(login)

class BaseLogins:

	def __init__(self):
		self.contenu = []

	def ajouterLogin(self, login):
		self.baseLogins.append(login)

	def contientLogin(self, login):
		return (login in contenu)

	def getContenu():
		return contenu


class LoginExistantError(Exception):
	pass
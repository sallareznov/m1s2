"""
Representation d'un compte bancaire.

Il contient une liste d'operations qui est vide a la creation du compte.

>>> compte = Compte()
>>> compte.solde()
0

On peut crediter un compte, ce qui affecte son solde.

>>> compte.viderListeOperations()
>>> compte.crediter(50.0)
>>> compte.solde()
50.0

On ne peut pas crediter negativement un compte.

>>> compte.crediter(-20.0)
banque_common.CreditNegatifError

On peut debiter un compte, ce qui affecte son solde.

>>> compte.viderListeOperations()
>>> compte.crediter(100.0)
>>> compte.debiter(50.0)
>>> compte.solde()
50.0

On ne peut pas debiter negativement un compte.

>>> compte.debiter(-20.0)
banque_common.DebitNegatifError
"""

class CompteV2():

	def __init__(self):
		self.listeOperations = []

	def solde(self):
		return sum(self.listeOperations)

	def viderListeOperations(self):
		self.listeOperations = []

	def crediter(self, montant):
		if (montant < 0):
			raise CreditNegatifError()
		self.listeOperations.append(montant)

	def debiter(self, montant):
		if (montant < 0):
			raise DebitNegatifError()
		self.listeOperations.append(-montant)

class CreditNegatifError(Exception):
	pass

class DebitNegatifError(Exception):
	pass

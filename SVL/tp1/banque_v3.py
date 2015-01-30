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
CreditNegatifError

On peut debiter un compte, ce qui affecte son solde.

>>> compte.viderListeOperations()
>>> compte.crediter(100.0)
>>> compte.debiter(50.0)
>>> compte.solde()
50.0

On ne peut pas debiter negativement un compte.

>>> compte.debiter(-20.0)
DebitNegatifError
"""

class CompteV3():

	def __init__(self):
		self.listeOperations = []

	def solde(self):
		solde = 0
		for op in self.listeOperations:
			solde += op.getMontant()
		return solde

	def viderListeOperations(self):
		self.listeOperations = []

	def crediter(self, montant):
		if (montant < 0):
		 	raise CreditNegatifError()
		credit = Credit(montant)
		self.listeOperations.append(credit)

	def debiter(self, montant):
		if (montant < 0):
			raise DebitNegatifError()
		debit = Debit(montant)
		self.listeOperations.append(debit)

class Credit():

	def __init__(self, montant):
		self.montant = montant

	def getMontant(self):
		return self.montant

class Debit():

	def __init__(self, montant):
		self.montant = montant

	def getMontant(self):
		return ((-1) * self.montant)

class CreditNegatifError(Exception):
	pass

class DebitNegatifError(Exception):
	pass

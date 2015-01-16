from banque_common import CompteCommon

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

class CompteV3(CompteCommon):


	def __init__(self):
		self.listeOperations = []

	def solde(self):
		solde = 0
		for op in self.listeOperations:
			solde += op.getMontant()
		return solde

	def viderListeOperations(self):
		self.listeOperations = []

	def crediter(self, credit):
		# if (credit.getMontant() < 0):
		# 	raise CreditNegatifError()
		self.listeOperations.append(credit)

 	def debiter(self, debit):
# 		if (montant < 0):
# 			raise DebitNegatifError()
 		self.listeOperations.append(debit)

class Operation:

	def getMontant(self):
		pass

class Credit(Operation):

	def __init__(self, montant):
		self.montant = montant

	def getMontant(self):
		return self.montant

class Debit(Operation):

	def __init__(self, montant):
		self.montant = montant

	def getMontant(self):
		return -self.montant
# class CreditNegatifError(Exception):
# 	pass

# class DebitNegatifError(Exception):
# 	pass
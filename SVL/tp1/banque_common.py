"""
"""

class CreditNegatifError(Exception):
	pass

class DebitNegatifError(Exception):
	pass

class CompteCommon():

	def solde(self):
		pass

	def viderListeOperations(self):
		pass
	
	def crediter(self, montant):
		pass
	
	def debiter(self, montant):
		pass
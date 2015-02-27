# Salla DIAGNE
# Contrôle TP SVL

"""
Representation d'un clapet.

Un clapet est initialement ouvert.

>>> clapet = Clapet()
>>> clapet.estOuvert()
True

On peut fermer ou ouvrir un clapet.

>>> clapet.fermer()
>>> clapet.estOuvert()
False

>>> clapet.ouvrir()
>>> clapet.estOuvert()
True
"""
class Clapet():

	def __init__(self):
		self.ouvert = True

	def estOuvert(self):
		return self.ouvert

	def ouvrir(self):
		self.ouvert = True

	def fermer(self):
		self.ouvert = False

class Boite():

	def __init__(self, clapets):
		self.clapets = clapets

	def ouvrir(self):
		for clapet in self.clapets:
			clapet.ouvrir()

	def estFermee(self):
		for clapet in self.clapets:
			if (clapet.estOuvert()):
				return False
		return True

	def fermer(self, numeroClapet):
		self.clapets[numeroClapet - 1].fermer()
		if (self.estFermee()):
			raise ShutTheBoxException()

class ShutTheBoxException(Exception):
	pass

class Disque:
	"""
	inv:
		self.diametre > 0
	"""

	def __init__(self, diametre):
		"""
		post:
			self.diametre == diametre
        """
		self.diametre = diametre

	def getDiametre(self):
		return self.diametre

	def estPlusPetitQue(self, disque):
		if (disque == None):
			return True
		return self.diametre <= disque.getDiametre()

class Tour:
	"""
	inv:
		self.nbreDisques >= 0
		self.capacite > 0
	"""

	def __init__(self, capacite):
		"""
		pre:
			capacite > 0
		post:
			self.nbreDisques == 0
			self.capacite == capacite
			self.disques == [None] * capacite
        """
		self.nbreDisques = 0
		self.capacite = capacite
		self.disques = [None] * self.capacite

	def getNbreDisques(self):
		return self.nbreDisques

	def disqueAuSommet(self):
		return self.disques[self.nbreDisques - 1]

	def empile(self, disque):
		if((self.disqueAuSommet() == None and self.capacite > 0) or (self.nbreDisques < self.capacite and disque.estPlusPetitQue(self.disqueAuSommet()))):
			self.disques[self.nbreDisques] = disque
			self.nbreDisques = self.nbreDisques + 1


	def depile(self):
		if (self.nbreDisques > 0):
			disque = self.disques[self.nbreDisques - 1]
			self.disques[self.nbreDisques - 1] = None
			self.nbreDisques = self.nbreDisques - 1
			return disque
		else:
			return None

class Hanoi:

	def __init__(self, tours):
		self.tours = tours

	def deplacerUnDisque(self, tourA, tourB):
		tourB.empile(tourA.depile())

	def deplacerDesDisques(self, n, tourA, tourC, tourB):
		if (n == 1):
			self.deplacerUnDisque(tourA, tourC)
		else:
			self.deplacerDesDisques(n-1, tourA, tourB, tourC)
			self.deplacerUnDisque(tourA, tourC)
			self.deplacerDesDisques(n-1, tourB, tourC, tourA)

	def resolution(self):
		self.deplacerDesDisques(self.tours[0].getNbreDisques(), self.tours[0], self.tours[2], self.tours[1])

#import contract
#contract.checkmod(__name__)

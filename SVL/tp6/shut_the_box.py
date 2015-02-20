from random import *

class ShutTheBox :

	def __init__(self, boite, nbToursMaximal):
		self.boite = boite
		self.termine = False
		self.nbToursMaximal = nbToursMaximal
		self.toursJoues = 0

	def jouer(self, joueur):
		lancer = joueur.lancer_des()
		proposition = joueur.proposer_clapets(lancer)
		if self.boite.est_ouvert(proposition):
			self.boite.fermer_clapets(proposition)
		else:
			points = self.boite.calcule_les_points()
			joueur.ajoute_points(points)
			if (self.boite.est_fermee()):
				self.termine = True

	def jouer_tout(self, joueur):
		while ((self.toursJoues < self.nbToursMaximal) and (not self.termine)):
			self.jouer(joueur)
			self.toursJoues += 1


class Clapet:

	def __init__(self, numero, ouvert):
		self.numero = numero
		self.ouvert = ouvert

	def est_ouvert(self):
		return self.ouvert

	def fermer(self):
		self.ouvert = False

	def ouvrir(self):
		self.ouvert = True


class CombinaisonClapets:
	
	def __init__(self):
		self.clapets = []

	def ajoute_clapet(self, clapet):
		self.clapets.append(clapet)

	def get_clapets(self):
		return self.clapets


class Des:

	def getValue():
		return random.randint(1,12)

class Joueur:

	def __init__(self, des):
		self.nbPoints = 0
		self.des = des

	def lancer_des(self):
		return self.des.getValue()

	def proposer_clapets(self, score, combinaison):
		pass
		#choisi 1er clapet
		#si 1er clapet > score then exception
		#Si clapet.getNumero() != score
		#choisi un deuxieme clapet
		#si la somme des clapets choisit n'est pas egale à score then exception

class Boite:
	pass

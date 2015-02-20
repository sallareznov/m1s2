#TP5 jeu Shut the box

from random import *

class Jeu(object):
	
	def __init__(self,plateau, joueur1, joueur2):
		self.plateau = plateau
		self.joueur1 = joueur1
		self.joueur2 = joueur2
		self.joueurCourant = joueur1

	def setJoueurCourant(self, joueur):
		self.joueurCourant = joueur

	def joueTour(self):
		scoreDes = self.joueurCourant.lanceDes()
		choix = self.joueurCourant.choisitCombinaison(scoreDes)
		self.plateau.fermer(choix)


class Plateau:
	pass

class Joueur:
	
	def __init__(self, des):
		self.nbPoints = 0
		self.des = des

	def lanceDes(self):
		return self.des.getValue()

	def choisitCombinaison(self, score):
		print(score)
		print("1 ou 2 choix ?")
		combinaison = CombinaisonClapets()
		n = raw_input()
		if n == 1:
			r = score
			combinaison.ajouteClapets(r)
		if n == 2:
			r1 = raw_input()
			r2 = raw_input()
		if (r1 + r2) != score :
			raise CombinaisonInvalideError()
		combinaison.ajouteClapets(r1)
		combinaison.ajouteClapets(r2)
		return combinaison


class Combinaison():
	
	def _init__(self):
		self.liste = []

	def ajouteClapets(choix):
		self.liste.append(choix)


class CombinaisonInvalideError(Exception):
	pass

class Des:

	def getValue():
		return random.randint(1,12)

class Message:

	def __init__(self, date, priorite, texte):
		self.date = date
		self.priorite = int(priorite)
		self.texte = texte

	def getDate(self):
		return self.date

	def getPriorite(self):
		return self.priorite

	def getTexte(self):
		return self.texte

	def __str__(self):
		return (self.date + ", " + self.priorite + ", " + self.texte)

class AnalyseurLog:

	def __init__(self, fabriqueMessage):
		self.fabriqueMessage = fabriqueMessage

	def getMessageSuivant(self, fichierEntree):
		ligne = fichierEntree.readline()
		if (ligne == ""):
			return None
		tab_infos = ligne.split(',')
		if (len(tab_infos) != 3):
			raise MessageIncorrectError()
		return self.fabriqueMessage.create(tab_infos[0].strip(), tab_infos[1].strip(), tab_infos[2].strip())

class FabriqueMessage:

	def create(self, date, priorite, texte):
		return Message(date, priorite, texte)

class MessageIncorrectError(Exception):
	pass
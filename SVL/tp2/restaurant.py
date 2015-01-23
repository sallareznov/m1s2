# SVL 1415 - CTD2 demo

"""

Restaurant d'entreprise.

Caisse, Carte. 

Fonctionnalités : 
- visualiser le solde d'une carte
- insérer une carte

"""

class Carte :
    """
    Carte personnelle d'un employe

    La carte peut faire office de porte-monnaie électronique.
    On peut créditer le porte-monnaie.

    >>> carte = Carte()
    >>> carte.crediter_porte_monnaie(20.0)
    >>> carte.solde()
    20.0

    On peut debiter le porte-monnaie.
    
    >>> carte.debiter_porte_monnaie_sans_ticket(5.0)
    >>> carte.solde()
    15.0
    """
    
    def __init__(self):
    	self.solde_carte = 0.0
    
    def solde(self) :
    	return self.solde_carte

    def crediter_porte_monnaie(self,montant):
    	self.solde_carte += montant

    def debiter_porte_monnaie_sans_ticket(self, montant_repas):
    	self.solde_carte -= montant_repas

    def debiter_porte_monnaie_avec_ticket(self, montant_repas, montant_ticket):
    	self.solde_carte -= montant_repas - montant_ticket

class Caisse :
    """
    On ne peut pas visualiser le solde d'une carte si elle n'est pas insérée. 

    >>> caisse = Caisse()
    >>> caisse.solde()
    Traceback (most recent call last):
    ...
    restaurant.AucuneCarteInsereeError

    Une fois la carte insérée, on peut visualiser son solde

    >>> carte = Carte()
    >>> carte.crediter_porte_monnaie(20.0)
    >>> caisse.inserer_carte(carte)
    >>> caisse.solde()
    20.0

    On peut payer un repas sans utiliser de ticket-repas, la carte est débitée. 

    >>> caisse.payer_repas_sans_ticket(15.0)
    >>> carte.solde()
    5.0

    On peut payer un repas en utilisant un ticket. La carte est débitée de la soustraction
    du montant du ticket par rapport du montant du repas

    >>> caisse.payer_repas_avec_ticket(7.0,5.0)
    >>> carte.solde()
    3.0

    Le paiement est refusé si la carte n'est pas solvable. 
    
    >>> caisse.payer_repas_sans_ticket(10.0)
    Traceback (most recent call last):
    ...
    restaurant.SoldePorteMonnaieInsuffisantError

    Le paiement est impossible si aucune carte n'est insérée.

    >>> caisse = Caisse()
    >>> caisse.payer_repas_sans_ticket(15.0)
    Traceback (most recent call last):
    ...
    restaurant.AucuneCarteInsereeError

    """

    def __init__(self) :
        self.carte = None


    
    def solde(self) :
        """
        Retourne le solde de la carte
        """
        if self.carte == None :
            raise AucuneCarteInsereeError()
        return self.carte.solde()


    def inserer_carte(self, une_carte) :
        self.carte = une_carte

    def payer_repas_sans_ticket(self, montant_repas) :
    	if self.carte == None :
            raise AucuneCarteInsereeError()
    	if (self.carte.solde() < montant_repas) :
    		raise SoldePorteMonnaieInsuffisantError()
    	self.carte.debiter_porte_monnaie_sans_ticket(montant_repas)
    
    def payer_repas_avec_ticket(self, montant_repas, montant_ticket) :
    	if self.carte == None :
            raise AucuneCarteInsereeError()
    	if (self.carte.solde() + montant_ticket) < montant_repas :
    		raise SoldePorteMonnaieInsuffisantError()
    	self.carte.debiter_porte_monnaie_avec_ticket(montant_repas, montant_ticket)

class AucuneCarteInsereeError(Exception) :
    pass

class SoldePorteMonnaieInsuffisantError(Exception) :
    pass


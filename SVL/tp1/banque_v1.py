# demo CTD1 SVL 2014-2015

"""
Representation d'un compte bancaire.

On peut creer un compte au solde nul.

>>> compte = Compte()
>>> compte.solde()
0.0

On peut crediter un compte, ce qui affecte son solde.

>>> compte.crediter(50.0)
>>> compte.solde()
50.0

On ne peut pas crediter negativement un compte.

>>> compte.crediter(-20.0)
Traceback (most recent call last):
...
banque.CreditNegatifError
"""

class Compte :

    def __init__(self) :
        self.solde_interne = 0.0

    def solde(self) :
        return self.solde_interne

    def crediter(self, montant) :
        """
        Crediter le compte du montant passe en parametre.

        >>> compte = Compte()
        >>> compte.crediter(50.0)
        >>> compte.solde()
        50.0
        """
        if montant < 0 :
            raise CreditNegatifError()
        self.solde_interne += montant

class CreditNegatifError(Exception) :
    pass

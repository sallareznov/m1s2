#SVL 1415 - CTD2 demo

import unittest

from mockito import *
from restaurant import *

class TestVisualiserSoldeCarte(unittest.TestCase) :

    def test_visualiser_le_solde_est_impossible_avant_insertion_de_carte(self):
        caisse = Caisse()
        self.assertRaises(AucuneCarteInsereeError,
                          caisse.solde)

    def test_le_solde_de_la_caisse_est_celui_de_la_carte(self) :
        caisse = Caisse()
        carte = mock()
        caisse.inserer_carte(carte)
        when(carte).solde().thenReturn(20.0)
        self.assertEqual(caisse.solde(), 20.0)

class TestPayerUnRepasAvecTicketRepas(unittest.TestCase) :

    def test_payer_repas_avec_ticket_si_carte_non_presente(self):
        caisse = Caisse()

        montant_repas = 15.0
        montant_ticket = 10.0

        self.assertRaises(AucuneCarteInsereeError,
                          caisse.payer_repas_avec_ticket,
                          montant_repas, montant_ticket)

    def test_payer_un_repas_avec_ticket_debite_la_carte(self) :
        caisse = Caisse()
        carte = mock()

        montant_repas = 15.0
        montant_ticket = 10.0

        when(carte).solde().thenReturn(5.0)

        caisse.inserer_carte(carte)

        caisse.payer_repas_avec_ticket(montant_repas, montant_ticket)
        verify(carte).debiter_porte_monnaie_avec_ticket(montant_repas, montant_ticket)

    def test_impossible_de_payer_un_repas_avec_ticket_si_carte_insolvable(self):
        caisse = Caisse()
        carte = mock()

        montant_repas = 15.0
        montant_ticket = 10.0

        when(carte).solde().thenReturn(0.0)
        when(carte).debiter_porte_monnaie_avec_ticket(montant_repas, montant_ticket).thenRaise(SoldePorteMonnaieInsuffisantError())

        caisse.inserer_carte(carte)

        self.assertRaises(SoldePorteMonnaieInsuffisantError,
                          caisse.payer_repas_avec_ticket,
                          montant_repas, montant_ticket) 

class TestPayerUnRepasSansTicketRepas(unittest.TestCase) :

    def test_payer_repas_sans_ticket_si_carte_non_presente(self):
        caisse = Caisse()

        montant_repas = 15.0

        
        self.assertRaises(AucuneCarteInsereeError,
                          caisse.payer_repas_sans_ticket,
                          montant_repas)

    def test_payer_un_repas_sans_ticket_debite_la_carte(self) :
        caisse = Caisse()
        carte = mock()

        montant_repas = 15.0
        
        when(carte).solde().thenReturn(20.0)

        caisse.inserer_carte(carte)
        caisse.payer_repas_sans_ticket(montant_repas)

        verify(carte).debiter_porte_monnaie_sans_ticket(montant_repas)

    def test_impossible_de_payer_un_repas_sans_ticket_si_carte_insolvable(self):
        caisse = Caisse()
        carte = mock()

        montant_repas = 15.0

        when(carte).solde().thenReturn(0.0)
        when(carte).debiter_porte_monnaie_sans_ticket(montant_repas).thenRaise(SoldePorteMonnaieInsuffisantError())

        caisse.inserer_carte(carte)

        self.assertRaises(SoldePorteMonnaieInsuffisantError,
                          caisse.payer_repas_sans_ticket,
                          montant_repas)

class TestCarte(unittest.TestCase):

    def test_crediter_carte(self):
        carte = Carte()
        montant = 10.0
        carte.crediter_porte_monnaie(montant)
        self.assertEqual(carte.solde(),montant)

    def test_debiter_carte_avec_ticket(self):
        carte = Carte()
        montant_repas = 10.0
        montant_ticket = 5.0
        montant = 20.0
        carte.crediter_porte_monnaie(montant)
        carte.debiter_porte_monnaie_avec_ticket(montant_repas,montant_ticket)
        self.assertEqual(carte.solde(),15.0)

    def test_debiter_carte_sans_ticket(self):
        carte = Carte()
        montant_repas = 10.0
        montant = 20.0
        carte.crediter_porte_monnaie(montant)
        carte.debiter_porte_monnaie_sans_ticket(montant_repas)
        self.assertEqual(carte.solde(),10.0)

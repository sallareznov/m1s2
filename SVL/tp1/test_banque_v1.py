# demo CTD1 SVL 2014-2015

import unittest
from banque_v1 import Compte
from banque_v1 import CreditNegatifError

class TestCreationCompte(unittest.TestCase) :

    def test_un_compte_est_cree_avec_un_solde_nul(self) :
        compte = Compte()
        self.assertEqual(compte.solde(), 0.0)

        
class TestUnCompteEstCredite(unittest.TestCase) :

    def setUp(self) :
        self.compte = Compte()

    def test_crediter_un_compte_affecte_son_solde(self) :
        self.compte.crediter(50.0)
        self.assertEqual(self.compte.solde(), 50.0)

    def test_on_ne_peut_pas_crediter_un_montant_negatif(self) :
        # une maniere de faire
        # self.assertRaises(CreditNegatifError,
        #                  self.compte.crediter,
        #                  -20.0)
                          
        try :
            self.compte.crediter(-20.0)
            self.fail("crediter doit lever une exception CreditNegatifError")
        except CreditNegatifError :
            pass

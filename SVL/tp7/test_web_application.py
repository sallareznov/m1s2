from selenium import webdriver
import unittest

class TestApplicationPoidsIdeal(unittest.TestCase):

    @classmethod
    def setUpClass(cls):
        cls.navigateur = webdriver.Firefox()
        cls.navigateur.get('http://localhost:8080')

    @classmethod
    def tearDownClass(cls):
        cls.navigateur.quit()

    def test_titre_page_accueil(self):
        titre = self.navigateur.title
        self.assertEqual(titre, "Votre poids ideal !")

    def test_entrer_une_taille(self):
        boite = self.navigateur.find_element_by_id("saisie_taille")
        boite.send_keys("175\n")
        header = self.navigateur.find_element_by_id("poids_ideal")
        poids = header.text
        self.assertEqual(poids, "68.75Kg")

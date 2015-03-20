# SVL 1415 - M. Nebut

from selenium import webdriver
import unittest

class TestAccueil(unittest.TestCase):

    @classmethod
    def setUpClass(cls):
        cls.navigateur = webdriver.Firefox()
        cls.navigateur.get('http://localhost:8080')

    @classmethod
    def tearDownClass(cls):
        cls.navigateur.quit()

    def test_titre_page_accueil(self):
        titre = self.navigateur.title
        self.assertEqual(titre, "Accueil")
        
    def test_texte_lien_vers_temperature(self):
        lien = self.navigateur.find_element_by_id("id_lien_temperature")
        texte = lien.text
        self.assertEqual(texte, "Conversion de temperatures")

    def test_url_lien_vers_temperature(self):
        lien = self.navigateur.find_element_by_id("id_lien_temperature")
        url = lien.get_attribute("href")
        self.assertEqual(url, "http://localhost:8080/temperature")

class TestTemperature(unittest.TestCase):

    def test_presence_boite_saisir_temperature(self):
        navigateur = webdriver.Firefox()
        navigateur.get('http://localhost:8080/temperature')
        boite = navigateur.find_element_by_id("id_boite_saisie_temperature")
        nom = boite.get_attribute("name")
        self.assertEqual(nom, "temperature_celsius")
        navigateur.quit()

class TestConversionCelsiusToFahrenheit(unittest.TestCase):

    def test_convertir_une_valeur_affiche_une_temperature_en_farhenheit(self):
        navigateur = webdriver.Firefox()
        navigateur.get('http://localhost:8080/temperature')
        boite = navigateur.find_element_by_id("id_boite_saisie_temperature")
        boite.send_keys("10\n")
        resultat = navigateur.find_element_by_id("id_resultat_c_to_f")
        self.assertEqual(resultat.text, "50F")
        navigateur.quit()
        
    def test_convertir_un_format_incorrect_affiche_un_message_erreur(self):
        pass

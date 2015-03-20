# SVL 1415 - M. NEBUT
# TODO
# - template html
# - refactorer page_resultat

PAGE_ACCUEIL = """
<html>
<head>
<title>Accueil</title>
</head>
<body>
<a id="id_lien_temperature" href="/temperature">Conversion de temperatures</a>
</body>
</html>
"""

PAGE_TEMPERATURE = """
<html>
<body>
<form action="/temperature">
<input id="id_boite_saisie_temperature" name="temperature_celsius"></input>
</form>
</body>
</html>
"""

import cherrypy
import calcul_temperature

class MonAppli:

    @cherrypy.expose
    def index(self):
        return PAGE_ACCUEIL 

    @cherrypy.expose
    def temperature(self, temperature_celsius=None):
        if not temperature_celsius:
            return PAGE_TEMPERATURE
        return self.page_resultat(temperature_celsius)

    def page_resultat(self, temperature_celsius):
        page = """
<html>
<body>
<p id="id_resultat_c_to_f">
"""
        res = calcul_temperature.celsius_to_farhenheit(int(temperature_celsius))
        page += str(res)
        page += """F</p>
</body>
</html>
"""
        return page
    
cherrypy.quickstart(MonAppli())

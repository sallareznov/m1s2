PAGE_ACCUEIL = """
<html>
<head>
<title>Votre poids ideal !</title>
</head>
<body>
<form>
<input id="saisie_taille" name="taille"></input>
</form>
</body>
</html>
"""

import cherrypy
import calcul_poids_ideal

class WebApplication:

    @cherrypy.expose
    def index(self, taille=None):
        if not taille:
            return PAGE_ACCUEIL
        return self.page_resultat(taille)

    def page_resultat(self, taille):
        page = """
<html>
<head>
<title>Votre poids ideal !</title>
</head>
<body>
<h1 id="poids_ideal">"""
        res = calcul_poids_ideal.calcul_poids_ideal_pour_un_homme(int(taille))
        page += str(res)
        page += """Kg</h1>
</body>
</html>
"""
        return page
    
cherrypy.quickstart(WebApplication())

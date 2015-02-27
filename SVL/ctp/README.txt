**********************************************
***** CTP SVL ********
**** Salla DIAGNE ****
**********************************************

Q1.1) Voir fichiers TestShutTheBox.py et shut_the_box.py

Q1.2) Lors du test de la boîte, nous pouvons nous limiter à un nombre restreint de clapets.
Nous n'avons pas besoin d'initialiser 9 clapets pour tester l'ouverture et la fermeture.
2 clapets suffisent largement.

Q1.3) Voir fichiers TestShutTheBox.py et shut_the_box.py

Rapport de couverture fourni par coverage

diagne@a12p13:~/workspace/m1s2/SVL/ctp$ nosetests -v --with-doctest --with-coverage --cover-package=shut_the_box --cover-html --cover-branches
Doctest: shut_the_box ... ok
test_fermer_le_dernier_clapet_ouvert_renvoie_une_exception (TestShutTheBox.TestBoite) ... ok
test_fermer_un_clapet_de_la_boite (TestShutTheBox.TestBoite) ... ok
test_ouvrir_la_boite_ouvre_tous_ses_clapets (TestShutTheBox.TestBoite) ... ok
test_fermer_un_clapet (TestShutTheBox.TestClapet) ... ok
test_ouvrir_un_clapet (TestShutTheBox.TestClapet) ... ok

Name           Stmts   Miss Branch BrPart  Cover   Missing
----------------------------------------------------------
shut_the_box      27      0      8      0   100%   
----------------------------------------------------------------------
Ran 6 tests in 0.056s

OK

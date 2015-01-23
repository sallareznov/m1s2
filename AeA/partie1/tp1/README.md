# RECHERCHE DE MOTIFS RÉPÉTÉS DANS UN GÉNOME

####*Auteurs* : ####
* Leo PERARD
* Salla DIAGNE

# Listing des fichiers et répertoires du projet
* *donnees/* : contient des fichiers de tests au format FASTA
* *src/* : contient les fichiers JAVA du projet
* *test/* : contient les tests unitaires concernant le projet
* *strand_searching.jar* : jar contenant le programme principal

# Fonctionnement du programme
```
USAGE : java -jar strand_searching.jar filename [strand|N] [-comp|-rev|-revComp]* [-bf|-so|-kr|-kmp|-bm]*
	filename : le nom du fichier fasta ou se trouve le genome a etudier
	[strand|N] : permet de rechercher soit :
		* strand : une sequence dont les occurences seront recherchees dans le genome
		* N : rechercher les occurences des mots de taille N
	[-comp|-rev|-revComp] : permettent de rechercher egalement pour le mot entre ou les occurences des mots de taille N :
		* comp : le complementaire
		* rev : le reverse
		* revComp : le reverse-complementaire
	[-bf|-so|-kr|-kmp|-bm] : permet de spécifier le ou les algos a rechercher parmi :
		* bf : Brute-force
		* so : Shift-Or
		* kr : Karp-Rabin
		* kmp : Knutt-Morris-Pratt
		* bm : Boyer-Moore
EXEMPLE : java -jar strand_searching.jar donnees/simple.fasta TATA -revComp -comp -rev -kr -bf -so -bm -kmp
```

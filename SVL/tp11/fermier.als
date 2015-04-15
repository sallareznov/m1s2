module fermier
open util/ordering[Etat]

/** Modèle */

abstract sig Personnage{
	mange : lone Personnage
}
one sig Fermier extends Personnage{}
one sig Loup extends Personnage{}
one sig Chou extends Personnage{}
one sig Chevre extends Personnage{}
sig Etat {
	cote_gauche : set Personnage,
	cote_droit : set Personnage
}

/** Invariants */

fact qui_mange_qui {
	Fermier.mange = none
	Loup.mange = Chevre
	Chevre.mange = Chou
	Chou.mange = none
}

fact personne_ne_mange_personne {
	not (Fermier not in Etat.cote_gauche and Loup + Chevre in Etat.cote_gauche)
	not (Fermier not in Etat.cote_droit and Loup + Chevre in Etat.cote_droit)
	not (Fermier not in Etat.cote_gauche and Chevre + Chou in Etat.cote_gauche)
	not (Fermier not in Etat.cote_droit and Chevre + Chou in Etat.cote_droit)
}

/** Fonctions */

fun liste_personnages_manges(ensemble_personnages : set Personnage) : set Personnage {
	Fermier in ensemble_personnages implies none else ensemble_personnages.mange & ensemble_personnages
}

/** Prédicats */

pred etat_initial(etat : Etat) {
	some cote_gauche
	no cote_droit
}

pred etat_final(etat : Etat) {
	no cote_gauche
	some cote_droit
}

pred passage_fermier_seulement_de_gauche_a_droite(etat_initial : Etat, etat_final : Etat) {
	Fermier in etat_initial.cote_gauche
	etat_final.cote_droit = etat_initial.cote_droit  + Fermier
	#liste_personnages_manges[etat_final.cote_droit] = 0
}

pred passage_fermier_seulement_de_droite_a_gauche(etat_initial : Etat, etat_final : Etat) {
	Fermier in etat_initial.cote_droit
	etat_final.cote_gauche = etat_initial.cote_gauche + Fermier
	#liste_personnages_manges[etat_final.cote_gauche] = 0
}

//pred passage_fermier_et_un_objet_de_gauche_a_droite(etat_initial : Etat, etat_final : Etat, objet : Objet) {
//	Fermier in etat_initial.cote_gauche
	//etat_final.cote_droit = etat_initial.cote_droit + Fermier + objet
//}

//pred passage_fermier_et_un_objet_de_droite_a_gauche(etat_initial : Etat, etat_final : Etat, objet : Objet) {
	//Fermier in etat_initial.cote_droit
	//etat_final.cote_gauche = etat_initial.cote_gauche + Fermier + objet
//}

pred passage(etat_initial : Etat, etat_final : Etat) {
	passage_fermier_seulement_de_gauche_a_droite[etat_initial, etat_final] or passage_fermier_seulement_de_droite_a_gauche[etat_final, etat_initial]
}

/** Assertions */

assert bon_nombre_de_personnages_manges {
	#liste_personnages_manges[Fermier + Loup + Chevre + Chou] = 0
	#liste_personnages_manges[Loup + Chevre + Chou] = 2
	#liste_personnages_manges[Loup + Chevre] = 1
	#liste_personnages_manges[Loup + Chou] = 0
	#liste_personnages_manges[Chevre + Chou] = 1
}

/** Commandes */

run etat_initial for 3
run etat_final for 3
run passage_fermier_seulement_de_gauche_a_droite for 3
run passage_fermier_seulement_de_droite_a_gauche for 3
check bon_nombre_de_personnages_manges for 1
	
	

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
	all e : Etat | not (Fermier in e.cote_gauche and Fermier in e.cote_droit)
	and not (Loup in e.cote_gauche and Loup in e.cote_droit)
	and not (Chevre in e.cote_gauche and Chevre in e.cote_droit)
	and not (Chou in e.cote_gauche and Chou in e.cote_droit)
	and not (Fermier not in e.cote_gauche and Loup + Chevre in e.cote_gauche)
	and not (Fermier not in e.cote_droit and Loup + Chevre in e.cote_droit)
	and not (Fermier not in e.cote_gauche and Chevre + Chou in e.cote_gauche)
	and not (Fermier not in e.cote_droit and Chevre + Chou in e.cote_droit)
}

fact chaque_personnage_est_sur_un_cote {
	all p : Personnage, e : Etat | p in e.cote_gauche or p in e.cote_droit
}

fact next_etat {
	all e1, e2, e3 : Etat | e2 = next[e1] and e3 = next[e2] implies passage[e1, e2] and passage[e2, e3]
}

/** Fonctions */

fun liste_personnages_manges(ensemble_personnages : set Personnage) : set Personnage {
	Fermier in ensemble_personnages implies none else ensemble_personnages.mange & ensemble_personnages
}

/** Prédicats */

pred etat_initial(etat : Etat) {
	some etat.cote_gauche
	no etat.cote_droit
}

pred etat_final(etat : Etat) {
	no cote_gauche
	some cote_droit
}

pred init(etat : Etat) {
	etat_initial[etat]
}

pred passage_fermier_seul_de_gauche_a_droite(etat_initial : Etat, etat_final : Etat) {
	etat_initial != etat_final
	Fermier in etat_initial.cote_gauche
	Fermier not in etat_initial.cote_droit
	Fermier not in etat_final.cote_gauche
	Fermier in etat_final.cote_droit
	etat_final.cote_droit = etat_initial.cote_droit  + Fermier
	etat_final.cote_gauche = etat_initial.cote_gauche - Fermier
	#liste_personnages_manges[etat_initial.cote_gauche] = 0
	#liste_personnages_manges[etat_initial.cote_droit] = 0
	#liste_personnages_manges[etat_final.cote_gauche] = 0
	#liste_personnages_manges[etat_final.cote_droit] = 0
}

pred passage_fermier_seul_de_droite_a_gauche(etat_initial : Etat, etat_final : Etat) {
	etat_initial != etat_final
	Fermier in etat_initial.cote_droit
	Fermier not in etat_initial.cote_gauche
	Fermier not in etat_final.cote_droit
	Fermier in etat_final.cote_gauche
	etat_final.cote_gauche = etat_initial.cote_gauche + Fermier
	etat_final.cote_droit = etat_initial.cote_droit - Fermier
	#liste_personnages_manges[etat_initial.cote_droit] = 0
	#liste_personnages_manges[etat_initial.cote_gauche] = 0
	#liste_personnages_manges[etat_final.cote_droit] = 0
	#liste_personnages_manges[etat_final.cote_gauche] = 0
}

// problem
pred passage_fermier_accompagne_de_gauche_a_droite(etat_initial : Etat, etat_final : Etat) {
	etat_initial != etat_final
	Fermier in etat_initial.cote_gauche
	Fermier not in etat_initial.cote_droit
	Fermier not in etat_final.cote_gauche
	Fermier in etat_final.cote_droit
	#(etat_final.cote_droit) - #(etat_initial.cote_droit) = 2
	#(etat_initial.cote_gauche) - #(etat_final.cote_gauche) = 2
	#liste_personnages_manges[etat_final.cote_droit] = 0
	#liste_personnages_manges[etat_final.cote_gauche] = 0
}

pred passage_fermier_accompagne_de_droite_a_gauche(etat_initial : Etat, etat_final : Etat) {
	etat_initial != etat_final
	Fermier in etat_initial.cote_droit
	Fermier not in etat_initial.cote_gauche
	Fermier not in etat_final.cote_droit
	Fermier in etat_final.cote_gauche
	#(etat_final.cote_gauche) - #(etat_initial.cote_gauche) = 2
	#(etat_initial.cote_droit) - #(etat_final.cote_droit) = 2
	#liste_personnages_manges[etat_final.cote_gauche] = 0
	#liste_personnages_manges[etat_final.cote_droit] = 0
}

pred passage(etat_initial : Etat, etat_final : Etat) {
	passage_fermier_seul_de_gauche_a_droite[etat_initial, etat_final]
	or passage_fermier_seul_de_droite_a_gauche[etat_final, etat_initial]
	or passage_fermier_accompagne_de_gauche_a_droite[etat_initial, etat_final]
	or passage_fermier_accompagne_de_droite_a_gauche[etat_initial, etat_final]
}

pred main() {}

/** Assertions */

assert bon_nombre_de_personnages_manges {
	#liste_personnages_manges[Fermier + Loup + Chevre + Chou] = 0
	#liste_personnages_manges[Loup + Chevre + Chou] = 2
	#liste_personnages_manges[Loup + Chevre] = 1
	#liste_personnages_manges[Loup + Chou] = 0
	#liste_personnages_manges[Chevre + Chou] = 1
}

/** Commandes */

run etat_initial for 2
run etat_final for 2
run passage_fermier_seul_de_gauche_a_droite for 2
run passage_fermier_seul_de_droite_a_gauche for 2
run passage_fermier_accompagne_de_gauche_a_droite for 2
check bon_nombre_de_personnages_manges for 1
run main for 8
	
	

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
	and liste_personnages_manges[e.cote_gauche] = none
	and liste_personnages_manges[e.cote_droit] = none
}

fact chaque_personnage_est_sur_un_cote {
	all p : Personnage, e : Etat | p in e.cote_gauche or p in e.cote_droit
}

fact paires_etats_successifs {
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
	no etat.cote_gauche
	some etat.cote_droit
}

pred passage_fermier_seul_de_gauche_a_droite(etat_initial : Etat, etat_final : Etat) {
	etat_initial != etat_final
	Fermier in etat_initial.cote_gauche
	Fermier not in etat_initial.cote_droit
	Fermier not in etat_final.cote_gauche
	Fermier in etat_final.cote_droit
	etat_final.cote_droit = etat_initial.cote_droit  + Fermier
	etat_final.cote_gauche = etat_initial.cote_gauche - Fermier
}

pred passage_fermier_seul_de_droite_a_gauche(etat_initial : Etat, etat_final : Etat) {
	etat_initial != etat_final
	Fermier in etat_initial.cote_droit
	Fermier not in etat_initial.cote_gauche
	Fermier not in etat_final.cote_droit
	Fermier in etat_final.cote_gauche
	etat_final.cote_gauche = etat_initial.cote_gauche + Fermier
	etat_final.cote_droit = etat_initial.cote_droit - Fermier
}

pred passage_fermier_accompagne_de_gauche_a_droite(etat_initial : Etat, etat_final : Etat) {
	etat_initial != etat_final
	Fermier in etat_initial.cote_gauche
	Fermier not in etat_initial.cote_droit
	Fermier not in etat_final.cote_gauche
	Fermier in etat_final.cote_droit
	(#(etat_final.cote_droit)).sub[#(etat_initial.cote_droit)] = 2
	(#(etat_initial.cote_gauche)).sub[#(etat_final.cote_gauche)] = 2
	#(etat_final.cote_droit - etat_initial.cote_droit - Fermier) = 1
	#(etat_initial.cote_gauche - etat_final.cote_gauche - Fermier) = 1
}

pred passage_fermier_accompagne_de_droite_a_gauche(etat_initial : Etat, etat_final : Etat) {
	etat_initial != etat_final
	Fermier in etat_initial.cote_droit
	Fermier not in etat_initial.cote_gauche
	Fermier not in etat_final.cote_droit
	Fermier in etat_final.cote_gauche
	(#(etat_final.cote_gauche)).sub[#(etat_initial.cote_gauche)] = 2
	(#(etat_initial.cote_droit)).sub[#(etat_final.cote_droit)] = 2
	#(etat_final.cote_gauche - etat_initial.cote_gauche - Fermier) = 1
	#(etat_initial.cote_droit - etat_final.cote_droit - Fermier) = 1
}

pred passage(etat_initial : Etat, etat_final : Etat) {
	passage_fermier_seul_de_gauche_a_droite[etat_initial, etat_final]
	or passage_fermier_seul_de_droite_a_gauche[etat_initial, etat_final]
	or passage_fermier_accompagne_de_gauche_a_droite[etat_initial, etat_final]
	or passage_fermier_accompagne_de_droite_a_gauche[etat_initial, etat_final]
}

pred main() {
	etat_initial[first]
	etat_final[last]
}

/** Assertions */

assert bon_personnages_manges {
	liste_personnages_manges[Fermier + Loup + Chevre + Chou] = none
	liste_personnages_manges[Loup + Chevre + Chou] = Chevre + Chou
	liste_personnages_manges[Loup + Chevre] = Chevre
	liste_personnages_manges[Loup + Chou] = none
	liste_personnages_manges[Chevre + Chou] = Chou
}

/** Commandes */

run etat_initial for 1
run etat_final for 1
run passage_fermier_seul_de_gauche_a_droite for 2
run passage_fermier_seul_de_droite_a_gauche for 2
run passage_fermier_accompagne_de_gauche_a_droite for 2
run passage_fermier_accompagne_de_droite_a_gauche for 2
check bon_personnages_manges for 1
run main for 3
run main for 4
run main for 5
run main for 6
run main for 7
run main for 8

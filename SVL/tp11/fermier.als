module fermier
open util/ordering[Etat]

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

fact qui_mange_qui {
	Fermier.mange = none
	Loup.mange = Chevre
	Chevre.mange = Chou
	Chou.mange = none
}

//fact etats {
	//no (Loup and Chevre not in Etat.cote_gauche and Chevre in Etat.cote_gauche)
//}

fun personnages_manges(personnages : set Personnage) : set Personnage {
	Fermier in personnages implies none else personnages.mange
}

pred etat_inital(etat : Etat) {
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
	#personnages_manges[etat_final.cote_gauche] = 0
}

pred passage_fermier_seulement_de_droite_a_gauche(etat_initial : Etat, etat_final : Etat) {
	Fermier in etat_initial.cote_droit
	etat_final.cote_gauche = etat_initial.cote_gauche + Fermier
	#personnages_manges[etat_final.cote_gauche] = 0
}

pred test(persos : set Personnage) {
	#personnages_manges[persos] = 1
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

run test for 1

//run generer_instances for 4 but 1 Carnet
	
	

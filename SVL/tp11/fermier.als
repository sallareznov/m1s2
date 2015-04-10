module fermier
open util/ordering[Etat]

abstract sig Objet{
	mange : lone Objet
}
one sig Fermier extends Objet{}
one sig Loup extends Objet{}
one sig Chou extends Objet{}
one sig Chevre extends Objet{}
sig Etat {
	cote_gauche : set Objet,
	cote_droit : set Objet
}

fact qui_mange_qui {
	Loup.mange = Chevre
	Chevre.mange = Chou
}


pred passage_fermier_seulement_de_gauche_a_droite(etat_initial : Etat, etat_final : Etat) {
	Fermier in etat_initial.cote_gauche
	etat_final.cote_droit = etat_initial.cote_droit  + Fermier
}

pred passage_fermier_seulement_de_droite_a_gauche(etat_initial : Etat, etat_final : Etat) {
	Fermier in etat_initial.cote_droit
	etat_final.cote_gauche = etat_initial.cote_gauche + Fermier
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

run passage for 3

//run generer_instances for 4 but 1 Carnet
	
	

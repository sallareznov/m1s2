// TP10
// Anis TELLO
// Salla DIAGNE

module carnet_simple

sig Identifiant{}
sig Surnom {}
sig Adresse {}
sig Carnet {
   id : Identifiant,
   nommage : Surnom -> lone Adresse // 0 ou 1
}

/******** operations *******/

pred ajout(c : Carnet, c' : Carnet, s : Surnom, a : Adresse) {
   c'.id = c.id
   c'.nommage = c.nommage + s -> a // union +
}

pred suppression_surnom(c : Carnet, c' : Carnet, s : Surnom) {
   c'.id = c.id
   c'.nommage = c.nommage - s -> Adresse // difference -
   // c'.nommage = c.nommage - { s0 : Surnom, a : Adresse | s0 = s } // idem
}

pred remplace_adresse(c : Carnet, c' : Carnet, s : Surnom, a : Adresse) {
	suppression_surnom[c, c', s]
	ajout[c, c', s, a]
}

/******** predicats *******/

pred genererInstances(c : Carnet){
}

pred genererAjout(c1 : Carnet, c2 : Carnet, s : Surnom, a : Adresse) {
    ajout[c1, c2, s, a] and c1 != c2
}

pred suppr_ajout(c1 : Carnet, c2 : Carnet, c3 : Carnet, s : Surnom, a : Adresse) {
  ajout[c1, c2, s, a] and suppression_surnom[c2, c3, s]
}

/******** assertions ********/

// fausse car pas de précond
assert pas_de_surnom_pointant_differentes_adresses {
   all c : Carnet | no s : Surnom | #s.(c.nommage) > 1
}

assert suppression_modifie_carnet {
	all c1, c2 : Carnet, s : Surnom | suppression_surnom[c1, c2, s] implies no s.(c2.nommage)
}

assert suppression_annule_ajout {
	all c1, c2, c3 : Carnet, s : Surnom, a : Adresse | no s.(c1.nommage) and no s.(c2.nommage) and no s.(c3.nommage) and ajout[c1, c2, s, a] and suppression_surnom[c2, c3, s] implies c1.nommage = c3.nommage
}

assert ajout_idempotent {
	all c1, c2, c3 : Carnet, s : Surnom, a : Adresse | ajout[c1, c2, s, a] and ajout[c2, c3, s, a] implies c2.nommage = c3.nommage
}

assert remplacer_naltere_pas_le_surnom {
	all c1, c2 : Carnet, s : Surnom, a1, a2 : Adresse | ajout[c1, c2, s, a1] and remplace_adresse[c1, c2, s, a2] implies s.(c1.nommage) = s.(c2.nommage) and (c2.nommage).a1 != (c1.nommage).a2 
}

/******* commandes **********/

run genererInstances for 3 but 1 Carnet
run genererAjout for 2
run suppr_ajout
check pas_de_surnom_pointant_differentes_adresses for 3
check suppression_modifie_carnet for 2
check suppression_annule_ajout for 3
check remplacer_naltere_pas_le_surnom for 2

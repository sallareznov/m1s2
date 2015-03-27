!create livret : Livret
!set livret.solde := 150

!create compte : Compte
!set compte.solde := 300

!create personne : Personne
!set personne.revenu := 15

!create conseiller : Personne
!set conseiller.revenu := 15

!insert (personne, conseiller) into Conseil
!insert (conseiller, personne) into Conseil
!insert (compte, personne) into GestionCompte
!insert (compte, personne) into ProprieteCompte
!insert (compte, livret) into CompteLivret
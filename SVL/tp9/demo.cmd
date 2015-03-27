!create compte : Compte
!set compte.montant := 150.0

!create compteInvalide : Compte
!set compteInvalide.montant := -10.0

!openter compte debiter(10.0)
!set compte.montant := 140.0
!opexit
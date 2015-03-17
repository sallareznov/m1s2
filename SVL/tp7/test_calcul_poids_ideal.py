from calcul_poids_ideal import *
from nose.tools import assert_equal

def test_calcul_poids_ideal_pour_un_homme():
    assert_equal(calcul_poids_ideal_pour_un_homme(175), 68.75)
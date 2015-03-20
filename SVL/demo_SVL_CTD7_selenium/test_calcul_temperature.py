from calcul_temperature import *
from nose.tools import assert_equal

def test_celsius_to_fahrenheit():
    assert_equal(celsius_to_farhenheit(10), 50)
    

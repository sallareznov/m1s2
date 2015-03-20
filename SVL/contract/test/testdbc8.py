#!/usr/bin/env python

"""
Test class-private variables.

>>> f = Foo()
>>> f.__attr
Traceback (most recent call last):
...
AttributeError: Foo instance has no attribute '__attr'
"""
class Foo:
    """inv: self._Foo__attr > 0"""
    def __init__(self):
	self.__attr = 1

def _test():
    import contract, doctest, testdbc8
    contract.checkmod(testdbc8)
    return doctest.testmod(testdbc8)

if __name__ == '__main__':
    t = _test()
    if t[0]:
	print "test: %d/%d failures" % t
    else:
	print "test: %d successes" % t[1]

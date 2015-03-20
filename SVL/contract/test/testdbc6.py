#!/usr/bin/env python
"""Thanks Dickon Reed... beta 1 broke with itpl

Example::
>>> c = C()
>>> print c.foo()
2 2

This should work, and not bang out with an exception
"""
from itpl import itpl
import contract

class C:
    def __init__(self, x=2):
        self.x = x
    def foo(self):
        return itpl('$self.x $self.x')

def _test():
    import contract, doctest, testdbc6
    contract.checkmod(testdbc6)
    return doctest.testmod(testdbc6)

if __name__ == '__main__':
    t = _test()
    if t[0] == 0:
        print "test: %d tests succeeded" % t[1]
    else:
        print "test: %d/%d tests failed" % t

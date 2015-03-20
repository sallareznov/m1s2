#!/usr/bin/env python
"""

Tests tightening preconditions... which should fail with an
InvalidPreconditionError

>>> b = Base()
>>> b.method(10)
90

>>> d = Derived()
>>> d.method(20)
200

>>> d.method(10)
Traceback (most recent call last):
 ...
PreconditionViolationError: ('testdbc9d.Derived.method', 6)
"""

from testdbc9b import Base

class Derived(Base):
    def method(self, a):
        """We cannot tighten preconditions, we must only be able to loosen
        them.  Otherwise, abstract classes cannot work.  The client thinks
        they're calling with a particular contract and Surprise!  it
        doesn't work now.

        pre: a > 10
        """
        return a * 10

def _test():
    import contract, doctest, testdbc9d
    contract.checkmod(testdbc9d, contract.CHECK_ALL)
    return doctest.testmod(testdbc9d)

if __name__ == '__main__':
    t = _test()
    if t[0]:
        print "test: %d/%d failures" % t
    else:
        print "test: %d successes" % t[1]

b = Base()
b.method(10)
b.method(6)

d = Derived()
d.method(20)
d.method(10)
 

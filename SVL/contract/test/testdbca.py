#!/usr/bin/env python
"""
Tests undefined preconditions on superclasses.

>>> b = B()
>>> b.method(4)
4
>>> b.method(6)
Traceback (most recent call last):
...
PreconditionViolationError: ('testdbca.B.method', 3)

>>> d1 = D1()
>>> d1.method(4)
8
>>> d1.method(6)
Traceback (most recent call last):
...
PreconditionViolationError: ('testdbca.B.method', 3)

>>> d2 = D2()
>>> d2.method(4)
12
>>> d2.method(6)
18
"""

G = 5

class B:
    def method(self, a):
        """Contracts

        pre: a < 5
        post: a <= G
	"""
	global G
	G = a
        return a

class D1(B):
    def method(self, a):
        """
	pre:
		a < 6
		B.method.__assert_pre(self, a)
	"""
	global G

	G = a * 2
        return a * 2

class D2(B):
    def method(self, a):
	global G
	G = a * 3
        return a * 3

def _test():
    import contract, doctest, testdbca
    contract.checkmod(testdbca)
    return doctest.testmod(testdbca)

if __name__ == '__main__':
    t = _test()
    if t[0] == 0:
	print "test: %d tests succeeded" % t[1]
    else:
	print "test: %d/%d tests failed" % t


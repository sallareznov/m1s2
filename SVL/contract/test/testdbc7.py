#!/usr/bin/env python
"""
>>> c = Checked()
>>> c.method()
Traceback (most recent call last):
...
PostconditionViolationError: ('testdbc7.ToughBase.method', 3)

>>> u = Unchecked()
>>> u.method()
Traceback (most recent call last):
...
PostconditionViolationError: ('testdbc7.ToughBase.method', 3)
"""

class ToughBase:
    def method(self):
	"""
	pre: False
	post: False
	"""
	pass

class Checked(ToughBase):
    def method(self):
	"""pre: True
	post: True
	"""
	return 1

class Unchecked(ToughBase):
    def method(self):
	return 1

def _test():
    import contract, doctest, testdbc7
    contract.checkmod(testdbc7)
    return doctest.testmod(testdbc7)

if __name__ == '__main__':
    t = _test()
    if t[0] == 0:
	print "test: %d tests succeeded" % t[1]
    else:
	print "test: %d/%d tests failed" % t

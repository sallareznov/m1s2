#!/usr/bin/env python
"""Test inner/outer classes

>>> outer.a
5
>>> o = outer()
>>> o.a, o.b
(5, 6)
>>> o.foo()
5 5 6
"""

class outer:
    a = 5

    def __init__(self):
        self.b = 6

    def foo(self):
        print outer.a, self.a, self.b

    class inner:
        """test inner classes.

        inv: self.c == 7
        """
        c = 7
        def __init__(self):
            self.d = 8

        def foo(self):
            print outer.a, self.c, self.d

def _test():
    import contract, doctest, testdbc2
    contract.checkmod(testdbc2)
    return doctest.testmod(testdbc2)

if __name__ == '__main__':
    t = _test()
    if t[0] == 0:
       print "test: %d tests succeeded" % t[1]
    else:
       print "test: %d/%d tests failed" % t

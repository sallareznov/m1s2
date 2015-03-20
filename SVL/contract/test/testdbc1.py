#!/usr/bin/env python
"""Test namespace resolution.

>>> a
5
>>> base.b
6
>>> b0 = base()
>>> b0.foo()
5 6 7 Hello!
>>> d0 = derived()
>>> d0.foo()
5 6 8 7 Guten tag!

# to test ':' vs '::' we alternate
inv::
    a == 5
    base.b == 6
    derived.b == 8

>>> t1()    # public functions can break the invariant
Traceback (most recent call last):
 ...
InvariantViolationError: ('testdbc1', 16)
>>> _fix()  # repair
>>> _t1()   # private functions don't test invariant
>>> _fix()
>>> t2(5)
Traceback (most recent call last):
 ...
PreconditionViolationError: ('testdbc1.t2', 3)
>>> t2(4)
Traceback (most recent call last):
 ...
InvariantViolationError: ('testdbc1', 16)
>>> _fix()

>>> base.dingle(2, 4)
8
>>> base.dingle(-1, 2)
Traceback (most recent call last):
 ...
PreconditionViolationError: ('testdbc1.base.dingle', 3)
"""
a = 5

# examples from the PEP
START, CONNECTING, CONNECTED, CLOSING, CLOSED = range(5)

class conn:
    """A network connection

    inv: self.state in [START, CLOSED,       # closed states
                        CONNECTING, CLOSING, # transition states
                        CONNECTED]

    inv:: 0 <= self.seqno < 256
    """
    def __init__(self):
        """Create a connection

        post: self.state == START and self.seqno == 0
        """
        self.state = START
        self.seqno = 0

    def send(self, msg):
        """Send a message.

        pre:: self.state == CONNECTED
        post[self.seqno]: self.seqno == (__old__.self.seqno + 1) % 256
        """
        self.seqno = (self.seqno + 1) % 256

class base:
    """Test base class.

    inv::
        base.b == 6
        self.c == 7
    """
    b = 6

    def __init__(self):
        """Create a base object.

        post: self.c == 7
        """
        self.c = 7

    def foo(self):
        """Foo.

        pre:: a == 5 and base.b == 6 and self.c == 7
        """
        print a, base.b, self.c, "Hello!"

    def impfoo(self):
        self.foo()

    def expfoo(self):
        base.foo(self)

    def dingle(x, y):
        """Test static method.

        pre: x > 0 and y > 0
        post:: __return__ == x * y
        """
        return x * y

    dingle = staticmethod(dingle)


class derived(base):
    b = 8

    def foo(self):
        print a, base.b, derived.b, self.c, "Guten tag!"

# even functions without docstrings get invariant checking
#
def t1():
    global a
    a = 4

# but not private functions
def _t1():
    global a
    a = 4

def _fix():
    global a
    a = 5

def t2(arg):
    """test: should fail

    pre:: arg != 5
    post[a]: a == arg
    """
    global a
    a = arg

def defargs(a, (b, c, d), e = 5, f = 6, *va, **ka):
    """Test default arguments, keyword arguments, etc.

    pre::
        e >= 5
        f <= 6
        type(va) == type( () )
        type(ka) == type({})

    >>> defargs(1, (2, 3, 4))
    >>> defargs(3, (4, 5, 6), 7, 4)
    >>> defargs('', ('', '', ''), 4)
    Traceback (most recent call last):
        ...
    PreconditionViolationError: ('testdbc1.defargs', 4)

    >>> defargs([], ('42', {}, None), 5, 7)
    Traceback (most recent call last):
        ...
    PreconditionViolationError: ('testdbc1.defargs', 5)

    >>> defargs(1, (2, 3, 4), foo='bar')
    """
    pass

def defargs2(a, fn = int):
    """Test pre-evaluated default arguments.

    pre: fn(a) or not fn(a) # just see if function works
    post:: __return__ == 0 or __return__ == 1

    >>> defargs2('42')
    0
    >>> defargs2('0')
    1
    """
    return not fn(a)

def _test():
    import contract, doctest, testdbc1
    contract.checkmod(testdbc1, contract.CHECK_ALL)
    return doctest.testmod(testdbc1)

if __name__ == '__main__':
    t = _test()
    if t[0]:
        print "test: %d/%d failures" % t
    else:
        print "test: %d successes" % t[1]

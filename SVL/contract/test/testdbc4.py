#!/usr/bin/env python
"""Basic tests for contracts.

This tests CHECK_PRECONDITIONS functionality.  See testdbc3 (which should be an
exact copy of this module) for CHECK_ALL functionality.

inv: a % 2 == 0 # a is even
"""

a = 4

def _fix():
    global a
    a = 2

def _break():
    global a
    a = 3

try:
    hasobject = not not object
except NameError:
    hasobject = 0

# 1.  modinv01 Module invariants are tested on entry and exit to
#              public functions.
#
def modinv01(b):
    """contract test modinv01

    >>> modinv01(2)
    >>> modinv01(3)
    >>> modinv01(3)
    Traceback (most recent call last):
       ...
    InvariantViolationError: ('testdbc4', 6)
    >>> _fix()
    >>> modinv01(4)
    >>> _break()
    >>> modinv01(6)
    Traceback (most recent call last):
       ...
    InvariantViolationError: ('testdbc4', 6)
    >>> _fix()
    """
    global a
    a = b

# 2.  modinv02 Module invariants aren't tested on private functions.
#
def _modinv02(b):
    """contract test modinv02

    >>> _modinv02(7)
    >>> _modinv02(9)
    >>> modinv01(4)
    Traceback (most recent call last):
       ...
    InvariantViolationError: ('testdbc4', 6)
    >>> _fix()
    """
    global a
    a = b

# 3.  modpre01 Pre-conditions are tested on entry to all functions,
#              public or private.
#
def modpre01a(a, b):
    """contract test modpre01

    pre: (a + b) % 2 == 0

    >>> modpre01a(2, 8)
    >>> modpre01a(2, 7)
    Traceback (most recent call last):
       ...
    PreconditionViolationError: ('testdbc4.modpre01a', 3)
    """
    pass

def _modpre01b(a, b):
    """contract test modpre01

    pre: (a + b) % 2 == 0

    >>> _modpre01b(2, 8)
    >>> _modpre01b(2, 7)
    Traceback (most recent call last):
       ...
    PreconditionViolationError: ('testdbc4._modpre01b', 3)
    """
    pass

# 4.  modpst01 Post-conditions are tested on exit from all functions,
#              public or private.
#
def modpst01a(a, b):
    """contract test modpst01

    pre: len(b) > 0
    pre: isinstance(b, type([]))
    post: b[0] == a

    >>> l = [0]
    >>> l
    [0]
    >>> modpst01a(5, l)
    >>> modpst01a(-2, l)
    """
    if a > 0:
        b[0] = a

def _modpst01b(a, b):
    """contract test modpst01

    pre: len(b) > 0
    pre: isinstance(b, type([]))
    post: b[0] == a

    >>> l = [0]
    >>> l
    [0]
    >>> _modpst01b(5, l)
    >>> _modpst01b(-2, l)
    """
    if a > 0:
        b[0] = a

class test:
    """contract test class

    inv: self.a == 42
    """

# 5.  clsinv01 Class invariants are tested on entry and exit to almost
#              all public methods.
#
# 6.  clsinv02 Class invariants aren't tested on private methods.
#
    def clsinv01(self, b):
        """contract test clsinv01

        >>> t = test()
        >>> t.clsinv01(42)
        >>> t.a = 41
        >>> t.clsinv01(42)
        Traceback (most recent call last):
           ...
        InvariantViolationError: ('testdbc4.test', 3)
        >>> t.clsinv01(43)
        Traceback (most recent call last):
           ...
        InvariantViolationError: ('testdbc4.test', 3)
        >>> t._clsinv02()
        >>> t._clsinv02(43)
        >>> t._clsinv02(41)
        >>> t.clsinv01(42)
        Traceback (most recent call last):
           ...
        InvariantViolationError: ('testdbc4.test', 3)
        >>> t.a = 42
        """
        self.a = b

    def _clsinv02(self, b = 42):
        self.a = b

# 7.  clsinv03 Class invariants aren't tested on *entry* to __init__.
#
    def __init__(self, b = 42):
         """Test

         >>> t = test()
         >>> t = test(41)
         Traceback (most recent call last):
            ...
         InvariantViolationError: ('testdbc4.test', 3)
         """
         self.a = b

# 8.  clsinv04 Class invariants aren't tested on *exit* from __del__.
#
#    def __del__(self):
#        """contract test clsinv04
#
#        1>>> t = test()
#        1>>> del t
#        1>>> t = test()
#
#        1>>> t.a = 41
#        1>>> del t
#        """
#        #del self.a
#        pass

# 9.  clspre01 Pre-conditions are tested on entry to all methods,
#              public or private.
#
    def clspre01a(self, a):
        """contract test clspre01

        pre: a % 2 == 0

        >>> t = test()
        >>> t.clspre01a(10)
        >>> t.clspre01a(11)
        Traceback (most recent call last):
           ...
        PreconditionViolationError: ('testdbc4.test.clspre01a', 3)
        >>> t._clspre01b(10)
        >>> t._clspre01b(11)
        Traceback (most recent call last):
           ...
        PreconditionViolationError: ('testdbc4.test._clspre01b', 3)
        """
        pass

    def _clspre01b(self, a):
        """contract test clspre01

        pre: a % 2 == 0
        """
        pass

# 10. clspst01 Post-conditions are tested on exit from all methods,
#              public or private.
#
    def clspst01a(self, a, b):
        """contract test clspst01

        pre: len(b) == 1
        post: b[0] == a

        >>> l = [0]
        >>> t = test()
        >>> t.clspst01a(10, l)
        >>> t.clspst01a(11, l)
        >>> t._clspst01b(10, l)
        >>> t._clspst01b(11, l)
        """
        if a % 2 == 0: b[0] = a

    def _clspst01b(self, a, b):
        """contract test clspst01

        pre: len(b) == 1
        post: b[0] == a
        """
        if a % 2 == 0: b[0] = a

# 11. clsinv05 Invariants for a class include invariants on all super-
#              classes.
#
class clsinv05a_base:
    """contract test clsinv05

    inv: self.a == 42
    """
    def __init__(self):
        self.a = 42

class clsinv05a_derived(clsinv05a_base):
    """contract test clsinv05

    inv: self.b == 49

    >>> d = clsinv05a_derived()
    >>> d.oops()
    >>> d.oops()
    Traceback (most recent call last):
       ...
    InvariantViolationError: ('testdbc4.clsinv05a_base', 3)
    """
    def __init__(self):
        clsinv05a_base.__init__(self)
        self.b = 49

    def oops(self):
        self.a = 43

    def _fix(self):
        self.a = 42
        self.b = 49

if hasobject:
    class clsinv05b_base(object):
        """contract test clsinv05

        inv: self.a == 42
        """
        def __init__(self):
            self.a = 42

    class clsinv05b_derived(clsinv05b_base):
        """contract test clsinv05

        inv: self.b == 49

        >>> d = clsinv05b_derived()
        >>> d.oops()
        >>> d.oops()
        Traceback (most recent call last):
           ...
        InvariantViolationError: ('testdbc4.clsinv05b_base', 3)
        """
        def __init__(self):
            clsinv05b_base.__init__(self)
            self.b = 49

        def oops(self):
            self.a = 43

        def _fix(self):
            self.a = 42
            self.b = 49

# 12. clspre02 Pre-conditions can only be weakened by overridden
#              methods.
#
class clspre02a_base:
    def foo(self, a):
        "pre: a % 2 == 0"
        pass

class clspre02a_derived(clspre02a_base):
    """contract test clspre02

    >>> b = clspre02a_base()
    >>> b.foo(2)
    >>> b.foo(4)
    >>> b.foo(5)
    Traceback (most recent call last):
       ...
    PreconditionViolationError: ('testdbc4.clspre02a_base.foo', 1)

    >>> d = clspre02a_derived()
    >>> d.foo(2)
    >>> d.foo(4)
    >>> d.foo(5)
    >>> d.foo(-2)
    Traceback (most recent call last):
       ...
    InvalidPreconditionError: ('testdbc4.clspre02a_derived.foo', 1)
    >>> d.foo(-3)
    Traceback (most recent call last):
       ...
    PreconditionViolationError: ('testdbc4.clspre02a_base.foo', 1)
    """
    def foo(self, a):
        "pre: a > 0"
        pass

if hasobject:
    class clspre02b_base(object):
        def foo(self, a):
            "pre: a % 2 == 0"
            pass

    class clspre02b_derived(clspre02b_base):
        """contract test clspre02

        >>> b = clspre02b_base()
        >>> b.foo(2)
        >>> b.foo(4)
        >>> b.foo(5)
        Traceback (most recent call last):
           ...
        PreconditionViolationError: ('testdbc4.clspre02b_base.foo', 1)

        >>> d = clspre02b_derived()
        >>> d.foo(2)
        >>> d.foo(4)
        >>> d.foo(5)
        >>> d.foo(-2)
        Traceback (most recent call last):
           ...
        InvalidPreconditionError: ('testdbc4.clspre02b_derived.foo', 1)
        >>> d.foo(-3)
        Traceback (most recent call last):
           ...
        PreconditionViolationError: ('testdbc4.clspre02b_base.foo', 1)
        """
        def foo(self, a):
            "pre: a > 0"

# 13. clspst02 Post-conditions can be strengthened by overridden
#              methods.
#
class clspst02a_base:
    def foo(self, a, b):
        """contract test clspst02

        post: __return__ % 2 == 0
        """
        return a * b

class clspst02a_derived(clspst02a_base):
    def foo(self, a, b):
        """contract test clspst02

        post: __return__ % 3 == 0

        >>> b = clspst02a_base()
        >>> b.foo(2, 3)
        6
        >>> b.foo(2, 1)
        2
        >>> b.foo(1, 3)
        3
        >>> d = clspst02a_derived()
        >>> d.foo(2, 3)
        6
        >>> d.foo(2, 1)
        2
        >>> d.foo(1, 3)
        3
        """
        return a * b

if hasobject:
    class clspst02b_base(object):
        def foo(self, a, b):
            """contract test clspst02

            post: __return__ % 2 == 0
            """
            return a * b

    class clspst02b_derived(clspst02b_base):
        def foo(self, a, b):
            """contract test clspst02

            post: __return__ % 3 == 0

            >>> b = clspst02b_base()
            >>> b.foo(2, 3)
            6
            >>> b.foo(2, 1)
            2
            >>> b.foo(1, 3)
            3
            >>> d = clspst02b_derived()
            >>> d.foo(2, 3)
            6
            >>> d.foo(2, 1)
            2
            >>> d.foo(1, 3)
            3
            """
            return a * b

# 14. clsinv06 class invariants AREN'T tested on constructor exception
#
class clsinv06a:
    """Test class invariants.

    inv:: self.a == 5

    >>> i = clsinv06a(5)
    >>> i = clsinv06a(7)
    Traceback (most recent call last):
       ...
    InvariantViolationError: ('testdbc4.clsinv06a', 3)
    >>> i = clsinv06a(4)
    Traceback (most recent call last):
       ...
    ZeroDivisionError: integer division or modulo by zero
    """
    def __init__(self, a):
        1 / (a % 2)
        self.a = a

if hasobject:
    class clsinv06b(object):
        """Test class invariants.

        inv:: self.a == 5

        >>> i = clsinv06b(5)
        >>> i = clsinv06b(7)
        Traceback (most recent call last):
           ...
        InvariantViolationError: ('testdbc4.clsinv06b', 3)
        >>> i = clsinv06b(4)
        Traceback (most recent call last):
           ...
        ZeroDivisionError: integer division or modulo by zero
        """
        def __init__(self, a):
            1 / (a % 2)
            self.a = a

__test__ = { '_modinv02': _modinv02, '_modpre01b': _modpre01b,
             '_modpst01b': _modpst01b }

def _test():
    import contract, doctest, testdbc4
    contract.checkmod(testdbc4, contract.CHECK_PRECONDITIONS)
    return doctest.testmod(testdbc4)

if __name__ == '__main__':
    t = _test()
    if t[0] == 0:
        print "test: %d tests succeeded" % t[1]
    else:
        print "test: %d/%d tests failed" % t

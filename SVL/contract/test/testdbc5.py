#!/usr/bin/env python
# Tests various ways of specifying docstring contracts

# contract is at beginning of docstring
# contract is at end of docstring
# multi-line
# single line
# ReST
# comments in middle of expression
# line continuations
"""Test contract docstring parsing
>>> strs(parse_docstring(test1, CODE_CONTRACTS))
[['a > 5', 'b < 6'], ['d > 7', 'e < 8']]
>>> strs(parse_docstring(test2, CODE_CONTRACTS))
[['a > 5', 'b < 6'], ['d > 7', 'e < 8']]
>>> strs(parse_docstring(test3, CODE_CONTRACTS))
[['a > 5', 'b < 6'], ['d > 7', 'e < 8']]
>>> strs(parse_docstring(test4, CODE_CONTRACTS))
[['a > 5', 'b < 6'], ['d > 7', 'e < 8']]
>>> strs(parse_docstring(test5, CODE_CONTRACTS))
[['a > 5', 'b < 6'], ['d > 7', 'e < 8']]
>>> strs(parse_docstring(test6, CODE_CONTRACTS))
[['state in [ test1 , test2 , test3 , test4 ]'], ['True']]
"""
from contract import parse_docstring, CODE_CONTRACTS

def strs(contracts):
    """Filter out everything except contract expressions from a docstring.
    """
    return [[b[0] for b in a[2]] for a in contracts]

test1 = """Silly function.

pre:
    a > 5
    b < 6
post[]:
    d > 7
    e < 8

That is all.
"""

test2 = """pre:
    a > 5
    b < 6
post[]:
    d > 7
    e < 8

That is all.
"""

test3 = """pre:
    a > 5
    b < 6
post[]:
    d > 7
    e < 8"""

test4 = """pre: a > 5
pre: b < 6
post[]: d > 7
post[]: e < 8"""

test5 = """Sample function.

pre:: # Eiffel-style pre-conditions
    a > \
        5   # more comments

    #comments

    b < 6
post[]:
     d > 7
     e < 8
"""

test6 = """Another function.

pre:: state in [test1, test2, # comment
                test3, test4]
post[]: True
"""

def _test():
    import doctest, testdbc5
    return doctest.testmod(testdbc5)

if __name__ == '__main__':
    t = _test()
    if t[0] == 0:
        print "test: %d tests succeeded" % t[1]
    else:
        print "test: %d/%d tests failed" % t

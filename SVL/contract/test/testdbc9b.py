#!/usr/bin/env python
"""Tests Ruben Reifenberg's invalid precondition bug.

The idea here is that a base class does not have any contract checking,
but a base class tightens the (non-existing) preconditions.

The full test is in testdbc9d.py
"""

class Base(object):
    def method(self, a):
        """Loose precondition

        pre: a > 5
        """
        return a * 9


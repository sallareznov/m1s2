#!/usr/bin/env python

from distutils.core import setup

NAME = 'contract'
VERSION = '1.4'
URL = 'http://www.wayforward.net/pycontract/'

DESC = """Annotate function docstrings with pre- and post-conditions,
and class/module docstrings with invariants, and this
automatically checks the contracts while debugging."""

setup(name = NAME,
      version = VERSION,
      description = "Programming by Contract for Python",
      long_description = DESC,
      author = 'Terence Way',
      author_email = 'terry@wayforward.net',
      url = URL,
      download_url = URL + 'contract-%s.tar.gz' % VERSION,
      license = 'Artistic; LPGL; Python Software Foundation License',
      classifiers = [
	'Development Status :: 5 - Production/Stable',
	'Intended Audience :: Developers',
	'License :: OSI Approved :: Artistic License',
	'License :: OSI Approved :: GNU Library or Lesser General Public License (LGPL)',
	'License :: OSI Approved :: Python Software Foundation License',
	'Operating System :: OS Independent',
	'Programming Language :: Python',
	'Topic :: Software Development :: Testing'
      ],
      py_modules = ['contract'])

#!/bin/bash
find . -name "*.java" |while read A ; do
  echo "Compiling $A"
  cd src
  pwd
  javac -cp 'lib/*:.' $A
  if [ "$?" != 0 ]; then
    exit 1
  fi
done



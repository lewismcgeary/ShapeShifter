#!/bin/bash

mkdir -p tmp

filename=$(basename "$1")
newfile="${filename%.*}_reveal.xml"
shortname="${filename%.*}_hidden"

cat reveal.xml | sed -e "s/REPLACEME/$shortname/" > tmp/$newfile

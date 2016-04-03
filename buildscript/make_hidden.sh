#!/bin/bash

mkdir -p tmp

filename=$(basename "$1")
newfile="${filename%.*}_hidden.xml"

cat $1 | sed -e 's/<path/\<clip-path/' | grep -v fillColor | sed -e 's/width=\".*\"/width=\"@dimen\/shape_size\"/' | sed -e 's/height=\".*\"/height=\"@dimen\/shape_size\"/' | grep -v \<\/vector > tmp/$newfile

cat ending.xml >>  tmp/$newfile

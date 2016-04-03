#!/bin/bash

cat start_list.xml > shape_list.xml

for i in drawableTemplates/*
do
    filename=$(basename "$i")
    itemline="<item>${filename%.*}</item>"

    ./make_hidden.sh $i
    ./make_reveal.sh $i
    echo "        "$itemline >> shape_list.xml
done

cat end_list.xml >> shape_list.xml

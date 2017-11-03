#!/bin/bash

# This generates the HTML document
pandoc -s -o index.html --extract-media ./ ProjectDescription.docx

# This generates readable text file using pandoc flavored markdown.
pandoc -s -o ProjectDescription.txt --extract-media ./ ProjectDescription.docx

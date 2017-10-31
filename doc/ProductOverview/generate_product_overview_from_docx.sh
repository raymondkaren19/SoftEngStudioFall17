#!/bin/bash

# This generates the HTML document
pandoc -s --toc -c ./css/github-style-markdown.css -o ProductOverview.html --extract-media ./ ProductOverview.docx


# This generates readable text file using pandoc flavored markdown.
pandoc -s -o ProductOverview.txt --extract-media ./ ProductOverview.docx

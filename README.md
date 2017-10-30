
Welcome to the project

Please see the [contributing guide.](CONTRIBUTING.md)

#### Web Client Environment

* Chrome
* Firefox
* Safari


#### User Types
* Admin (ReadWrite)
* Auditor (ReadOnly Admin)
* User (ReadWrite)
* Observer (Readonly User)

#### Documentation

* Aside from README.md and CONTRIBUTING.md, all other documentation should placed in the `/doc` folder.

* Please use markdown txt files for all documentation. See Updating Docx documents.

* You do not have to write the documentation in markdown directly. You can use something like Pandoc to convert your document to markdown. 

#### Updating Docx documents

* Word documents are binary files that can not be tracker for changes in git. We need to keep track of the changes so we are going to use Pandoc to create a mirror Pandoc flavored Markdown txt file that is readable.

* Install [Pandoc](http://pandoc.org/)

* CD into the `doc/ProductOverview/` dir
* run `pandoc --extract-media ./ ProductOverview.docx -o ProductOverview.txt` This works in macOs and Linux. TODO: Please update this for windows.

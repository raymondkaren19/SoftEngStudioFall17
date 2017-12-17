
Welcome to the project

Please see the [contributing guide.](CONTRIBUTING.md)

#### Web Client Environment

* Chrome
* Firefox
* Safari

#### Deployments

[Staging Environment](http://ec2-52-90-192-232.compute-1.amazonaws.com/root/SoftEngStudioFall17/environments/1) is what is currently on master. 



[Production Server](http://ec2-52-90-192-232.compute-1.amazonaws.com/root/SoftEngStudioFall17/environments/2) is what our customer would see.

In each of the environments you can click on "View deployment" to see the live site.

#### Production server Logs.

- Currently we keep track of the logs on the production server by setting up an automatic cron job which upload the logs every minute. See #90 

- MySQL:  
http://ec2-52-90-192-232.compute-1.amazonaws.com/ci-bot/logs-production-mysql

- TomCat:  
http://ec2-52-90-192-232.compute-1.amazonaws.com/ci-bot/logs-production-tomcat


#### Documentation

* Aside from README.md and CONTRIBUTING.md, all other documentation should placed in the `/doc` folder.

* Please use markdown txt files for all documentation. See Updating Docx documents.

* You do not have to write the documentation in markdown directly. You can use something like Pandoc to convert your document to markdown.

#### Updating Docx documents

* Word documents are binary files that can not be tracker for changes in git. We need to keep track of the changes so we are going to use Pandoc to create a mirror Pandoc flavored Markdown txt file that is readable in as a plain text file. This is desirable because we can keep track of the changes in a visual form.

* Install [Pandoc 2.0+ ](http://pandoc.org/installing.html)

* CD into the `doc/ProductOverview/` dir
* run `pandoc --extract-media ./ ProductOverview.docx -o ProductOverview.txt` This works in macOs and Linux. TODO: Please update this for windows.

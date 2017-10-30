
👍🎉 First off, Congrats on making this far at Brandeis Software Engineering Degree! 🎉👍

#### Table Of Contents

#### Server Development Environment

* Docker
* Ubuntu Trusty
* Java 8
* Maven
* tomcat
* MySQL

#### Communication Plan

* Open communications with each other encourage sharing opinions.

* Equal time for expressing ideas during meetings.

* Provide feedback gracefully in a roundtable format.

* Order of contacting: Gitlab Issue comment -> Email -> Text Message.


#### Conflict Resolution Plan

* Patience and understanding will lead our approach to conflicts. See Communication plan.

* Any conflicts lasting more than 1 day should be listed as a blocker in the Gitlab issues. Just fill out a new issue and add the label `BLOCKER` so we can discuss on our next stand up.

* Please ask for help if you get stuck. We should aim at Pair Programing when we start to have issues. Please let the team know when you are pair programming during our meetings. See Stand Up.

* Schedule Mob programming session when when working on a blocker item or something that needs everybody's attention. TODO: Decide if this is a good idea in a remote environment.



#### Stand Up

* Sunday 8pm East Coast time

* Thursdays 8pm East Coast time


#### Sprint / Schedule

* Wednesday to Tuesday is the work week.

* Weekends are available to pair and mob programming.

* A sprint is 2 weeks.


#### Commit Process

* Small chunks, commit often, push often, create merge request often, group approval for any merge request to master. In gitlab Pull Request (PR) are called Merge Request.

* All code that goes to master needs to be done via a merge request .

* No rewriting the history of the master branch.

* Rebase/update local branches after approved merge request is merged ("pull party" after merging to master)

* Every merge request should be attached to an issue. See gitlab process.


#### GitLab Process

* Start from an Issue / User story and create a "Merge Request". This will create a branch with the name of the issue and it will open a "Merge request" / "Pull Request" as a Work In Progress (WIP) Branch.


* You can also do this manually. Create a branch locally and push it before creating the Merge request then just mention the issue in the description. `closes #8`. It is a good idea to add WIP to a merge request that is not ready to be merged.

* Once you are done remove the WIP from you branch so everybody knows the merge request is ready to be reviewed and approved by others.


### Adding a user story

* Select the template USER-STORY when you create an issue.

* Please fill out all the description and Acceptance Criteria / DoD

* Mention dependent issues or related issues.


### Reporting bug

* Please follow the "BUG" template under issues.

* Bug should not have to wait for the next sprint but this is up to the team.

#### Style

* TODO: Decide on what JAVA, Javascript style we are going to use.

* TODO: Decide how to enforce the style on the CI.

####  Release Process

* Automatic release

* Follow the release document practices of archiving and reproducibility.

* TODO: Fill out the details on how we are going to release.

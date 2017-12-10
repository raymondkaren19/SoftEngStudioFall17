
👍🎉 First off, Congrats on making this far at Brandeis Software Engineering Degree! 🎉👍

#### Table Of Contents

#### Server Development Environment

* Docker
* Ubuntu Trusty
* Java 8
* Maven
* tomcat
* MySQL
* Please see the docker buildfile to see specific version.

#### Communication Plan

* Open communications with each other encourage sharing opinions.

* Equal time for expressing ideas during meetings.

* Provide feedback gracefully in a round table format.

* Order of contacting: Gitlab Issue comment -> Email -> Text Message.


#### Conflict Resolution Plan

* Patience and understanding will lead our approach to conflicts. See Communication plan.

* Any conflicts lasting more than 1 day should be listed as a blocker in the Gitlab issues. Just fill out a new issue and add the label `BLOCKER` so we can discuss on our next stand up.

* Please ask for help if you get stuck. We should aim at Pair Programing when we start to have issues. Please let the team know when you are pair programming during our meetings. See Stand Up.

* Schedule Mob programming session when when working on a blocker item or something that needs everybody's attention. TODO: Decide if this is a good idea in a remote environment.



#### Stand Up

* 8pm East Coast time

* Twice a week.


#### Sprint / Schedule

* Wednesday to Tuesday is the work week.

* A sprint is 2 weeks.


#### Commit Process

* Small chunks, commit often, push often, create merge request often, group approval for any merge request to master. In Gitlab Pull Request (PR) are called Merge Request.

* All code that goes to master needs to be done via a merge request .

* No rewriting the history of the master branch unless agreed by email from the rest of the team. (When we need to fix a bag merge or something similar)

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

#### Code Review

* Code review is going to be required on each merge request. 

* TODO: Decide on what JAVA, Javascript style we are going to use.

* TODO: Decide how to enforce the style on the CI.

* Each person who reviews a Merge Request / Pull Request and approves, should respond with "LGTM" (Looks Good to Me) comment. 

* The "LGTM" response is needed because this will trigger an email notification. The thumbs up feature in Gitlab does not trigger emails notification. This is one of the limitation of our version of Gitlab, among others: https://about.gitlab.com/2015/07/29/feature-highlight-merge-request-approvals/

####  Release Process

* Automatic release to staging after merging to master.

* Follow the release document practices of archiving and reproducibility.

* First release to staging. 

* Once staging has been tested to make sure it work, publish to production.

## Docker

### Default SSH Password
Please change the default password in the dockerfile before building. `ssh-docker:devPassword`
Alternatbly, change ssh password on first connection.
https://docs.nexcess.net/article/how-to-change-ssh-passwords-from-the-cli.html

### How to build
`docker build -t tomcat-mysql-image .`

## Password for the user, run the container on the background and map ports
## Change the MySQL password beforing running. Take note.
`docker run -d --name="tomcat-mysql-run" -e MYSQL_PASSWORD=ChangeMeToSomeReallyLongPassword -p 3306:3306 -p 8888:8080 -p 2222:22 tomcat-mysql-image`

Delete the container if you are changing the build image. 
`docker rm tomcat-mysql-run`

### SSH into running container

From host machine:
`ssh ssh-docker@0.0.0.0 -p 2222`
From outside host machine, replace 0.0.0.0 with host machine address.

### Alternatibly you can run bash from the host machine.
`docker exec -it tomcat-mysql-run /bin/bash`

## Files derived from Source
`https://github.com/Aallam/Dockerfiles/tree/master/tomcat-mysql`

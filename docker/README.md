## Docker

### How to build
`docker build -t tomcat-mysql-image .`

## Password for the user, run the container on the background and map ports
## Change the MySQL password
`docker run -d --name="tomcat-mysql-run" -e MYSQL_PASSWORD=root -p 3306:3306 -p 8080:8080 tomcat-mysql-image`

## Files derived from Source
`https://github.com/Aallam/Dockerfiles/tree/master/tomcat-mysql`

# Spring-Boot-with-H2
Demo project for beginners with H2 DB (In Memory Data Base)

* Spring Boot
* MySQL 5.7
* Logging
* Rest
* JSON (Parent child relation)
* ApplicationRunner

# Notes:

* Download MySQL docker image from [Docker Hub](https://hub.docker.com/_/mysql)
* Pull and run docker mysql image:
	
		docker run mysql:5.7
* After image pulled then it'll sohow error to set username and password.
* Set Username and password and run in detached mood (-d):
	
		docker run -d -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=Az -e MYSQL_USER=az -e MYSQL_PASSWORD=root mysql:5.7
* Download MySQL client shell [url](https://dev.mysql.com/downloads/shell)
* After installation stop mysql docker image and run with exposing port option:
* Get Container list:
	
		docker container ls
* Stop mysql:5.7 by container id from container list:
	
		docker container stop containerID

* Now Remove:
 
		docker rm containerID
* Rerun mysql:5.7 and expose port:
	
		docker run -d -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=Az -e MYSQL_USER=az -e MYSQL_PASSWORD=root --name mysql -p 3306:3306 mysql:5.7
* After installation mySQL shell run command in cmd:
	
		mysqlsh
* Connect to mysql:
	
		\connect root@localhost:3306
* For starting mysql server it'll take 1 to 2 mints thne run above command else you'll face error.
* After above command it'll required password enter that (in my case root is the password).
* Now make password remeber and then select schema (in my case db is Az).
	
		\use Az
* convert as sql:
	
		\sql
* run application and hit [url](http://localhost:8080/)
* For Users hit [url](http://localhost:8080/users)
* Also verify from mySQL Shel by running this command:
	
		SELECT * FROM user;

###Environment Variables:
	
* Without docker application will run with:

		spring.datasource.url=jdbc:mysql://localhost:3306/Az
		spring.datasource.username=az
		spring.datasource.password=root

* Without docker image application needs to set some environment variables:

		spring.datasource.url=jdbc:mysql://${RDS_HOSTNAME:localhost}:${RDS_PORT:3306}/${RDS_DB_NAME:Az}
		spring.datasource.username=${RDS_USERNAME:az}
		spring.datasource.password=${RDS_PASSWORD:root}
* After all this we can create Dockerfile and build docker image by maven plugin or command ([Have a look Docker](/Docker))
* Now for running web application as docker image in docker we need bellow command:

* For build:

		docker build -t az-spring-boot-with-mysql .
* Check Image:
		
		docker images
* Run Image:

		docker run -p 8080:8080 --link=mysql -e RDS_HOSTNAME=mysql -e RDS_PORT=3306 -e RDS_DB_NAME=Az az-spring-boot-with-mysql:latest

* Check Application root url [http://localhost:8080/spring-boot-with-mysql/](http://localhost:8080/spring-boot-with-mysql/).
* Check users data [http://localhost:8080/spring-boot-with-mysql/users](http://localhost:8080/spring-boot-with-mysql/users).
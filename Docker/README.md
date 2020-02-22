# Docker

* I'll discuss here only related to Docker which includes Docker installation, Docker Hub, Docker Image and run docker images.

#### Docker Hub:
* It's a central repository where we save over Docker images.
* When we try to run docker image locally Docker will check that image is available locally or not, if not then it'll download then run that image.


#### Commands:
** Run Docker Image:

	docker run -p imageDefined-Port:exposing-port docker-hub-image-full-path:version
** Push Docker Image:
	
	docker push dockerID/nameOfImage:version
** Local Images:

	docker images
* Images are noting just a file that have complete information of application,
* When we run docker image it will automatically create container

** List of Containers:

	docker container ls
* Containers is running version of docker image.
** Start Container:

	docker container start containerID/Names
** Stop Container:

	docker container stop containerID/Names
** Logs of Container:
	
	docker container log containerID/Names
** Remove Container:
	
	docker container rm containerID/Names
** Romve all the stop containers
	
	docker container prune
** Image history:

	docker image history dockerID/nameOfImage:version
** Remove Image:

	docker image remove imageID
* Image will not remove if it is still part of start/stop container, need to remove that container then remove image.
#### Helping video
* Watch this tutorial for Docker [https://www.youtube.com/watch?v=Rt5G5Gj7RP0&feature=youtu.be](https://www.youtube.com/watch?v=Rt5G5Gj7RP0&feature=youtu.be)

#### Build Docker Image:
* Use this commande:
	
	docker build -t image-name-that-define-in-Dockerfil .
* . means root-directory where Dockerfile present
#### Run Docker Image:

	docker run -p 9090:8080 image-name
* 9090 will be the container exposed port for clients.
* 8080 is the port image exposed port.

#### Push Image to DockerHub:
* For push image we need add tag to image.
	
	docker tag image-name dockerID/image-name:0.0.1
* Now confirm this:
	
	docker image ls
* Now Push image:
	
	docker push dockerID/image-name:0.0.1
* It'll take some time then we can cofirm from docker-hub

#### Pull Docker Image:

	docker pull dockerID/image-name:0.0.1
* Now we can run this image by simple docker run command.

#### Remove Docker Image:
* For removing docker images needs to stop and remove container which referencing that image.

			docker image rm image-name:tag
* Sometimes we have to forcefuly remove image
		
		docker image rm -f image-name
		

#### Create Image by MVN plugin:
* [Helping guide](https://youtu.be/Rt5G5Gj7RP0?t=2413)
* Need maven plugin:

		<plugin>
			<groupId>com.spotify</groupId>
			<artifactId>dockerfile-maven-plugin</artifactId>
			<version>1.4.10</version>
			<executions>
				<execution>
					<id>default</id>
					<goals>
						<goal>build</goal>
						<!-- <goal>push</goal> --> 
					</goals>
				</execution>
			</executions>
			<configuration>
				<repository>azharmobeen/${project.artifactId}</repository>
				<tag>${project.version}</tag>
				<skipDockerInfo>true</skipDockerInfo>
			</configuration>
		</plugin>

#### Dockerfil Examples:

* For Jar file with built in tomcat:
	
			FROM openjdk:8
			EXPOSE 8080
			ADD target/spring-boot-with-h2.jar spring-boot-with-h2-docker.jar
			ENTRYPOINT ["java","-jar","spring-boot-with-h2-docker.jar"]
* In above Dockerfil expose port is 8080 with respect to Spring boot built in tomcat or property file port/default port 8080
* For war file with tomcat and Java8:
	
			FROM tomcat:8.0.51-jre8-alpine
			EXPOSE 8080
			RUN rm -rf /usr/local/tomcat/webapps/*
			COPY target/*.war /usr/local/tomcat/webapps/spring-boot-with-h2.war
			CMD ["catalina.sh","run"]
* In above Dockerfil expose port is 8080 with respect to tomcat image `tomcat:8.0.51-jre8-alpine`

##### Installation:
* Visit [https://docs.docker.com/get-docker/](https://docs.docker.com/get-docker/)
* It needs singup for docker then download and install.
* Installation guide also available on above link

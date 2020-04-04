##### Installation by docker image steps:
* docker pull jenkins/jenkins
* Create Folder `jenkins` then run docker image: 
* docker run -d -p 49001:8080 -v $PWD/jenkins:/var/jenkins_home:z -t jenkins/jenkins
* In above I have expose jennkins on port 49001
* Need to check jenkins container log for password: 
* run: docker ps
* it will show jenkins/jenkins container id then
* run: docker logs containerId
* copy initial password and past into bellow url
* http:localhost:49001 (it will asked initail password)

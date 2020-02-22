# Az-Spring-Boot-with-Kubernetes

* In this repository I'll use Spring-Boot microservice with kubernates, Docker and deploy on google cloud.
* We can achieve this step by step.
#### About Kubernetes (K8S):
* It's a tool for deploy application, manage containers, self healing and server side load balancing by creating multiple instances.
* It help us for continous deployment with zero down time for your application.
* We just need to pass docker image and run with few commands.
* No need to worry about frameworks, dependencies, environment just deploy docker image and run.
* With in seconds you can run multiple instances for your application.
* When some instance of your application is down with some reason kubernates will up an other instance and share load over all the instances.
* We can scale our microservice instances with respect to load.
* With Kubernetes we can deploy our application in any cloud platform.
* In Google Cloud Platform (GCP) have manage service Google Kubernetes Engine (GKE).
* Different cloud providers called it:
** Amazon called it Elastic Kubernetes Service (EKS).
** Microsoft Azure called it Azure Kubernetes Service (AKS).
** Google called it Google Kubernetes Engine (GKE).
##### Creating google cloud account:
* Login with your google account then
* visit [https://cloud.google.com/](https://cloud.google.com/)
* Click get started for free and then accept all the terms of service after that click continue.
* after adding required detail just click start my free trial.
##### Kubernetes Clusters:
* Kubernetes mange resources (Servers/ Virtual servers)
* Different cloud providers have different name:
** Amazon called them (EC2) Elastic compute cloud.
** Microsoft Azure called them virtual machines.
** Google called them Compute Engine.
** And kubernates called it Nodes (it have two types of nodes Master Nodes and Worker Nodes).
* So what is *cluster* is the combination of nodes and master nodes.
* The Node those do work called worker nodes.
* The Node those are managing worker nodes called master node.
###### Creating Cluster
* Go to console and select my first project.
* Now we need to enable Kubernetes Engine.
* Type Kubernetes Engine in search bar and click on it.
* After some time create cluster button will be enable.
* We'll go for default and simple setting to create cluster.
* click create button it'll take also time to complete.
##### Application Deployment:
* First step is to connect with cluster.
* Google make it so easy by providing Google cloud shell.
* Top right corner there is a button for cloud shell click on it for activation.
* Now Click connect button in cluster page it will show command, copy that command and then past into cloud shell.
  ** kubectl (Kube Controller) is the command that allow kubernates to intract with cluster.
##### kubectl command:
* We need this commande for many purpose like If you want to deploy application, create multiple instances, deploy new version for application.
* lets try kubectl version (It'll show some details)
##### Commands:
* For Deployment:

	kubectl create deployment application-name --image=az-docker/Az-Spring-Rest-Api:0.0.1.RELEASE
* In above commande --image = docker image that is available on hub.docker.com/az-docker/Az-Spring-Rest-Api with different versions
* Above command will create deployment now we need to *expose* this deployment:

	kubectl expose deployment application-name --type=LoadBalancer --port=8080
* It will take time to complete this command.
* Now goto services & ingress tab, if application status is ok then click on endpoint.
* For Events:

	kubectl get events
* Back end kubernates doning many things
* For Pods:

	kubectl get pods
* For Replicaset:

	kubectl get replicaset
* For Deployment show:

	kubectl get deployment
* For Service:

	kubectl get service
##### About Pods:
* Most important concept in kubernates.
* Smallest deployable unit in kubernates.
* Containers live inside pods.
* Run :

	kubectl get pods -o wide
* We can see every pod have unique IP and we can know that how many containers runing in this pod.
* With in the same pod containers can share resource.
* Run :

	kubectl explain pods
* Every node can have many pods and every pod can have many containers.
##### Replicaset:
* Command:

	kubectl get replicaset / kubectl get rs
* Execute this commande to delete pod:

	kubectl delete pods unique-id-for-application
* It'll be deleted but when we get pods detial again, then an other application instance will be up automatically by *Replicaset.
* Scale Deployment with multiple applicaiton instances:

	kubectl scale deployment application-name --replicas=3
* In above commande it will create three instances for the application and when we try to delete one of them then kubernates automatically up and run an other instance of the application because of *Replicaset.
* We can check desired and current replicaset by:

	kubectl get replicaset OR rs
* Check background commands order by timestamp:

	kubectl get events --sort-by=.metadata.creationTimestamp
* Deployment new version with zero downtime (Update Docker Image):
** First we'll check old applicaiton details:

	kubectl get rs -o wide
** Above commande will tell us containers name and image detail.
** Now run bellow command:

	kubectl set image deployment application-name containers-name=same-image-path-from-docker-hub:version
	
* When we want to deploy new version of application kubernates deploy new version in new pod then kubernates kill one pod of older version then so on.
* Service:
* Run 	
	
	kubectl get pods -o wide
* It will show detail of every instance of application with Id and IP. Every instance is runing in different IP but for enduser/consumer needs only one IP to call this application.
* Kubernetes handle internaly with LoadBalancer and called different instances all this done by *Kubernetes Service*
* When we expose deployment then service will be created.
##### Master Node:
* It have Api Server, Distributed Database, Scheduler and Controller Manager.
** All the information related to deployment, cluster configurations, repicas are saved in Distributed Database (etcd).
** All the commands executed/changes submitted by kubectl to API server, by Api server cloud shell communicate with kubernates cluster, same for kubernates consol.
** Scheduler is responsible for Scheduling pods on the nodes. (As we know cluster have many nodes and every node have many pods).
** Controller Manager is manage overall the health of cluster.
##### Worker Node:
* All the user applications are runing in pods of worker nodes.
* Worker Node have Node Agent, Networking Component, Containers runtime and Pods.
** Node Agent manages aplication stats if one pod is down then it tell master node and then master node create an other pod/instance.
##### Check Status of Node Component:
	
	kubectl get componentstatuses
##### Google Cloud Regions and Zones:
* When we create cluster kubernates ask us for region and zone.
#### Docker:
* Check Docker README file

#### Build Docker Image:
* Needs maven plugin and DockerFile
* [Helping guide](https://youtu.be/Rt5G5Gj7RP0?t=2413)

##### Installation:
* Visit [https://docs.docker.com/get-docker/](https://docs.docker.com/get-docker/)
* It needs singup for docker then download and install.
* Installation guide also available on above link

##### Install tools to deploy from local machine:
* Both are command line interface.
* [Install kubectl For kubernetes.](https://kubernetes.io/docs/tasks/tools/install-kubectl/)
* [Install gCloud For Google Cloud.](https://cloud.google.com/sdk/docs/)

#### How to use:
* After both installation just run google cloud
* Now got to console and click on connect for cluster popup will apear and copy highlighted command, in my case bellow is the command:
	
	gcloud container clusters get-credentials standard-cluster-1 --zone us-central1-a --project leafy-bulwark-266007

##### Maintain Record for deployment (Change-cause):
** When we use --record with deployment it will save as record.

	kubectl set image deployment application-name container-name=dockerID/application-name:version --record
** Show application version records (Application deployment history):

	kubectl rollout history deployment application-name
** Check status of latest deployment:

	kubectl rollout status deployment application-name
##### Undo Deployment:

	kubectl rollout undo deployment application-name --to-revision=3
##### Logs:
* With podID we can get logs of application
* First get podID

	kubectl get pods
* Now get log:

	kubectl logs podID
* If you want to follow the logs:

	kubectl logs podID -f
##### Get Deployment Detail:

* Few Detail:

	kubectl get deployment application-name
* More Details:

	kubectl get deployment application-name -o wide
* More and More Details:

	kubectl get deployment application-name -o yaml
* For Deletion:

	kubectl delete all -l app=hello-world-rest-api

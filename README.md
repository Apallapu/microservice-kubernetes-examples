# microservice-kubernetes-examples




step to deploy the micro service in kubernate
=============================================

step1:
=====
  create the spring boot project

step2:
=====
  create the docker file for spring boot project
  
  example docker file below
  ========================
  Dockerfile
  =============
  FROM openjdk:10.0.2-jre-slim
COPY target/mybus-service-0.0.1-SNAPSHOT.jar .
CMD /usr/bin/java -Xmx400m -Xms400m -jar mybus-service-0.0.1-SNAPSHOT.jar
EXPOSE 8080

step3:
=====
  Build the spring boot project with below docker commands
  
  example:docker build -t mybus-service .
  
  above command build the spring boot project and create docker image in local system
  how to check the docker images in local system
  
  example: docker image ls
  
  
step4:
===== 
   push the docker image into docker hub,before pushing the docker image into docker hub ,create the tag the docker images
   
   example:docker tag mybus-service ankammapallapu/mybus-service:latest
   
   login the docker hub by using the below commands
   docker login -u=userid -p=password 
   
   then push the docker image into docker hub by using below command
   
   docker push ankammapallapu/mybus-service:latest
   
 
 step5:
======= 

its time to deploy the micro service in kubernate.

install the minikube and kubectl command

create the pod and service yaml file in spring boot project

pod.deploy.yaml
==============

apiVersion: apps/v1
kind: Deployment
metadata:
  name: mybus-service
spec:
  replicas: 1
  selector:
    matchLabels:
      app: mybus-service
  template:
    metadata:
      labels:
        app: mybus-service
    spec:
      containers:
      - name: mybus-service
        image: ankammapallapu/mybus-service:latest

		

example of service.yaml file
============================
service.yaml
===========
apiVersion: v1
kind: Service
metadata:
  name: mybus-service
spec:
  selector:
    app: mybus-service
  ports:
  - name: http
    port: 9192
    nodePort: 30099
  type: NodePort

  
  

 

  
step6:
======= 
deploy the micro service application in kubernate with below commands

kubectl apply -f pod-deploy.yaml
kubect apply -f service.yaml



step7:
=======
check the below url for application working or not

http://minikubeipaddress:30090/swagger-ui.html
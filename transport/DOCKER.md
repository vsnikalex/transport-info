# Extending, Securing and Dockerizing Spring Boot Microservices
"Extending, Securing and Dockerizing Spring Boot Microservices" from LinkedIn Learning.
by, Mary Ellen Bowman, @MEllenBowman



Final Product requires External MySql Database.
Install Docker For Mac/Windows/Linux
#### Docker Commands
##### Start MySql Container (downloads image if not found)
``
docker run --detach --name ti-mysql -p 6604:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=transportinfo -e MYSQL_USER=admin -e MYSQL_PASSWORD=admin -d mysql
``

##### view all images
``
docker images
``

##### view all containers (running or not)
``
docker ps -a
``
##### Interact with Database (link to ti-mysql container) with mysql client
``
docker run -it --link ti-mysql:mysql --rm mysql sh -c 'exec mysql -h"$MYSQL_PORT_3306_TCP_ADDR" -P"$MYSQL_PORT_3306_TCP_PORT" -uroot -p"$MYSQL_ENV_MYSQL_ROOT_PASSWORD"'
``
##### Stop ti-mysql container
``
docker stop ti-mysql
``
##### (ReStart) ti-mysql container
``
docker start ti-mysql
``
##### Remove ti-mysql container (must stop it first)
``
docker rm ti-mysql
``
##### Remove image (must stop and remove container first)
``
docker rmi mysql:latest
``

##### Use database
``
mysql>  show databases;  use transportinfo;  show tables;  quit
``
##### Full docker command
``
docker build -t transportinfo .
&& docker run
-p 8080:8080
--env spring.profiles.active=docker
--name ti-app
-e server=ti-mysql
-e port=3306
-e dbuser=admin
-e dbpassword=admin
--link ti-mysql
-d
transportinfo
``

##### RabbitMQ
``
docker run -d --hostname ti-rabbit --name ti-rabbit -p 15672:15672 -p 5672:5672 -e RABBITMQ_DEFAULT_VHOST=transportinfo -e RABBITMQ_DEFAULT_USER=admin -e RABBITMQ_DEFAULT_PASS=admin -e RABBITMQ_NODE_PORT=5672 rabbitmq:management
``
##### ActiveMQ
``
docker run -d -it --rm -p 8161:8161 -p 61616:61616 --hostname ti-activemq --name ti-activemq -e ARTEMIS_USERNAME=admin -e ARTEMIS_PASSWORD=admin  vromero/activemq-artemis
``

``
docker run -p 61616:61616 -p 8161:8161 --hostname ti-activemq --name ti-activemq rmohr/activemq
``
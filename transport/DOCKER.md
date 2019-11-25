# Docker for Transport module

#### Start MySql Container (downloads image if not found)
```
docker run --detach --name ti-mysql -p 6604:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=transportinfo -e MYSQL_USER=admin -e MYSQL_PASSWORD=admin -d mysql
```
##### view all images
```
docker images
```
##### view all containers (running or not)
```
docker ps -a
```
##### Interact with Database (link to ti-mysql container) with mysql client
```
docker run -it --link ti-mysql:mysql --rm mysql sh -c 'exec mysql -h"$MYSQL_PORT_3306_TCP_ADDR" -P"$MYSQL_PORT_3306_TCP_PORT" -uroot -p"$MYSQL_ENV_MYSQL_ROOT_PASSWORD"'
```
##### SQL commands
```
mysql>  show databases;  use transportinfo;  show tables;  quit
```
##### Stop ti-mysql container
```
docker stop ti-mysql
```
##### (Re)Start ti-mysql container
```
docker start ti-mysql
```
##### Remove ti-mysql container (must stop it first)
```
docker rm ti-mysql
```
##### Remove image (must stop and remove container first)
```
docker rmi mysql:latest
```

#### Start ActiveMQ
```
docker run -d -it --rm -p 8161:8161 -p 61616:61616 --hostname ti-activemq --name ti-activemq -e ARTEMIS_USERNAME=admin -e ARTEMIS_PASSWORD=admin  vromero/activemq-artemis
```

#### Final configuration (WF admin page localhost:9990)
```
docker build -t ti-transport .
&& docker run
-p 3333:8080 -p 127.0.0.1:9990:9990
--env spring.profiles.active=docker
--name ti-transport
-d
-e server=ti-mysql -e port=3306 -e dbuser=admin -e dbpassword=admin
--link ti-activemq --link ti-mysql
ti-transport 
```

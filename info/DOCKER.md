# Docker for Info module

#### Final configuration
``
docker build -t ti-info .
&& docker run
-p 8080:8080
--name ti-info
--link ti-activemq
ti-info 
``

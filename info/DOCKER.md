# Docker for Info module

#### Final configuration
``
docker build -t ti-info .
&& docker run
-p 127.0.0.1:18080:29697
--name ti-info
--link ti-activemq
ti-info 
``

FROM jboss/wildfly

ADD wildfly-conf/modules /opt/jboss/wildfly/modules/org/apache/activemq/ra/main/
COPY wildfly-conf/activemq-rar-5.11.1.rar /opt/jboss/wildfly/standalone/deployments/
COPY wildfly-conf/standalone-full.xml /opt/jboss/wildfly/standalone/configuration/standalone-full.xml
COPY helloworld-activemq/target/helloworld-activemq.war /opt/jboss/wildfly/standalone/deployments/

RUN /opt/jboss/wildfly/bin/add-user.sh admin admin --silent
CMD ["/opt/jboss/wildfly/bin/standalone.sh",  "-c", "standalone-full.xml", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]

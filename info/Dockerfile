FROM jboss/wildfly

COPY wildfly-config/module.xml /opt/jboss/wildfly/modules/org/apache/activemq/ra/main/
ADD wildfly-config/unpacked /opt/jboss/wildfly/modules/org/apache/activemq/ra/main/
COPY wildfly-config/activemq-rar-5.15.9.rar /opt/jboss/wildfly/standalone/deployments/

COPY wildfly-config/standalone-full.xml /opt/jboss/wildfly/standalone/configuration/standalone-full.xml

COPY target/info.war /opt/jboss/wildfly/standalone/deployments/

RUN /opt/jboss/wildfly/bin/add-user.sh admin admin --silent
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-c", "standalone-full.xml", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]

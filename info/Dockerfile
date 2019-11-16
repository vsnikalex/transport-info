FROM jboss/wildfly

COPY wildfly-config/standalone-full.xml /opt/jboss/wildfly/standalone/configuration/standalone-full.xml
COPY target/info.war /opt/jboss/wildfly/standalone/deployments/

RUN /opt/jboss/wildfly/bin/add-user.sh admin admin --silent
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-c", "standalone-full.xml", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]

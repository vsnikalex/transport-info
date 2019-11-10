FROM jboss/wildfly
COPY target/TransportInfo.war /opt/jboss/wildfly/standalone/deployments/

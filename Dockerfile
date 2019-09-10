FROM jboss/wildfly
 
ADD ./target/rehaTabloid.war /opt/jboss/wildfly/standalone/deployments/
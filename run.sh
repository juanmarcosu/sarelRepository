export MAVEN_OPTS='-Xmx4000M -XX:MaxPermSize=4000M -Xdebug -Xrunjdwp:transport=dt_socket,address=4000,server=y,suspend=y'
mvn jetty:run

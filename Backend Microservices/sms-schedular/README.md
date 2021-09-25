# SMS-Service #
> Multi-Module Application with log4j2 rotation and Swagger-UI 2

- JBoss
- Auth 2.0
- Cache Management using H2 Database
- Wild Fly
- Key Clock
- Kafka Consumer

[Swagger-UI](http://localhost:8090/sms-service/api/v1/swagger-ui.html "Swagger URL")
[Embeddable-Demo](https://www.callicoder.com/hibernate-spring-boot-jpa-embeddable-demo/ "Embeddable Demo")

## Versions ##
> Mongo DB Version _v3.2.22_
> MySql Version 14.14 Distrib 5.7.25

```cd .. && mvn clean install -DskipTests && cd ezr-oms-web-service && mvn spring-boot:run -Drun.arguments="--spring.profiles.active=prod" -Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5090"```

## Steps to SetUp Server ##
1. Download Tomcat 9.0.16
2. Copy application.properies to Tomcat-9.0.16/conf/application.properties
3. Copy mail-templates at Tomcat-9.0.16/bin/mail-templates
3. Create Environment file - Tomcat-9.0.16/bin/setenv.sh
4. Write below in setenv.sh
Spring 1: JAVA_OPTS='-Dspring.profiles.active=prod -Dspring.config.location=<Tomcat-9.0.16 PATH>/conf/ -Duser.timezone=US/Pacific -Dlog4j.rootAppender="console" -Drun.jvmArguments="-Xms1g -Xmx2g -Xmn512m -Xss256m"'
Spring 2: JAVA_OPTS='-Dspring.profiles.active=prod -Dspring.config.additional-location=<Tomcat-9.0.16 PATH>/conf/ -Duser.timezone=US/Pacific -Dlog4j.rootAppender="console"  -Drun.jvmArguments="-Xms1g -Xmx2g -Xmn512m -Xss256m"'
5. See Log4J2 Files Path <Tomcat-9.0.16 PATH>/bin/logs
6. Edit <Tomcat-9.0.16 PATH>/conf/catalina.properties & Edit Array of tomcat.util.scan.StandardJarScanFilter.jarsToSkip & CommentOut annotations-api.jar, mail*.jar
7. Clean webapps dir and Copy War File into webapps/ROOT.war
export LOGGING_CONFIG="-Djava.util.logging.config.file=/opt/apache-tomcat-9.0.16/conf/log4j2.xml"
8. ./bin/catalina stop && ./bin/catalina start
9.  tail -f catalina.out | grep "ApiRequest"
10. tail -100000f catalina.out | egrep -i '/login|"password"' > out.log
11. Copy MySqlConnector.jar into Tomcat Lib

## Security Setup on MongoDB ##
> https://www.digitalocean.com/community/tutorials/how-to-install-and-secure-mongodb-on-ubuntu-16-04
```
use admin
db.createUser({user: "EZRAdmin", pwd: "EZRAdmin@123!", roles: [ { role: "root", db: "admin" } ]})

# Enabling Authentication
sudo nano /etc/mongod.conf

## Log Rotation ##
>https://jfrog.com/knowledge-base/how-to-configure-a-log-rotation-for-the-tomcats-catalina-out-log-file/
/opt/apache-tomcat-9.0.16/logs/catalina*.* {
        su root root
        notifempty
        copytruncate
        daily
        rotate 7
        compress
        missingok
        size 500M
}

...
security:
  authorization: "enabled"
...
```
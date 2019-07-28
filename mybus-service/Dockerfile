FROM openjdk:10.0.2-jre-slim
COPY target/mybus-service-0.0.1-SNAPSHOT.jar .
CMD /usr/bin/java -Xmx400m -Xms400m -jar mybus-service-0.0.1-SNAPSHOT.jar
EXPOSE 8080

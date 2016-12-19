FROM java:8-jre
MAINTAINER Mateusz Okarmus <mateusz.okarmus@gmail.com>

ADD /target/zipkin-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/zipkin-service.jar"]

EXPOSE 9411
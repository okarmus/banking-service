FROM java:8-jre
MAINTAINER Mateusz Okarmus <mateusz.okarmus@gmail.com>

ADD /target/register-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/register-service.jar"]

EXPOSE 8082
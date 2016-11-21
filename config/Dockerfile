FROM java:8-jre
MAINTAINER Mateusz Okarmus <mateusz.okarmus@gmail.com>

ADD ./target/config.jar /app/
CMD ["java", "-Xmx200m" , "-jar", "/app/config.jar"]

EXPOSE 8888
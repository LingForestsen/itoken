FROM openjdk:8-jre
MAINTAINER Sen<1483026405qq.com>

RUN mkdir /app
COPY iToken-config-1.0.0-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar", "--spring.profiles.active=prod"]

EXPOSE 8888
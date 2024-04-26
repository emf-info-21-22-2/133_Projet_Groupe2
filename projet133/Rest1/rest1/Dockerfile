FROM openjdk:17-jdk-alpine
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/rest1-0.0.1-SNAPSHOT.jar rest1.jar
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar rest1.jar
ENV DATABASE_URL=jdbc:mysql://host.docker.internal:3306/rest1
FROM openjdk:17-jdk-alpine
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/apigtw-0.0.1-SNAPSHOT.jar apigtw.jar
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar apigtw.jar
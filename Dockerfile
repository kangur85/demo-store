FROM openjdk:11
VOLUME /tmp
COPY build/libs/demo-store-0.0.1-SNAPSHOT.jar ./app.jar

ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar" ]
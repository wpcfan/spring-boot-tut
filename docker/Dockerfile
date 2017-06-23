FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD build/libs/taskmgr-backend-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Dspring.data.mongodb.uri=mongodb://mongodb/taskmgr -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
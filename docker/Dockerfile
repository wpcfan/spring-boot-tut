FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD "taskmgr-backend-0.0.1-SNAPSHOT.jar" "app.jar"
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS="-Xdebug -Xrunjdwp:server=y,transport=dt_socket,suspend=n"
ENV MONGODB_URI=mongodb://mongodb/taskmgr
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Dspring.data.mongodb.uri=$MONGODB_URI -Djava.security.egd=file:/dev/urandom -jar /app.jar" ]
#-----------------------------------------------------------------------------------------------------
# User Service
#-----------------------------------------------------------------------------------------------------
FROM openjdk:8u121-jdk-alpine

LABEL author="Peng Wang <wpcfan@gmail.com>"

# Keep consistent with build.gradle
ENV APP_JAR_NAME zipkinserver
ENV APP_JAR_VERSION 0.0.1

# Install curl
RUN apk --update add curl bash && \
	rm -rf /var/cache/apk/*

RUN mkdir /app

ADD ${APP_JAR_NAME}-${APP_JAR_VERSION}.jar /app/
ADD run.sh /app/
RUN chmod +x /app/run.sh

WORKDIR /app

EXPOSE 9411

ENTRYPOINT ["/bin/bash","-c"]
CMD ["/app/run.sh"]

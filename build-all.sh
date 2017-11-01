#!/bin/sh

./gradlew :gateway:clean :gateway:build;
./gradlew :auth:clean :auth:build;
./gradlew :config-server:clean :config-server:build;
./gradlew :discovery:clean :discovery:build;
./gradlew :taskmgr-jpa:clean :taskmgr-jpa:build;
./gradlew :taskmgr-mongo:clean :taskmgr-mongo:build;
./gradlew :zipkin-server:clean :zipkin-server:build;

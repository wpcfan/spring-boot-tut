#!/bin/sh

./gradlew :gateway:clean :gateway:build :gateway:buildDocker;
./gradlew :auth:clean :auth:build :auth:buildDocker;
./gradlew :config-server:clean :config-server:build :config-server:buildDocker;
./gradlew :discovery:clean :discovery:build :discovery:buildDocker;
./gradlew :taskmgr-jpa:clean :taskmgr-jpa:build :taskmgr-jpa:buildDocker;
./gradlew :taskmgr-mongo:clean :taskmgr-mongo:build :taskmgr-mongo:buildDocker;
./gradlew :zipkin-server:clean :zipkin-server:build :zipkin-server:buildDocker;

#!/bin/bash

echo "building dummy-app docker image"
cd dummy-app
./mvnw install dockerfile:build -DskipTests=true
cd ..

echo "building dummy-app-with-db docker image"
cd dummy-app-with-db
./mvnw install dockerfile:build -DskipTests=true
cd ..

echo "building eureka-server docker image"
cd eureka-server
./mvnw install dockerfile:build -DskipTests=true
cd ..

echo "building zuul docker image"
cd zuul
./mvnw install dockerfile:build -DskipTests=true
cd ..

echo "building gateway docker image"
cd gateway
./mvnw install dockerfile:build -DskipTests=true
cd ..

echo "You are ready to start docker-compose: docker-compose up"

FROM amazoncorretto:17
EXPOSE 8080
WORKDIR /app
COPY  build/libs/simple-messenger-system-1.1-SNAPSHOT.jar simple-messenger-system.jar
ENTRYPOINT ["java","-jar","simple-messenger-system.jar"]
FROM amazoncorretto:17-alpine-jdk
COPY target/*.jar places.jar
ENTRYPOINT ["java","-jar","/places.jar"]
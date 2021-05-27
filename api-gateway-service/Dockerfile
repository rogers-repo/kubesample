FROM openjdk:11
MAINTAINER RogerDhas
ADD target/api-gateway-service.jar api-gateway-service.jar
ENV PORT 8765
EXPOSE 8765
ENTRYPOINT ["java","-jar","api-gateway-service.jar" ]

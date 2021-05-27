FROM openjdk:11
MAINTAINER RogerDhas
ADD target/configuration-service.jar configuration-service.jar
ENV PORT 8888
EXPOSE 8888
ENTRYPOINT ["java","-jar","configuration-service.jar" ]

FROM openjdk:11
MAINTAINER RogerDhas
ADD target/service-registry.jar service-registry.jar
ENV PORT 8761
EXPOSE 8761
ENTRYPOINT ["java","-jar","service-registry.jar" ]

FROM openjdk:11
MAINTAINER RogerDhas
ADD target/deliveryagent.jar deliveryagent.jar
ENV PORT 9005
EXPOSE 9005
ENTRYPOINT ["java","-jar","deliveryagent.jar" ]

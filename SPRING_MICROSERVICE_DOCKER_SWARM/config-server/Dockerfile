FROM openjdk:11
ADD build/libs/config-server-1.0.0.RELEASE.jar configServer.jar
ENTRYPOINT ["java","-jar", "-Deureka.client.serviceUrl.defaultZone=${ENV_EUREKA_URL}","configServer.jar"]
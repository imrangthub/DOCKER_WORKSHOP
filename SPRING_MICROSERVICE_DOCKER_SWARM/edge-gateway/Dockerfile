FROM openjdk:11
ADD build/libs/edge-gateway-1.0.0.RELEASE.jar edgeGateway.jar
ENTRYPOINT ["java","-jar", "-Deureka.client.serviceUrl.defaultZone=${ENV_EUREKA_URL}","edgeGateway.jar", "--CONFIG_SERVER=${ENV_CONFIG_SERVER_URL}"]

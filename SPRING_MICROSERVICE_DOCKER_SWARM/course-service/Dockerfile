FROM openjdk:11
ADD build/libs/course-service-1.0.0.RELEASE.jar courseService.jar
ENTRYPOINT ["java","-jar", "-Deureka.client.serviceUrl.defaultZone=${ENV_EUREKA_URL}","courseService.jar", "--CONFIG_SERVER=${ENV_CONFIG_SERVER_URL}"]

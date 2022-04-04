FROM openjdk:11
ADD build/libs/student-service-1.0.0.RELEASE.jar studentService.jar
ENTRYPOINT ["java","-jar", "-Deureka.client.serviceUrl.defaultZone=${ENV_EUREKA_URL}","studentService.jar", "--CONFIG_SERVER=${ENV_CONFIG_SERVER_URL}"]

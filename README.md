# Docker Basic


Create a simple Image from Dockerfile

        FROM ubuntu
        RUN apt-get update
        run apt-get -y install nginx
	    COPY index.html /var/www/html
        EXPOSE 80
        CMD ["nginx", "-g", "daemon off;"]

Build docker image

	=>docker build .
	



# SPRING MICROSERVICE DOCKER-SWARM

BahsSnapshort Docker:


	=>docker network create app-net
	=>docker image build -t imranmadbar/bahs-config-server .
	=>docker push imranmadbar/bahs-config-server 
	=>docker container run --network app-net --name bahsConfigServer --env ENV_EUREKA_URL=http://172.19.0.2:9092/eureka/ -p 9091:9091 -d imranmadbar/bahs-config-server
	=>docker container run --network app-net --name bahsConfigServer --env ENV_EUREKA_URL=http://bahsDiscoveryEureka:9092/eureka/ -p 9091:9091 -d imranmadbar/bahs-config-server

	=>docker image build -t imranmadbar/bahs-discovery-eureka .
	=>docker push imranmadbar/bahs-discovery-eureka
	=>docker container run --network app-net --name bahsDiscoveryEureka -p 9092:9092 -d imranmadbar/bahs-discovery-eureka

	=>java -jar student-service-1.0.0.RELEASE.jar CONFIG_SERVER=configserver:http://172.19.0.3:9091
	=>docker image build -t imranmadbar/bahs-student-service .
	=>docker push imranmadbar/bahs-student-service
	=>docker container run --network app-net --name bahsStudentServer --env ENV_EUREKA_URL=http://172.19.0.2:9092/eureka/ -p 8081:8081 -d imranmadbar/bahs-student-service
	=>docker container run --network app-net --name bahsStudentServer --env ENV_EUREKA_URL=http://172.19.0.2:9092/eureka/ --env ENV_CONFIG_SERVER_URL=configserver:http://172.19.0.3:9091 -p 8081:8081 -d imranmadbar/bahs-student-service  
	=>docker container run --network app-net --name bahsStudentServer --env ENV_EUREKA_URL=http://172.19.0.2:9092/eureka/ --env ENV_CONFIG_SERVER_URL=configserver:http://9aea6bf64c02:9091 -p 8081:8081 -d imranmadbar/bahs-student-service
	=>docker container run --network app-net --name bahsStudentServer --env ENV_EUREKA_URL=http://bahsDiscoveryEureka:9092/eureka/ --env ENV_CONFIG_SERVER_URL=configserver:http://bahsConfigServer:9091 -p 8081:8081 -d imranmadbar/bahs-student-service   

	=>docker image build -t imranmadbar/bahs-course-service .
	=>docker push imranmadbar/bahs-course-service
	=>docker container run --network app-net --name bahsCourseServer --env ENV_EUREKA_URL=http://bahsDiscoveryEureka:9092/eureka/ --env ENV_CONFIG_SERVER_URL=configserver:http://bahsConfigServer:9091 -p 8082:8082 -d imranmadbar/bahs-course-service   

	=>docker image build -t imranmadbar/bahs-edge-gateway .
	=>docker push imranmadbar/bahs-edge-gateway
	=>docker container run --network app-net --name bahsEdgeGateway --env ENV_EUREKA_URL=http://bahsDiscoveryEureka:9092/eureka/ --env ENV_CONFIG_SERVER_URL=configserver:http://bahsConfigServer:9091 -p 9090:9090 -d imranmadbar/bahs-edge-gateway 

	=>docker ps -a
	=>docker start containerId
		
		
# docker-compose:
 
 SimpleFile1: docker-compose.yml
 
        version: "3.9"
        name: simple-composer-prod
        services:
          mysqldb-services:
            image: mysql
            container_name: mysqldb
            restart: always
            environment:
              MYSQL_DATABASE: 'docker_spring_boot_mysql_db'
              MYSQL_ROOT_PASSWORD: 'root'
            ports:
              - '3306:3306'
            volumes:
              - my-db:/var/lib/mysql
        volumes:
          my-db:

 SimpleFile2: docker-compose.yml
 
	version: "3.9"
	services:
	  bahsConfigServer:
		image: imranmadbar/bahs-config-server
		restart: on-failure
		environment:
		  - ENV_EUREKA_URL=http://bahsDiscoveryEureka:9092/eureka/
		ports:
		  - "9091:9091"
		networks:
		  - bahs_net
	  bahsDiscoveryEureka:
		image: imranmadbar/bahs-discovery-eureka
		depends_on:
		  - bahsConfigServer
		ports:
		  - "9092:9092"
		networks:
		  - bahs_net
	  bahsEdgeGateway:
		image: imranmadbar/bahs-edge-gateway
		restart: on-failure
		depends_on:
		  - bahsConfigServer
		  - bahsDiscoveryEureka
		environment:
		  - ENV_EUREKA_URL=http://bahsDiscoveryEureka:9092/eureka/
		  - ENV_CONFIG_SERVER_URL=configserver:http://bahsConfigServer:9091
		ports:
		  - "9090:9090"
		networks:
		  - bahs_net
	  bahsStudentServer:
		image: imranmadbar/bahs-student-service
		restart: on-failure
		depends_on:
		  - bahsConfigServer
		  - bahsDiscoveryEureka
		environment:
		  - ENV_EUREKA_URL=http://bahsDiscoveryEureka:9092/eureka/
		  - ENV_CONFIG_SERVER_URL=configserver:http://bahsConfigServer:9091
		ports:
		  - "8081:8081"
		networks:
		  - bahs_net
	  bahsCourseServer:
		image: imranmadbar/bahs-course-service
		restart: on-failure
		depends_on:
		  - bahsConfigServer
		  - bahsDiscoveryEureka
		environment:
		  - ENV_EUREKA_URL=http://bahsDiscoveryEureka:9092/eureka/
		  - ENV_CONFIG_SERVER_URL=configserver:http://bahsConfigServer:9091
		ports:
		  - "8082:8082"
		networks:
		  - bahs_net
	networks:
	  bahs_net:
		name: bahs_net

=>docker compose up


# docker-swarm:

	=>docker stack ps bahsSwarmStack
	=>docker stack rm bahsSwarmStack
	=>docker service scale serviceId=5
	=>docker stack deploy --compose-file docker-stack-swarm.yml bahsSwarmStack
	=>docker service create --replicas 2 --name SpringBootWebApp -p 8080:8080 imranmadbar/hello-world-java-maven-web-spring-boot

  


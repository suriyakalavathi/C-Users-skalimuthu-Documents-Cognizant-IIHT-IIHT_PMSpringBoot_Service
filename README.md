# IIHT_PMSpringBoot

Source @ GitHub

MySQL Docker Image

Jenkins Docker Image

Build executable jar
    mvn -B -DskipTests clean package

Build docker image
    docker build -t skalimuthu/pmspringboot-service .
    
    Next, run the built docker image
    docker run --network test-docker-network --name test-pmspringboot-service -p 8080:8080 skalimuthu/pmspringboot-service
# IIHT_PMSpringBoot_Service

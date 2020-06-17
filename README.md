# IIHT_PMSpringboot_service

MySQL Workbench

Jenkins

Source Reporsitory in GitHub

Build executable jar
    mvn -B -DskipTests clean package

Build docker image
    docker build -t skalimuthu/IIHT_PMSpringboot_service
    
    Next, run the built docker image
    docker run --network test-docker-network --name test-IIHT_PMSpringboot_service -p 8080:8080 skalimuthu/IIHT_PMSpringboot_service

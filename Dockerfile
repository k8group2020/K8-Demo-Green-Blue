FROM openjdk:8-jre-alpine
 
# copy the packaged jar file into our docker image
COPY target/demoProj-0.0.1-SNAPSHOT.jar /demo.jar
 
# set the startup command to execute the jar
CMD ["java", "-jar", "/demo.jar"]

#https://codefresh.io/docker-tutorial/create-docker-images-for-java/
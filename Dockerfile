# the first stage of our build will use a maven 3.6.1 parent image
FROM maven:3.6.1-jdk-8-alpine AS MAVEN_BUILD
 
WORKDIR /app

COPY ./pom.xml ./pom.xml

# fetch all dependencies
RUN mvn dependency:go-offline -B 

# copy your other files
COPY ./src ./src

# build for release
# NOTE: my-project-* should be replaced with the proper prefix
RUN mvn package && cp target/demoProj-*.jar app.jar
 
# the second stage of our build will use open jdk 8 on alpine 3.9
FROM openjdk:8-jre-alpine3.9

# OPTIONAL: copy dependencies so the thin jar won't need to re-download them
COPY --from=MAVEN_BUILD /root/.m2 /root/.m2

# set deployment directory
WORKDIR /app

# copy over the built artifact from the maven image
COPY --from=MAVEN_BUILD /app/app.jar ./app.jar

# set the startup command to run your binary
CMD ["java", "-jar", "/app/app.jar"]

#Exposing the port
EXPOSE 8087
#https://codefresh.io/docker-tutorial/create-docker-images-for-java/
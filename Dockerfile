# 1st stage, build the app
FROM maven:3.6-jdk-11 as build

WORKDIR /dwz-ex

# Create a first layer to cache the "Maven World" in the local repository.
# Incremental docker builds will always resume after that, unless you update
# the pom
ADD pom.xml .
ADD config.yml .
ADD docker-compose.yml .
RUN mvn package -DskipTests

# Do the Maven build!
# Incremental docker builds will resume here when you change sources
ADD src src
RUN mvn package -DskipTests
RUN echo "done!"

# 2nd stage, build the runtime image
FROM openjdk:11-jre-slim
WORKDIR /dwz-ex

# Copy the binary built in the 1st stage
COPY --from=build /dwz-ex/target/dropwizard-examples-1.0-SNAPSHOT.jar ./
COPY --from=build /dwz-ex/config.yml ./
COPY --from=build /dwz-ex/docker-compose.yml ./

EXPOSE 8080 8081

CMD ["java", "-jar", "dropwizard-examples-1.0-SNAPSHOT.jar", "server", "config.yml"]
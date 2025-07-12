FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install -DskipTests



FROM eclipse-temurin:21-jdk-alpine
ARG USERNAME=user
ARG USER_UID=1000
ARG GROUPNAME=$USERNAME
ARG USER_GID=$USER_UID
RUN addgroup --gid $USER_GID $USERNAME
RUN adduser --disabled-password --gecos "" --uid $USER_UID --ingroup $GROUPNAME $USERNAME
RUN mkdir -p /app && chown $USERNAME /app
WORKDIR /app
RUN rm -f /app/*.jar
COPY --from=build --chown=$USERNAME:$GROUPNAME /app/target/restjava-0.0.1-SNAPSHOT.jar app.jar
USER $USERNAME
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
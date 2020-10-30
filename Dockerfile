FROM openjdk:8-jdk-alpine as builder

EXPOSE 9000

WORKDIR application
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} spring-multiple-auth.jar
RUN java -Djarmode=layertools -jar spring-multiple-auth.jar extract

FROM openjdk:8-jdk-alpine
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
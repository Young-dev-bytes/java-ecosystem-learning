FROM maven as maven-build

WORKDIR /eureka/server
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src src
RUN mvn install -DskipTests


FROM openjdk:17-jdk-alpine
VOLUME /tmp
WORKDIR /app
COPY --from=maven-build /eureka/server/target/*.jar app.jar
EXPOSE 8761
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app/app.jar" ]
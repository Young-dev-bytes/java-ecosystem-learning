FROM maven as maven-build
WORKDIR /conversion/server
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src src
RUN mvn install -DskipTests

FROM openjdk:17-jdk-alpine
VOLUME /tmp

WORKDIR /

COPY --from=maven-build /conversion/server/target/*.jar app.jar

EXPOSE 8100
#ADD target/*.jar app.jar

ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS  -jar /app.jar"]
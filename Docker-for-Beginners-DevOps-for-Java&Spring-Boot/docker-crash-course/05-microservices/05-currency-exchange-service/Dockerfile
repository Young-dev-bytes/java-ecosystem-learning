FROM maven AS maven-build
WORKDIR /exchange/server
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src src
RUN mvn install -DskipTests

FROM openjdk:17-jdk-alpine
#VOLUME /tmp

WORKDIR /

COPY --from=maven-build /exchange/server/target/*.jar app.jar

EXPOSE 8000

ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "java -jar /app.jar"]


# docker run -p 8000:8000 --network=web-mysql-network --name=currency-exchange-service younghub/05-currency-exchange-service:0.0.1-SNAPSHOP
# docker run -p 8100:8100 --network=web-mysql-network -e CURRENCY_EXCHANGE_URI=http://currency-exchange-service:8000 younghub/05-currency-conversion-service:0.0.1-SNAPSHOP
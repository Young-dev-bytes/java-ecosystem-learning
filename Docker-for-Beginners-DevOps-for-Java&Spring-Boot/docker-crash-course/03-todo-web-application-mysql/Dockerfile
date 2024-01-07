FROM maven AS maven-build

WORKDIR /maven/web
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src src
RUN mvn install -DskipTests

FROM tomcat:8.0.51-jre8-alpine
RUN rm -rf /usr/local/tomcat/webapps/*
#COPY ./target/*.war /usr/local/tomcat/webapps/ROOT.war
COPY --from=maven-build /maven/web/target/*.war /usr/local/tomcat/webapps/ROOT.war
CMD ["catalina.sh","run"]
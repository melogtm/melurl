FROM openjdk:17-jdk-alpine
FROM maven AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine
WORKDIR /app

COPY --from=build /app/target/tinyurl-0.0.1-SNAPSHOT.jar /app/tinyurl.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "tinyurl.jar"]
FROM maven:3.8.8-eclipse-temurin-17 AS build

# Copy source code vào container
COPY . /app
WORKDIR /app

# Dùng Maven build project
RUN mvn clean package

# Dùng image Java để chạy app
FROM eclipse-temurin:17-jre
COPY --from=build /app/target/ivivu-0.0.1-SNAPSHOT.war /app.war
EXPOSE 8080
CMD ["java", "-jar", "/app.war"]



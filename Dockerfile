# Bước 1: Build dự án bằng Maven
FROM maven:3.8.8-eclipse-temurin-17 AS build
COPY . /app
WORKDIR /app
RUN mvn clean package -DskipTests

# Bước 2: Chạy với Tomcat (vì Servlet cần container)
FROM tomcat:9-jre17
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]




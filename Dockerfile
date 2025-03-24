# Sử dụng image Java chính thức
FROM openjdk:21-jdk-slim

# Đặt thư mục làm việc
WORKDIR /app

# Copy toàn bộ project vào Docker image
COPY . .

# Build project với Maven
RUN ./mvnw clean package

# Mở cổng 8080 để ứng dụng chạy
EXPOSE 8080

# Lệnh khởi chạy ứng dụng
CMD ["java", "-jar", "target/ivivu.war"]

# ===== Build stage =====
FROM eclipse-temurin:21-jdk AS builder
WORKDIR /build

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN chmod +x mvnw
RUN ./mvnw -v

COPY src src
RUN ./mvnw clean package -DskipTests

# ===== Runtime stage =====
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=builder /build/target/*.jar app.jar

EXPOSE 8000
ENTRYPOINT ["java", "-jar", "app.jar"]

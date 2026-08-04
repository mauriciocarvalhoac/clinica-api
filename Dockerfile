# === ESTÁGIO 1: Compilação (Build) ===
FROM maven:3-eclipse-temurin-25-alpine AS builder
WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# === ESTÁGIO 2: Execução (Runtime) ===
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser
COPY --from=builder /build/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

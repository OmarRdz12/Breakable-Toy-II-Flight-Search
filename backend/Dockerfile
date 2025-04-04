FROM eclipse-temurin:21-jdk as builder
WORKDIR /backend

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

RUN chmod +x ./gradlew
RUN ./gradlew clean build --no-daemon

FROM eclipse-temurin:21-jre-alpine 
WORKDIR /backend
COPY --from=builder /backend/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/backend/app.jar"]
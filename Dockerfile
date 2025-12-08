FROM eclipse-temurin:17-jdk-alpine 
WORKDIR /app 
COPY src/ ./src/ 
RUN mkdir -p data 
RUN javac src/com/example/*.java 
VOLUME ["/app/data"] 
ENTRYPOINT ["java", "-cp", "src", "com.example.App"] 

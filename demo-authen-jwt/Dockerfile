FROM openjdk:11-jre-slim
EXPOSE 8099:8099
ADD target/demo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT java $JAVA_OPTS -jar /app.jar


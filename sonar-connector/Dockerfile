FROM openjdk:10-jre-slim
COPY ./target/code-quality-game-1.0.0-SNAPSHOT.war /usr/src/code-quality-game/
WORKDIR /usr/src/code-quality-game
EXPOSE 8080
CMD ["java", "-jar", "code-quality-game-1.0.0-SNAPSHOT.war"]
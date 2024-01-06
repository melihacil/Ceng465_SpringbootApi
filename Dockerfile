FROM openjdk:17-oracle
COPY build/libs/*.jar distMusicApp.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "distMusicApp.jar"]
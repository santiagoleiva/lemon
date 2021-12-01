FROM openjdk:11

COPY ./build/libs/challenge-*-SNAPSHOT.jar /api.jar

CMD ["java", "-jar", "/api.jar"]

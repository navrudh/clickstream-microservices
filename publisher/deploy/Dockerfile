FROM openjdk:8-jre-alpine

VOLUME /tmp

ADD build/libs/*boot.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-XX:+UnlockExperimentalVMOptions","-XX:+UseCGroupMemoryLimitForHeap","-Djava.security.egd=file:/dev/./urandom","-Dserver.port=8080","-jar","/app.jar"]

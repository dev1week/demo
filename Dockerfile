FROM openjdk:17-jdk
ARG JAR_FILE="build/libs/demo-0.0.1-SNAPSHOT.jar"
COPY ${JAR_FILE} app.jar
EXPOSE 8080
#CMD ls -al
ENTRYPOINT ["java", "-jar","-javaagent:/pinpoint/pinpoint-bootstrap-3.0.1.jar", "-Dpinpoint.agentId=adventcalendarDev","-Dpinpoint.applicationName=adventcalendar","-Dpinpoint.config=./pinpoint/pinpoint-root.config","-Dspring.profiles.active=${SERVER_MODE}","-Duser.timezone=Asia/Seoul", "/app.jar"]
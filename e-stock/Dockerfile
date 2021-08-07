FROM java:8-jre
WORKDIR usr/src
ADD ./target/stock-0.0.1-SNAPSHOT.jar /usr/src/stock-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "stock-0.0.1-SNAPSHOT.jar"]

FROM hub.c.163.com/library/java:8-alpine

ADD target/*.jar application.jar

RUN echo "Asia/Shanghai" >/etc/timezone

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/application.jar"]
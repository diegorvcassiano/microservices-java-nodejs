FROM openjdk:11
VOLUME /tmp
EXPOSE 8081
ADD ./target/product-api-1.0.0.jar product-api.jar
ENTRYPOINT ["java","-jar","/product-api.jar"]
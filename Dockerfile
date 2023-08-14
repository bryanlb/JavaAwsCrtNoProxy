FROM maven:3.9-amazoncorretto-17 as build
COPY . /work/
RUN cd /work; mvn package -DskipTests

FROM amazoncorretto:17
COPY --from=build /work/target/JavaAwsCrtNoProxy.jar /

ENV HTTP_PROXY="http://localhost:8080/"
ENV HTTPS_PROXY="https://localhost:8080/"

ENTRYPOINT [ "java", "-jar", "./JavaAwsCrtNoProxy.jar"]

FROM maven:3.6.3-jdk-11 AS build
LABEL maintainer="gs-w_eto_eb_federal_employees@usgs.gov"

# Add pom.xml and install dependencies
COPY pom.xml /build/pom.xml
WORKDIR /build
RUN mvn -B dependency:go-offline

# Add source code and (by default) build the jar
COPY src /build/src
RUN mvn -B clean package -Dmaven.test.skip=true

FROM usgswma/openjdk:11

RUN apt-get update && apt-get install --no-install-recommends --no-upgrade -y \
    curl \
 && rm -rf /var/lib/apt/lists/*

COPY --chown=1000:1000 --from=build /build/target/wqp.jar app.jar

USER $USER

CMD ["java", "-jar", "app.jar"]

HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -H "Accept: application/json" -v -k "http://127.0.0.1:${SERVER_PORT}${SERVER_CONTEXT_PATH}/about/health" | grep -q "{\"status\":\"UP\"}" || exit 1

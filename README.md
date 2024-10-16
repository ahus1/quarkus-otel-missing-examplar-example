# Quarkus OTEL HTTP Metrics with exemplars

This is to show-case that HTTP metrics don't show exemplars.

```
# build JAR
./mvnw package
# run JAR in background
java -jar target/quarkus-app/quarkus-run.jar &
# Access the HTTP server
curl http://localhost:8080
# Retrieve the metric
curl http://localhost:8080/q/metrics | grep "^http_server_requests_seconds_count"
```

Actual output: 

```
http_server_requests_seconds_count{method="GET",outcome="SUCCESS",status="200",uri="root"} 1.0
```

Expected output: 

```
http_server_requests_seconds_count{method="GET",outcome="SUCCESS",status="200",uri="root"} 1.0 # {span_id="a423857f2c2564f6",trace_id="a4d9bca798183c42aff30d54691df2ff"} 1.0 1729084389.503
```

NOTE: There is a partially patched `Http1xServerConnection.txt` file that can be renamed to `Http1xServerConnection.java` to try out the fix locally.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/otel-exemplar-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- OpenTelemetry ([guide](https://quarkus.io/guides/opentelemetry)): Use OpenTelemetry to trace services
- Micrometer metrics ([guide](https://quarkus.io/guides/micrometer)): Instrument the runtime and your application with dimensional metrics using Micrometer.

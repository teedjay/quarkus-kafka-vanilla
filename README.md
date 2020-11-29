# quarkus-kafka-vanilla project

This project uses [Quarkus](https://quarkus.io/), the Supersonic Subatomic Java Framework.

## Prerequisite
You need JDK 15 to compile and run this project and you also need to enable preview mode since record are used.
Right now enabling preview mode for compiling and dev mode works out of the box, not need to do anthing specific.

But for some test and some more advanced build processing you might need to set this ENV variable.
```
export _JAVA_OPTIONS="--enable-preview"
```

## Push to Docker Hub
Configured with JIB, will push directly to docker hub
```
./mvnw clean package -Dmaven.test.skip=true -Dquarkus.container-image.push=true
```

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `quarkus-kafka-vanilla-1.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/quarkus-kafka-vanilla-1.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/quarkus-kafka-vanilla-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.

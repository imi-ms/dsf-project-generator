# DSF Project Generator

The DSF ([Data Sharing Framework](https://dsf.dev)) Project Generator is born within the [DSF Spring School 2026](https://dsf.dev/posts/2026-03-18-spring-school-2026-recap.html), to speed up DSF process plugin development.
This DSF-Community tool is based on [Java](https://www.java.com), [Mustache }}](https://mustache.github.io/) and [JavaFX](https://openjfx.io/).

## Prerequisite

Download [OpenJDK JDK 25 or later](https://openjdk.org/projects/jdk/) for your operating system. Make sure `JAVA_HOME` is properly set to the JDK installation directory.

## Build and Run

If you run on Linux, Mac or Windows, follow these steps:

    cd dsf-project-generator

To compile and run the project:

    mvn clean javafx:run

## Usage

The DSF Process Tutorial [Prerequesites](https://github.com/datasharingframework/dsf-process-tutorial/blob/main/exercises/prerequisites.md) and [Exercise 0](https://github.com/datasharingframework/dsf-process-tutorial/blob/main/exercises/exercise-0.md) can be used as a starting point, if you are not familiar with the DSF.

### Linux

It could be, that some created folders need special write permissions.
This is true for the keycloak db under `./dev-setup/keycloak/data/h2` so give it write permissions
like so: 

```bash
sudo chmod 777 ./dev-setup/keycloak/data/h2
```

## License
All code is published under the [Apache-2.0 License](LICENSE).

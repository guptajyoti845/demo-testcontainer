# Table of Contents

1. **Prerequisites**
2. **Build and Run**
3. **Testing with Testcontainers**
4. **Commands**

This project demonstrates a Spring Boot application using PostgreSQL, Flyway for database
migrations, and Testcontainers for integration testing. The application includes a simple student
management system with APIs to create and retrieve student records.

**Prerequisites**

Before you begin, ensure you have the following installed:

1. JDK 17 or higher
2. Gradle
3. Docker (required for Testcontainers)
4. For macOS with M1 chip users, additional setup is required for Docker compatibility.


**Build and Run**

1. Clone the repository

`git clone <repository-url> && cd <repository-directory>`

2. Configure Flyway
   Update the flyway configuration in build.gradle with your PostgreSQL credentials:

`flyway {
url = 'jdbc:postgresql://localhost:5432/quickdemo'
user = 'your-username'
password = 'your-password'
schemas = ["public"]
locations = ['filesystem:src/main/resources/db/migration']
}`

3. Build the project
   `./gradlew clean build`

4. Run the application
   `./gradlew bootRun`

The application will start on http://localhost:8080.

**Testing with Testcontainers**

This project uses Testcontainers to run integration tests with a PostgreSQL database in a Docker
container.

1. Start Colima (for macOS with M1 chip)

If you are using macOS with an M1 chip, you need to start Colima and set the Docker socket override:

`colima start`

**For M1 Mac users**

`export TESTCONTAINERS_DOCKER_SOCKET_OVERRIDE=/var/run/docker.sock
export DOCKER_HOST="unix://${HOME}/.colima/docker.sock"
sudo ln -s $HOME/.colima/default/docker.sock /var/run/docker.sock`

2. Run the tests
   `./gradlew clean test`

The test suite includes integration tests that use Testcontainers to spin up a PostgreSQL container,
ensuring isolation and a consistent test environment.

**Commands**

Build the project: `./gradlew clean build`

Run the application: `./gradlew bootRun`

Run tests: `./gradlew clean test`


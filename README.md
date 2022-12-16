# Blogosphere Web Services

### Build & Run Docker containers:
- `cd blog-web-api`
- `./gradlew clean build`
- `docker-compose up --build -d`

### Access Open API reference at:

`localhost:8080/swagger-ui.html`

### Authentication with default User:
![request](https://user-images.githubusercontent.com/39915225/208146968-5c39e4c7-ece6-4a33-b289-edeb3afc3e56.png)

### Access Token response after successful authentication:
![response](https://user-images.githubusercontent.com/39915225/208146997-eee3aecd-b5a2-4fb1-8a3f-3e1c6087000c.png)

Use the response token when creating new requests. 

### Karate Automation Tests: `Post` entity example:
![karate](https://user-images.githubusercontent.com/39915225/207652757-91b470e3-ea66-461d-a677-505ccce66838.png)

This is a sample of karate automation testing for Posts features. 
The file is generated on build subfolder `build/karate-reports/karate-summary.html`

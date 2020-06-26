# basket-service

Open in Java IDE
Wait for the dependencies to be satisfied
Run as Java application or "mvn install" then "java -jar target/basket-0.0.1-SNAPSHOT.jar"

Go to http://localhost:3000/swagger-ui.html for the Rest API documentation

Create customer first at http://localhost:3000/api/public/register with POST method, see swagger ui for request body format.\
Login at http://localhost:3000/api/public/login with POST method, see swagger ui for request body format.\
A token will be provided so you can try the Rest APIs, pass the token in the Authorization Bearer.


NOTE: 'whenAdded' and 'whenUpdated' json properties doesn't need to be in the request body

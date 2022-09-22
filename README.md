# forum
Projeto teste de kotlin web

mvn package

docker build -t forum -f Dockerfile .

docker run -p 3080:8080 forum

heroku create

heroku git:remote -a calm-eyrie-85192

heroku container:login

heroku container:push web

heroku container:release web

***************

docker pull mysql:8.0.28

docker run -d -p 3306:3306 --name mysql-container -e MYSQL_ROOT_PASSWORD=root -e MYSQL_PASSWORD=root mysql:8.0.28

docker exec -it mysql-container bash

mysql -u root -p

create database forum;

**************
Swagger:

http://localhost:8080/swagger-ui/index.html

**************

docker pull redis:latest

docker run --name redis-local redis:latest

docker exec -it redis sh

redis-cli

monitor

***************

https://mailtrap.io/




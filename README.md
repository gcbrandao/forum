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




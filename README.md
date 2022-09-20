# forum
Projeto teste de kotlin web

mvn package

docker build -t forum -f Dockerfile .

docker run -p 3080:8080 forum

# Deploy

## Criar imagem angular 

${code}
cd scrum-project/scrum-angular 
docker build -t scrum-angular:latest  .    
$/{}

com isso você ira criar uma imagem da ultima versão do aplicativo


## Criar imagem Spring


${code}
mvn install
docker build --build-arg JAR_FILE=$(ls target/*.jar) -t scrum-spring .
$/{}

## Criar Imagem Banco

${code}
docker build --pull  "dockerfile" -t banco:latest "." 
$/{}


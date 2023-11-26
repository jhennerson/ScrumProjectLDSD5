# IFSP-Scrum

Um projeto para gerenciamento de equipes usando a metodologia Scrum


O projeto tem como propósito simular a vivencia de uma equipe agil desenvolvendo uma solução para gerenciamento de equipes. Projeto feito durante as aulas de láboratorio de desenvolvimento do curso de graduação do Instituto federal de São Paulo.


![](https://img.shields.io/badge/TypeScript-007ACC?style=for-the-badge&logo=typescript&logoColor=white)
![](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)
![](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![](https://img.shields.io/badge/Shell_Script-121011?style=for-the-badge&logo=gnu-bash&logoColor=white)
![](https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white)
![](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![](https://img.shields.io/badge/json%20web%20tokens-323330?style=for-the-badge&logo=json-web-tokens&logoColor=pink)

# Começando

O projeto divide em 2 aplicações principais sendo a 

 - Scrum Angular
 - Scrum Spring

sendo respectivamente uma aplicação para acessar a plataforma pelo site e a outra o backend responsavel por processar e persistir os dados no banco de dados


## Spring Angular


Um site onde o usuario pode fazer o cadastro e gerenciar sua equipe podendo criar sprints , tarefas ,atribuir elas entre outros ritos das metodologias ageis.
A aplicação funciona consumindo os serviços entregues pelo backend 


### Pré-requisitos

- Node.js | v20^
- Npm     | v10.1.0

### Iniciar ambiente de desenvolvimento

é importante lembrar que a aplicação consome os serviços do backend , sendo então necessario que a instancia do Scrum Spring esteja sendo executada e devidamente configurada.

Clone o projeto na sua maquina


Acesse o diretório scrum-angular

```bash 
cd scrum-project/scrum-angular
```

Para instalar as dependencias do projeto use o comando abaixo:

```bash
npm install --legacy-peer-dep
```

e para iniciar o ambiente de desenvolvimento na sua maquina utilize o comando:

```bash
npm start
```

## Spring Spring

Uma API REST que processa as requisições vinda do aplicativo WEB

### Pré-requisitos 

Maven | 3.6.3

### Iniciar o ambiente de desenvolvimento

Para iniciar o ambiente de desenvolvimento primeiro é importante de lembrar que o banco de dados deve estar previamente configurado, utilizando qualquer IDE , basta entrar no diretorio  da aplicação  a classe principal do projeto.

```bash
cd scrum-project/scrum-spring/
```



### Containers

O projeto tem 3 imagens para ser geradas 
- O Banco de dados Postgre
- A Api Rest 
- Aplicação web angular


cada container pode ser montado individualmente porém existe um docker-compose para subir todos os containers de uma vez



**Atenção antes é necessario gerar uma versão da API-REST**

```bash
cd scrum-project/scrum-spring
mvn clean package install
```


Use o comando abaixo na raiz do projeto para criar as imagens
```bash
docker compose build 
```

e para subir os containers:
```bash
docker compose up -d
```




## Autores

- **Augusto Souza**      - *Desenvolvimento* - [Perfil](https://github.com/Augusto-S01)
- **Jhennerson Barbosa** - *Desenvolvimento* - [Perfil](https://github.com/jhennerson)
- **Guilherme Pereira**  - *Desenvolvimento* - [Perfil](https://github.com/Guilherme-ap)
- **Lorrayne Santos**    - *Desenvolvimento* - [Perfil](https://github.com/LorrayneSaraiva)
- **Claudiane**          - *Desenvolvimento* - [Perfil]() 
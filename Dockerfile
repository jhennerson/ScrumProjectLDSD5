# Use a imagem oficial do PostgreSQL como base
FROM postgres:latest

# Defina as variáveis de ambiente para o PostgreSQL
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD password

# Copie o arquivo de inicialização do banco de dados para o diretório de inicialização
COPY ./init.sql /docker-entrypoint-initdb.d/

# Crie um volume para persistir os dados do banco de dados
VOLUME /var/lib/postgresql/data

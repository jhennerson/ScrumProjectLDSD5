-- Criação da enumeração para o status
CREATE TYPE Status AS ENUM ('to do', 'in progress', 'done');

-- Criação da tabela Usuario (Usuário)
CREATE TABLE Usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

-- Criação da tabela Sprint
CREATE TABLE Sprint (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL
);

-- Criação da tabela Backlog com chave estrangeira para Sprint
CREATE TABLE Backlog (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    sprint_id INT NOT NULL,
    assignmentDate DATE,
    endDate DATE,
    FOREIGN KEY (sprint_id) REFERENCES Sprint(id)
);

-- Criação da tabela Task com chave estrangeira para Backlog
CREATE TABLE Task (
    id INT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    user_id INT,
    assignmentDate DATE,
    endDate DATE,
    effort INT,
    description TEXT,
    status VARCHAR(255) NOT NULL,
    backlog_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Usuario(id),
    FOREIGN KEY (backlog_id) REFERENCES Backlog(id)
);
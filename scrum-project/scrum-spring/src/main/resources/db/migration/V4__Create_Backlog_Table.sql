CREATE TABLE Backlog (
                         id SERIAL PRIMARY KEY,
                         title VARCHAR(255) NOT NULL,
                         sprint_id INT NOT NULL,
                         assignmentDate DATE,
                         endDate DATE,
                         FOREIGN KEY (sprint_id) REFERENCES Sprint(id)
);
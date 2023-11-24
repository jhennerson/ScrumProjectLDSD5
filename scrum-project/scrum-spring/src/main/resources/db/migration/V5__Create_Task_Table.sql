CREATE TABLE Task (
                      id SERIAL PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      user_id INT,
                      assignmentDate DATE,
                      endDate DATE,
                      effort INT,
                      description TEXT,
                      status Status NOT NULL,
                      backlog_id INT NOT NULL,
                      FOREIGN KEY (user_id) REFERENCES Usuario(id),
                      FOREIGN KEY (backlog_id) REFERENCES Backlog(id)
);
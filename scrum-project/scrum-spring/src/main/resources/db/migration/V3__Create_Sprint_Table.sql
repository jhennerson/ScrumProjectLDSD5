CREATE TABLE Sprint (
                        id SERIAL PRIMARY KEY,
                        title VARCHAR(255) NOT NULL,
                        status Status NOT NULL
);
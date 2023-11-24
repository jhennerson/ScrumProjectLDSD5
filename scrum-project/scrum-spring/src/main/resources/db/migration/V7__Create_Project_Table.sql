CREATE TABLE project (
                         id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                         title VARCHAR(255) NOT NULL,
                         assignment_date DATE,
                         end_date DATE,
                         assignee_id UUID REFERENCES users(id),
                         deleted BOOLEAN DEFAULT false
);
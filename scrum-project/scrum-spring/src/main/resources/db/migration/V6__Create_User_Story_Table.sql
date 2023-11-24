CREATE TABLE user_stories (
                              id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                              title VARCHAR(255) NOT NULL,
                              assignee_id UUID REFERENCES users(id),
                              reporter_id UUID REFERENCES users(id),
                              description TEXT,
                              deleted BOOLEAN DEFAULT false
);
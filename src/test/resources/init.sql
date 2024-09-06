CREATE TABLE IF NOT EXISTS secret_key (
    id SERIAL PRIMARY KEY,
    value VARCHAR(20) NOT NULL
);

INSERT INTO secret_key (value) VALUES ('ABCdef456789!@#$%^&)');
INSERT INTO secret_key (value) VALUES ('KLMnop987654%^&*()!@');
INSERT INTO secret_key (value) VALUES ('ZXCvbn123450!@#$$#()');

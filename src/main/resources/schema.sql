CREATE TABLE IF NOT EXISTS account (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS quote (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    content VARCHAR(1000),
    score BIGINT,
    created TIMESTAMP,
    updated TIMESTAMP,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES account(id)
);

CREATE TABLE IF NOT EXISTS vote (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    type VARCHAR(20) NOT NULL,
    quote_id BIGINT,
    user_id BIGINT,
    FOREIGN KEY (quote_id) REFERENCES quote(id),
    FOREIGN KEY (user_id) REFERENCES account(id)
);

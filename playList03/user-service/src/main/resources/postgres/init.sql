CREATE TABLE IF NOT EXISTS users (
                                     id SERIAL PRIMARY KEY,
                                     name VARCHAR(255) NOT NULL,
    balance INTEGER NOT NULL
    );

CREATE TABLE IF NOT EXISTS user_transactions (
                                                 id SERIAL PRIMARY KEY,
                                                 user_id INTEGER NOT NULL REFERENCES users(id),
    amount INTEGER NOT NULL,
    transaction_date TIMESTAMP NOT NULL
    );

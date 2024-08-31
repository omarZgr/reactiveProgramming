-- init.sql

-- Create the 'users' table if it does not exist
CREATE TABLE IF NOT EXISTS users (
                                     id SERIAL PRIMARY KEY,
                                     name VARCHAR(255) NOT NULL,
    balance INTEGER NOT NULL
    );

-- Create the 'user_transactions' table if it does not exist
CREATE TABLE IF NOT EXISTS user_transactions (
                                                 id SERIAL PRIMARY KEY,
                                                 user_id INTEGER NOT NULL,
                                                 amount INTEGER NOT NULL,
                                                 transaction_date TIMESTAMP NOT NULL,
                                                 FOREIGN KEY (user_id) REFERENCES users(id)
    );

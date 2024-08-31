CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT,
                       name VARCHAR(50),
                       balance INT,
                       PRIMARY KEY (id)
);

CREATE TABLE user_transaction (
                                  id BIGINT AUTO_INCREMENT,
                                  user_id BIGINT,
                                  amount INT,
                                  transaction_date TIMESTAMP,
                                  PRIMARY KEY (id),
                                  FOREIGN KEY (user_id) REFERENCES users(id)
);

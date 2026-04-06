CREATE TABLE refresh_token (
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    token      VARCHAR(255) NOT NULL UNIQUE,
    user_id    BIGINT       NOT NULL UNIQUE,
    expires_at DATETIME(6)  NOT NULL,
    CONSTRAINT fk_refresh_token_user FOREIGN KEY (user_id) REFERENCES app_user (id)
);

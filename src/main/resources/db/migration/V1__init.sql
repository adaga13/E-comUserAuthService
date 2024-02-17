CREATE TABLE ecom_user
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    created_at        datetime NULL,
    last_updated_at   datetime NULL,
    is_deleted        BIT(1) NULL,
    name              VARCHAR(255) NULL,
    email             VARCHAR(255) NULL,
    hashed_password   VARCHAR(255) NULL,
    is_email_verified BIT(1) NOT NULL,
    CONSTRAINT pk_ecomuser PRIMARY KEY (id)
);

CREATE TABLE ecom_user_roles
(
    ecom_user_id BIGINT NOT NULL,
    roles_id     BIGINT NOT NULL
);

CREATE TABLE `role`
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    created_at      datetime NULL,
    last_updated_at datetime NULL,
    is_deleted      BIT(1) NULL,
    name            VARCHAR(255) NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE token
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    created_at      datetime NULL,
    last_updated_at datetime NULL,
    is_deleted      BIT(1) NULL,
    value           VARCHAR(255) NULL,
    expiry_at       datetime NULL,
    user_id         BIGINT NULL,
    CONSTRAINT pk_token PRIMARY KEY (id)
);

ALTER TABLE token
    ADD CONSTRAINT FK_TOKEN_ON_USER FOREIGN KEY (user_id) REFERENCES ecom_user (id);

ALTER TABLE ecom_user_roles
    ADD CONSTRAINT fk_ecouserol_on_ecom_user FOREIGN KEY (ecom_user_id) REFERENCES ecom_user (id);

ALTER TABLE ecom_user_roles
    ADD CONSTRAINT fk_ecouserol_on_role FOREIGN KEY (roles_id) REFERENCES `role` (id);
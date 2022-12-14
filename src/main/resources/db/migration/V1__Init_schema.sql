CREATE TABLE roles
(
    id         VARCHAR(36)  NOT NULL PRIMARY KEY,
    created_at DATETIME(6)  NOT NULL,
    updated_at DATETIME(6)  NOT NULL,
    name       VARCHAR(255) NOT NULL
);

CREATE TABLE users
(
    id            VARCHAR(36)  NOT NULL PRIMARY KEY,
    created_at    DATETIME(6)  NOT NULL,
    updated_at    DATETIME(6)  NOT NULL,
    username      VARCHAR(255) NOT NULL,
    email         VARCHAR(255) NOT NULL,
    first_name    VARCHAR(255) NOT NULL,
    last_name     VARCHAR(255) NOT NULL,
    password      VARCHAR(255) NOT NULL,
    phone_number  VARCHAR(255),
    gender        VARCHAR(255),
    date_of_birth DATE,
    city          VARCHAR(255),
    country       VARCHAR(255),
    zip_code      VARCHAR(255),
    website       VARCHAR(255),
    CONSTRAINT UKmflljq3quxankee539h1vjk74
        UNIQUE (email, phone_number, username)
);

CREATE TABLE users_roles
(
    user_id VARCHAR(36) NOT NULL,
    role_id VARCHAR(36) NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT FK2o0jvgh89lemvvo17cbqvdxaa
        FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT FKj6m8fwv7oqv74fcehir1a9ffy
        FOREIGN KEY (role_id) REFERENCES roles (id)
);

INSERT INTO roles
VALUES ('6c03d2dd-1df1-4f92-bcd2-ab7076c31531', NOW(), NOW(), 'USER'),
       ('4096f65c-c903-4e56-8e8b-545921606121', NOW(), NOW(), 'ADMIN');

INSERT INTO users
VALUES ('d6436e3b-717d-4c8f-8a0c-6e5820f494ae', NOW(), NOW(), 'admin', 'John.doe@gmail.com', 'John',
        'Doe', '$2a$10$E8qDaJXdKlQZUj5iK1ned.vuOqp0RHiAG9bEvXzOH8VnXCbvXJt6K', '+38349123123', 'MALE',
        '1990-07-07', 'London', 'England', '56273', 'www.john-doe.com');

INSERT INTO users_roles
VALUES ('d6436e3b-717d-4c8f-8a0c-6e5820f494ae', '4096f65c-c903-4e56-8e8b-545921606121');
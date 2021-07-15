-- set timezone, encoding
SET global time_zone = '+0:00';
SET NAMES utf8mb4;

DROP TABLE IF EXISTS task;

DROP TABLE IF EXISTS category;

DROP TABLE IF EXISTS priority;

DROP TABLE IF EXISTS roles;

DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id                bigint PRIMARY KEY      NOT NULL AUTO_INCREMENT,
    name              VARCHAR(40)             NOT NULL,
    email             VARCHAR(40)             NOT NULL,
    password          VARCHAR(40)             NOT NULL,
    registered        TIMESTAMP DEFAULT now() NOT NULL,
    enabled           BOOL      DEFAULT TRUE  NOT NULL,
    completed_total   bigint    DEFAULT 0,
    uncompleted_total bigint    DEFAULT 0
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE roles
(
    user_id bigint NOT NULL,
    role    VARCHAR(20),
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);


CREATE TABLE category
(
    id                bigint      NOT NULL AUTO_INCREMENT,
    user_id           bigint      NOT NULL,
    title             varchar(45) NOT NULL,
    completed_count   bigint DEFAULT 0,
    uncompleted_count bigint DEFAULT 0,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    KEY index_title (title) INVISIBLE
);

CREATE TABLE priority
(
    id    bigint      NOT NULL AUTO_INCREMENT,
    title varchar(45) NOT NULL,
    color varchar(45) NOT NULL,
    PRIMARY KEY (id),
    KEY index_title (title)
);

CREATE TABLE task
(
    id          bigint       NOT NULL AUTO_INCREMENT,
    user_id     bigint       NOT NULL,
    title       varchar(100) NOT NULL,
    completed   int      DEFAULT 0,
    date        datetime DEFAULT NULL,
    priority_id bigint   DEFAULT NULL,
    category_id bigint   DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    KEY fk_category_idx (category_id),
    KEY fk_priority_idx (priority_id),
    KEY index_title (title),
    KEY index_completed (completed),
    KEY index_date (date),
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE SET NULL ON UPDATE RESTRICT,
    CONSTRAINT fk_priority FOREIGN KEY (priority_id) REFERENCES priority (id) ON DELETE SET NULL ON UPDATE RESTRICT
);

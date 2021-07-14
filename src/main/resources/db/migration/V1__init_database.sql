-- set timezone, encoding
SET global_timezone = '+0:00';
SET NAMES utf8;

DROP TABLE IF EXISTS 'category';

DROP TABLE IF EXISTS 'priority';

DROP TABLE IF EXISTS 'stat';

DROP TABLE IF EXISTS 'task';



CREATE TABLE 'category'
(
    'id'                bigint      NOT NULL AUTO_INCREMENT,
    'title'             varchar(45) NOT NULL,
    'completed_count'   bigint DEFAULT '0',
    'uncompleted_count' bigint DEFAULT '0',
    PRIMARY KEY (`id`),
    KEY 'index_title' ('title') INVISIBLE */
);

CREATE TABLE 'priority'
(
    'id'    bigint      NOT NULL AUTO_INCREMENT,
    'title' varchar(45) NOT NULL,
    'color' varchar(45) NOT NULL,
    PRIMARY KEY ('id'),
    KEY 'index_title' ('title')
);

CREATE TABLE 'stat'
(
    `id`                bigint NOT NULL AUTO_INCREMENT,
    `completed_total`   bigint DEFAULT '0',
    `uncompleted_total` bigint DEFAULT '0',
    PRIMARY KEY (`id`)
);

CREATE TABLE 'task'
(
    'id'          bigint                                                  NOT NULL AUTO_INCREMENT,
    'title'       varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    'completed'   int      DEFAULT '0',
    'date'        datetime DEFAULT NULL,
    'priority_id' bigint   DEFAULT NULL,
    'category_id' bigint   DEFAULT NULL,
    PRIMARY KEY ('id'),
    KEY 'fk_category_idx' ('category_id'),
    KEY 'fk_priority_idx' ('priority_id'),
    KEY 'index_title' ('title'),
    KEY 'index_completed' ('completed'),
    KEY 'index_date' ('date'),
    CONSTRAINT 'fk_category' FOREIGN KEY ('category_id') REFERENCES 'category' ('id') ON DELETE SET NULL ON UPDATE RESTRICT,
    CONSTRAINT 'fk_priority' FOREIGN KEY ('priority_id') REFERENCES 'priority' ('id') ON DELETE SET NULL ON UPDATE RESTRICT
);




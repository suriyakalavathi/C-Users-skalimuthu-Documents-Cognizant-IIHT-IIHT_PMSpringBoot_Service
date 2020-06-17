DROP TABLE IF EXISTS `pm`.`user`;
DROP TABLE IF EXISTS `pm`.`task`;
DROP TABLE IF EXISTS `pm`.`parent_task`;
DROP TABLE IF EXISTS `pm`.`project`;

DROP DATABASE IF EXISTS `pm`;

CREATE DATABASE IF NOT EXISTS `pm`;

USE `pm`;

CREATE TABLE IF NOT EXISTS `pm`.`project` (
    project_id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(32) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    priority INT NOT NULL,
    PRIMARY KEY (project_id)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS `pm`.`parent_task` (
    parent_task_id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(32) NOT NULL,
    PRIMARY KEY (parent_task_id)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS `pm`.`task` (
    task_id BIGINT NOT NULL AUTO_INCREMENT,
    parent_task_id BIGINT,
    project_id BIGINT NOT NULL,
    name VARCHAR(32) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    priority INT NOT NULL,
    status BOOLEAN NOT NULL,
    PRIMARY KEY (task_id),
    FOREIGN KEY (parent_task_id) REFERENCES `pm`.`parent_task`(parent_task_id) ON DELETE CASCADE,
    FOREIGN KEY (project_id) REFERENCES `pm`.`project`(project_id) ON DELETE CASCADE
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS `pm`.`user` (
    user_id BIGINT NOT NULL AUTO_INCREMENT,
    employee_id BIGINT NOT NULL,
    first_name VARCHAR(32) NOT NULL,
    last_name VARCHAR(32) NOT NULL,
    project_id BIGINT,
    task_id BIGINT,
    PRIMARY KEY (user_id),
    FOREIGN KEY (project_id) REFERENCES `pm`.`project`(project_id) ON DELETE SET NULL,
    FOREIGN KEY (task_id) REFERENCES `pm`.`task`(task_id) ON DELETE SET NULL
) ENGINE=INNODB;

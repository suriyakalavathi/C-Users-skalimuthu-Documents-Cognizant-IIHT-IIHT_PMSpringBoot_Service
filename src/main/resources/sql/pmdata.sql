-- Data to use during development

INSERT INTO `pm`.`project` (name, start_date, end_date, priority) VALUES ('DisburmentProject', '2020-05-01', '2020-11-30', 4);
INSERT INTO `pm`.`project` (name, start_date, end_date, priority) VALUES ('SalesProject', '2020-06-02', '2020-12-31', 8);

INSERT INTO `pm`.`parent_task` (name) VALUES ('Development');
INSERT INTO `pm`.`parent_task` (name) VALUES ('Stage');

INSERT INTO `pm`.`task` (project_id, name, start_date, end_date, priority, status) VALUES (1, 'Requirements', '2020-05-01', '2020-11-30', 1, TRUE);
INSERT INTO `pm`.`task` (parent_task_id, project_id, name, start_date, end_date, priority, status) VALUES (1, 1, 'DisburmentProject Build', CURDATE(), '2020-11-30', 3, FALSE);
INSERT INTO `pm`.`task` (parent_task_id, project_id, name, start_date, end_date, priority, status) VALUES (1, 1, 'DisburmentProject Testing', '2020-07-01', '2020-12-30', 6, FALSE);
INSERT INTO `pm`.`task` (parent_task_id, project_id, name, start_date, end_date, priority, status) VALUES (2, 1, 'SalesProject Build', '2020-06-15', '2020-07-31', 9, FALSE);
INSERT INTO `pm`.`task` (parent_task_id, project_id, name, start_date, end_date, priority, status) VALUES (2, 1, 'SalesProject Testing', '2020-06-30', '2020-12-31', 12, FALSE);
INSERT INTO `pm`.`task` (project_id, name, start_date, end_date, priority, status) VALUES (1, 'Release', '2020-08-01', '2020-11-30', 1, FALSE);

INSERT INTO `pm`.`task` (project_id, name, start_date, end_date, priority, status) VALUES (2, 'Requirements', '2020-05-01', '2020-11-30', 1, TRUE);
INSERT INTO `pm`.`task` (parent_task_id, project_id, name, start_date, end_date, priority, status) VALUES (1, 2, 'DisburmentProject Build', CURDATE(), '2020-11-30', 3, FALSE);
INSERT INTO `pm`.`task` (parent_task_id, project_id, name, start_date, end_date, priority, status) VALUES (1, 2, 'DisburmentProject Testing', '2020-07-01', '2020-12-30', 6, FALSE);
INSERT INTO `pm`.`task` (parent_task_id, project_id, name, start_date, end_date, priority, status) VALUES (2, 2, 'SalesProject Build', '2020-06-15', '2020-07-31', 9, FALSE);
INSERT INTO `pm`.`task` (parent_task_id, project_id, name, start_date, end_date, priority, status) VALUES (2, 2, 'SalesProject Testing', '2020-06-30', '2020-12-31', 12, FALSE);
INSERT INTO `pm`.`task` (project_id, name, start_date, end_date, priority, status) VALUES (2, 'Release', '2020-08-01', '2020-11-30', 1, FALSE);

INSERT INTO `pm`.`user` (employee_id, first_name, last_name, project_id) VALUES (1, 'Aeron', 'Sam', 1);
INSERT INTO `pm`.`user` (employee_id, first_name, last_name, project_id) VALUES (2, 'Baby', 'Cute', 2);
INSERT INTO `pm`.`user` (employee_id, first_name, last_name) VALUES (3, 'Carol', 'Love');
INSERT INTO `pm`.`user` (employee_id, first_name, last_name, task_id) VALUES (4, 'Duke', 'Energy', 2);
INSERT INTO `pm`.`user` (employee_id, first_name, last_name,task_id) VALUES (4, 'English', 'Man', 8);
INSERT INTO `pm`.`user` (employee_id, first_name, last_name,task_id) VALUES (5, 'Epic', 'Source', 3);


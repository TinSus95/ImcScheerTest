#   DB INIT VALUES

INSERT INTO `learning_component_type` (`id`,`name`)
VALUES
    (1, 'Course'),
    (2, 'Media'),
    (3, 'Programme')
ON DUPLICATE KEY UPDATE `name` = VALUES(`name`);

INSERT INTO `user_status` (`id`,`name`)
VALUES
    (1, 'Booked'),
    (2, 'InProgress'),
    (3, 'Completed')
ON DUPLICATE KEY UPDATE `name` = VALUES(`name`);

INSERT INTO `meta_tag` (`id`,`name`)
VALUES
    (1, 'Scrum'),
    (2, 'Agile'),
    (3, 'Project Management'),
    (4, 'Framework')
ON DUPLICATE KEY UPDATE `name` = VALUES(`name`);

INSERT IGNORE INTO `user` (`id`, `username`, `password`)
VALUES
    (1, 'admin', 'root'),
    (2, 'user_1', 'test'),
    (3, 'user_2', 'test');

INSERT IGNORE INTO `learning_component` (`id`, `name`, `description`, `category`, `type_id`, `image_url`, `duration`)
VALUES
    ('eLearningComponentId123', 'Introduction to Scrum', 'This course provides a comprehensive introduction to the Scrum framework...', 'Software Development', 1, 'http://example.com/images/scrum_course.jpg', 8),
    ('eLearningComponentId456', 'Agile Methodologies Overview Video', 'Video about Agile methodologies', 'Software Development',2, 'http://example.com/images/agile_video.png', 4),
    ('eLearningComponentId789', 'Kanban Overview', 'Kanban programme', 'Software Development', 3, 'http://example.com/images/kanban_overview.png', 1);

INSERT IGNORE INTO `component_tag` (`component_id`, `tag_id`)
VALUES
    ('eLearningComponentId123', 1),
    ('eLearningComponentId123', 2),
    ('eLearningComponentId123', 3),
    ('eLearningComponentId123', 4),
    ('eLearningComponentId456', 1),
    ('eLearningComponentId456', 2),
    ('eLearningComponentId456', 3),
    ('eLearningComponentId456', 4),
    ('eLearningComponentId789', 3);


INSERT IGNORE INTO `user_learning` (`user_id`, `component_id`, `status_id`, `start_date`, `end_date`)
VALUES
    (1, 'eLearningComponentId123', 1, NOW(), date_add(NOW(), INTERVAL 1 YEAR)),
    (1, 'eLearningComponentId456', 2, NOW(), date_add(NOW(), INTERVAL 1 YEAR)),
    (2, 'eLearningComponentId789', 3, NOW(), date_add(NOW(), INTERVAL 1 YEAR)),
    (3, 'eLearningComponentId123', 3, NOW(), null);
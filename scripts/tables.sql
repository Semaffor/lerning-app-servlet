CREATE TABLE `course`
(
    `id`                      bigint NOT NULL AUTO_INCREMENT,
    `title`                   varchar(90)  DEFAULT NULL,
    `description`             varchar(255) DEFAULT NULL,
    `duration`                int          DEFAULT NULL,
    `technology`              int          DEFAULT NULL,
    `course_format`           int          DEFAULT NULL,
    `current_pupils_quantity` int          DEFAULT NULL,
    `max_pupils_quantity`     int          DEFAULT NULL,
    `is_active`               tinyint      DEFAULT '1',
    `is_deleted`              tinyint      DEFAULT '0',
    `couch_id`                bigint       DEFAULT NULL,
    `photo_uri`               varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `title_UNIQUE` (`title`),
    KEY                       `course_user_fk` (`couch_id`),
    CONSTRAINT `course_user_fk` FOREIGN KEY (`couch_id`) REFERENCES `user` (`id`)
);

CREATE TABLE `task`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT,
    `title`       varchar(90) DEFAULT NULL,
    `description` varchar(255) NOT NULL,
    `course_id`   bigint      DEFAULT NULL,
    `deadline`    datetime    DEFAULT NULL,
    `is_deleted`  tinyint     DEFAULT '0',
    PRIMARY KEY (`id`),
    UNIQUE KEY `task_title_uindex` (`title`),
    KEY           `task_course_fk` (`course_id`),
    CONSTRAINT `task_course_fk` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
);

CREATE TABLE `user`
(
    `id`         bigint      NOT NULL AUTO_INCREMENT,
    `username`   varchar(45) NOT NULL,
    `password`   varchar(45) NOT NULL,
    `role`       int     DEFAULT '2',
    `is_blocked` tinyint DEFAULT '0',
    `is_deleted` tinyint DEFAULT '0',
    PRIMARY KEY (`id`)
);

CREATE TABLE `user_course`
(
    `id`         bigint NOT NULL AUTO_INCREMENT,
    `user_id`    bigint NOT NULL,
    `course_id`  bigint DEFAULT NULL,
    `start_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `is_deleted` tinyint(1) DEFAULT '0',
    PRIMARY KEY (`id`),
    UNIQUE KEY `composite_key` (`user_id`,`course_id`),
    KEY          `user-course_course__fk` (`course_id`),
    CONSTRAINT `user-course_course__fk` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
    CONSTRAINT `user-course_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

CREATE TABLE `user_task`
(
    `id`             bigint    NOT NULL AUTO_INCREMENT,
    `user_id`        bigint       DEFAULT NULL,
    `task_id`        bigint       DEFAULT NULL,
    `solution`       varchar(255) DEFAULT NULL,
    `mark`           int          DEFAULT NULL,
    `feedback`       varchar(255) DEFAULT NULL,
    `submitted_date` timestamp NOT NULL,
    `check_date`     timestamp NULL,
    `is_deleted`     tinyint      DEFAULT '0',
    PRIMARY KEY (`id`),
    KEY              `user-task_task__fk` (`task_id`),
    KEY              `user-task_user__fk` (`user_id`),
    CONSTRAINT `user-task_task__fk` FOREIGN KEY (`task_id`) REFERENCES `task` (`id`),
    CONSTRAINT `user-task_user__fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);
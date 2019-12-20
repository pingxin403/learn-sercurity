CREATE TABLE `t_user`
(
  `id`       int          NOT NULL COMMENT '用户id',
  `username` varchar(64)  NOT NULL,
  `password` varchar(64)  NOT NULL,
  `fullname` varchar(255) NOT NULL COMMENT '用户姓名',
  `mobile`   varchar(11) DEFAULT NULL COMMENT '手机号',
  primary key (`id`)
);
CREATE TABLE `t_role`
(
  `id`          varchar(32) NOT NULL,
  `role_name`   varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `create_time` datetime     DEFAULT NULL,
  `update_time` datetime     DEFAULT NULL,
  `status`      char(1)     NOT NULL,
  PRIMARY KEY (`id`)
);
CREATE TABLE `t_user_role`
(
  `id`          int NOT NULL,
  `user_id`     int NOT NULL,
  `role_id`     int NOT NULL,
  `create_time` datetime     DEFAULT NULL,
  `creator`     varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `t_permission`
(
  `id`          int         NOT NULL,
  `code`        varchar(32) NOT NULL COMMENT '权限标识符',
  `description` varchar(64)  DEFAULT NULL COMMENT '描述',
  `url`         varchar(128) DEFAULT NULL COMMENT '请求地址',
  PRIMARY KEY (`id`)
);

CREATE TABLE `t_role_permission`
(
  `id`            int NOT NULL,
  `role_id`       int NOT NULL,
  `permission_id` int NOT NULL,
  PRIMARY KEY (`id`)
);

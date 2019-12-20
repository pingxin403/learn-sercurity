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


DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`
(
  `id`                      integer,
  `client_id`               varchar(255) NOT NULL COMMENT '客户端标
识',
  `resource_ids`            varchar(255) NULL     DEFAULT NULL
    COMMENT '接入资源列表',
  `client_secret`           varchar(255) NULL     DEFAULT NULL
    COMMENT '客户端秘钥',
  `scope`                   varchar(255) NULL     DEFAULT NULL,
  `authorized_grant_types`  varchar(255) NULL     DEFAULT
                                                    NULL,
  `web_server_redirect_uri` varchar(255) NULL     DEFAULT
                                                    NULL,
  `authorities`             varchar(255) NULL     DEFAULT NULL,
  `access_token_validity`   int(11)      NULL     DEFAULT NULL,
  `refresh_token_validity`  int(11)      NULL     DEFAULT NULL,
  `additional_information`  longtext,
  `create_time`             timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE
    CURRENT_TIMESTAMP(0),
  `archived`                tinyint(4)   NULL     DEFAULT NULL,
  `trusted`                 tinyint(4)   NULL     DEFAULT NULL,
  `autoapprove`             varchar(255) NULL     DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code`
(
  `id`             integer auto_increment,
  `create_time`    timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `code`           varchar(255) NULL     DEFAULT NULL,
  `authentication` blob         NULL,
  primary key (`id`)
);
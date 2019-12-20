insert into t_user
values (1, 'zhangsan', '$2a$10$1b5mIkehqv5c4KRrX9bUj.A4Y2hug3IGCnMCL5i4RpQrYV12xNKye', '张三', '13811114444');

-- insert into t_user
-- values (2, 'lisi', '$2a$10$1b5mIkehqv5c4KRrX9bUj.A4Y2hug3IGCnMCL5i4RpQrYV12xNKye', '李四', '13811114444');

insert
into `t_role`(`id`, `role_name`, `description`, `create_time`, `update_time`, `status`)
values (1, '管理员', NULL, NULL, NULL, '');

insert
into `t_user_role`(`id`, `user_id`, `role_id`, `create_time`, `creator`)
values (1, 1, 1, NULL, NULL);

insert
into `t_permission`(`id`, `code`, `description`, `url`)
values (1, 'p1', '测试资源1', '/r/r1'),
       (2, 'p2', '测试资源2', '/r/r2');

insert
into `t_role_permission`(`id`, `role_id`, `permission_id`)
values (1, 1, 1),
       (2, 1, 2);

INSERT INTO `oauth_client_details`
VALUES (1, 'c1', 'res1',
        '$2a$10$NlBC84MVb7F95EXYTXwLneXgCca6/GipyWR5NHm8K0203bSQMLpvm', 'ROLE_ADMIN,ROLE_USER,ROLE_API',
        'client_credentials,password,authorization_code,implicit,refresh_token', 'http://www.baidu.com',
        NULL, 7200, 259200, NULL, CURRENT_TIMESTAMP(0), 0, 0, 'false');
INSERT INTO `oauth_client_details`
VALUES (2, 'c2', 'res2',
        '$2a$10$NlBC84MVb7F95EXYTXwLneXgCca6/GipyWR5NHm8K0203bSQMLpvm', 'ROLE_API',
        'client_credentials,password,authorization_code,implicit,refresh_token', 'http://www.baidu.com',
        NULL, 31536000, 2592000, NULL, CURRENT_TIMESTAMP(0), 0, 0, 'false');

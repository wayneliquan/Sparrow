DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user
(
  id           BIGSERIAL              NOT NULL,
  username     CHARACTER VARYING(255) NOT NULL,
  password     CHARACTER VARYING(255) NOT NULL,
  nickname     CHARACTER VARYING(255),
  status       INTEGER                        DEFAULT 0,
  date_created TIMESTAMP(3) WITHOUT TIME ZONE DEFAULT now(),
  CONSTRAINT sys_user_pkey PRIMARY KEY (id)

);

DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role
(
  id           BIGSERIAL              NOT NULL,
  name         CHARACTER VARYING(255) NOT NULL,
  code         CHARACTER VARYING(255) NOT NULL,
  weight       CHARACTER VARYING(255)         DEFAULT 0,
  type         INTEGER                NOT NULL,
  date_modify  TIMESTAMP(3) WITHOUT TIME ZONE,
  date_created TIMESTAMP(3) WITHOUT TIME ZONE DEFAULT now(),
  CONSTRAINT sys_role_pkey PRIMARY KEY (id)

);

DROP TABLE IF EXISTS sys_resource;
CREATE TABLE sys_resource
(
  id           BIGSERIAL                      NOT NULL,
  pid          BIGSERIAL                      NOT NULL,
  url          CHARACTER VARYING(8000)        NOT NULL,
  icon         CHARACTER VARYING(255),
  weight       CHARACTER VARYING(255)         NOT NULL DEFAULT 0,
  code         CHARACTER VARYING(255),
  type         INTEGER,
  level        INTEGER,
  name         CHARACTER VARYING(255)         NOT NULL,
  pname        CHARACTER VARYING(255),
  status       INTEGER                        DEFAULT 0,
  unique_code  CHARACTER VARYING(255),
  date_modify  TIMESTAMP(3) WITHOUT TIME ZONE,
  date_created TIMESTAMP(3) WITHOUT TIME ZONE DEFAULT now(),
  CONSTRAINT sys_resource_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS user_role;
CREATE TABLE user_role
(
  id           BIGSERIAL NOT NULL,
  user_id      BIGINT    NOT NULL,
  role_id      BIGINT    NOT NULL,
  status       INTEGER                        DEFAULT 0,
  date_modify  TIMESTAMP(3) WITHOUT TIME ZONE,
  date_created TIMESTAMP(3) WITHOUT TIME ZONE DEFAULT now(),
  CONSTRAINT user_role_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS role_resource;
CREATE TABLE role_resource
(
  id           BIGSERIAL              NOT NULL,
  role_id      BIGINT                 NOT NULL,
  role_code    CHARACTER VARYING(255) NOT NULL,
  resource_id  BIGINT                 NOT NULL,
  resource_url CHARACTER VARYING(255) NOT NULL,
  date_modify  TIMESTAMP(3) WITHOUT TIME ZONE,
  date_created TIMESTAMP(3) WITHOUT TIME ZONE DEFAULT now(),
  CONSTRAINT role_resource_pkey PRIMARY KEY (id)
);

-- sys_user 插入需要保证 id为1,2
INSERT INTO "sys_user" ("username", "password", "nickname", "status") VALUES ('admin', '21232f297a57a5a743894a0e4a801fc3', '管理员', '0');
INSERT INTO "sys_user" ("username", "password", "nickname", "status") VALUES ('user', '202cb962ac59075b964b07152d234b70', NULL, '0');

-- sys_role 插入需要保证 id为1,2
INSERT INTO "sys_role" ("name", "code", "weight", "type") VALUES ('系统管理员', 'ROLE_SYS_ADMIN', '1', '1');
INSERT INTO "sys_role" ("name", "code", "weight", "type") VALUES ('用户', 'ROLE_USER', '2', '4');


-- sys_user 插入需要保证 id为1,2
INSERT INTO "user_role" ("user_id", "role_id") VALUES ('1', '1');
INSERT INTO "user_role" ("user_id", "role_id") VALUES ('2', '2');


INSERT INTO "sys_resource" ("pid", "url", "icon", "weight", "code", "type", "level", "name", "pname", "status", "unique_code") VALUES ( '0', '/', NULL, '1', '', '1', '1', '系统管理', NULL, '0', NULL);
INSERT INTO "sys_resource" ("pid", "url", "icon", "weight", "code", "type", "level", "name", "pname", "status", "unique_code") VALUES ( '1', '/sysResource/list', NULL, '2', 'SYS', '1', '2', '资源管理', NULL, '0', NULL);
INSERT INTO "sys_resource" ("pid", "url", "icon", "weight", "code", "type", "level", "name", "pname", "status", "unique_code") VALUES ( '1', '/sysRole/list', NULL, '1', 'SYS', '1', '2', '角色管理', NULL, '0', NULL);


INSERT INTO "role_resource" ("role_id", "role_code", "resource_id", "resource_url") VALUES ('2', 'ROLE_USER', '1', '/');
INSERT INTO "role_resource" ("role_id", "role_code", "resource_id", "resource_url") VALUES ('2', 'ROLE_USER', '3', '/sysRole/list');
INSERT INTO "role_resource" ("role_id", "role_code", "resource_id", "resource_url") VALUES ('1', 'ROLE_SYS_ADMIN', '1', '/');
INSERT INTO "role_resource" ("role_id", "role_code", "resource_id", "resource_url") VALUES ('1', 'ROLE_SYS_ADMIN', '2', '/sysResource/list');
INSERT INTO "role_resource" ("role_id", "role_code", "resource_id", "resource_url") VALUES ('1', 'ROLE_SYS_ADMIN', '3', '/sysRole/list');
INSERT INTO "role_resource" ("role_id", "role_code", "resource_id", "resource_url") VALUES ('1', 'ROLE_SYS_ADMIN', '1', '/');


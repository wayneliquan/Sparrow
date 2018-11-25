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
  resource_id  BIGINT                 NOT NULL,
  date_modify  TIMESTAMP(3) WITHOUT TIME ZONE,
  date_created TIMESTAMP(3) WITHOUT TIME ZONE DEFAULT now(),
  CONSTRAINT role_resource_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS area;
CREATE TABLE area
(
  area_id      BIGSERIAL NOT NULL,
  code         CHARACTER VARYING(10),
  name         CHARACTER VARYING(30),
  level        INTEGER,
  sort         INTEGER,
  postal_code  CHARACTER VARYING(10),
  area_pid     BIGINT,
  status       SMALLINT,
  date_modify  TIMESTAMP(3) WITHOUT TIME ZONE,
  date_created TIMESTAMP(3) WITHOUT TIME ZONE DEFAULT now(),
  CONSTRAINT area_pkey PRIMARY KEY (area_id)
);

-- sys_user 插入需要保证 id为1,2
INSERT INTO "sys_user" ("username", "password", "nickname", "status") VALUES ('admin', '21232f297a57a5a743894a0e4a801fc3', '管理员', '0');
INSERT INTO "sys_user" ("username", "password", "nickname", "status") VALUES ('user', '202cb962ac59075b964b07152d234b70', NULL, '0');
INSERT INTO "sys_user" ("username", "password", "nickname", "status") VALUES ('test', '202cb962ac59075b964b07152d234b70', NULL, '0');

-- sys_role 插入需要保证 id为1,2
INSERT INTO "sys_role" ("name", "code", "weight", "type") VALUES ('系统管理员', 'ROLE_SYS_ADMIN', '1', '1');
INSERT INTO "sys_role" ("name", "code", "weight", "type") VALUES ('用户', 'ROLE_USER', '2', '4');
INSERT INTO "sys_role" ("name", "code", "weight", "type") VALUES ('TEST', 'ROLE_TEST', '4', '4');


-- user_role 插入需要保证 id为1,2
INSERT INTO "user_role" ("user_id", "role_id") VALUES ('1', '1');
INSERT INTO "user_role" ("user_id", "role_id") VALUES ('2', '2');
INSERT INTO "user_role" ("user_id", "role_id") VALUES ('2', '3');



INSERT INTO "sys_resource" ("pid", "url", "icon", "weight", "code", "type", "level", "name", "pname", "status", "unique_code")
VALUES 
( '0', '/', NULL, '1', '', '1', '1', '系统管理', NULL, '0', NULL),
( '1', '/sysResource/list', NULL, '2', 'SYS', '1', '2', '资源管理', NULL, '0', NULL),
( '1', '/sysRole/list', NULL, '2', 'SYS', '1', '2', '角色管理', NULL, '0', NULL),
( '1', '/sysUser/list', NULL, '2', 'SYS', '1', '2', '用户管理', NULL, '0', NULL),
( '1', '/device/list', NULL, '5', 'DEVICE', '1', '2', 'License管理', NULL, '0', NULL),
( '1', '/license/list', NULL, '6', 'LICENSE', '1', '2', 'License管理', NULL, '0', NULL),
( '1', '/deviceOta/list', NULL, '7', 'DEVICE', '1', '2', '设备OTA信息', NULL, '0', NULL),
( '1', '/deviceTest/list', NULL, '8', 'DEVICE', '1', '2', '测试数据上传', NULL, '0', NULL);

INSERT INTO "role_resource" ("role_id", "resource_id") 
VALUES
('1', '1'),
('1', '2'),
('1', '3'),
('1', '4'),
('1', '5'),
('1', '6'),
('1', '7'),
('1', '8'),
('1', '9'),
('2', '1'),
('2', '2'),
('2', '5'),
('2', '6'),
('2', '7'),
('2', '8'),
('3', '1'),
('3', '8');



DROP TABLE IF EXISTS apk_info;
CREATE TABLE apk_info
(
  apk_info_id   BIGSERIAL NOT NULL,
  apk_name CHARACTER VARYING(100),
  package_name CHARACTER VARYING(100),
  version_name CHARACTER VARYING(30),
  download_url CHARACTER VARYING(8000),
  file_id  BIGINT,
  date_modify  TIMESTAMP(3) WITHOUT TIME ZONE,
  date_created TIMESTAMP(3) WITHOUT TIME ZONE DEFAULT now(),
  CONSTRAINT apk_info_pk PRIMARY KEY (apk_info_id)
);


DROP TABLE IF EXISTS device_info;
CREATE TABLE device_info
(
  device_info_id   BIGSERIAL NOT NULL,
  uuid CHARACTER VARYING(40),
  serialno CHARACTER VARYING(100),
  name CHARACTER VARYING(30),
  brand CHARACTER VARYING(8000),
  model  BIGINT,
  sdk INTEGER,
  release  CHARACTER VARYING(100),
  width_pixels  INTEGER,
  height_pixels INTEGER,
  date_modify  TIMESTAMP(3) WITHOUT TIME ZONE,
  date_created TIMESTAMP(3) WITHOUT TIME ZONE DEFAULT now(),
  CONSTRAINT device_info_pk PRIMARY KEY (device_info_id)
);

DROP TABLE IF EXISTS autoread_script;
CREATE TABLE autoread_script
(
  autoread_script_id   BIGSERIAL NOT NULL,
  apk_info_id BIGINT,
  width_pixels  INTEGER,
  height_pixels INTEGER,
  script CHARACTER VARYING(8000),
  date_modify  TIMESTAMP(3) WITHOUT TIME ZONE,
  date_created TIMESTAMP(3) WITHOUT TIME ZONE DEFAULT now(),
  CONSTRAINT autoread_script_pk PRIMARY KEY (autoread_script_id)
);



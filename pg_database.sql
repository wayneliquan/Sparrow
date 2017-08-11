DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user
(
  id           BIGSERIAL                      NOT NULL,
  username     CHARACTER VARYING(255)         NOT NULL,
  password     CHARACTER VARYING(255)         NOT NULL,
  nickname     CHARACTER VARYING(255),
  status       INTEGER                        NOT NULL DEFAULT 0,
  date_created TIMESTAMP(3) WITHOUT TIME ZONE NOT NULL DEFAULT now(),
  CONSTRAINT sys_user_pkey PRIMARY KEY (id)

);

DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role
(
  id           BIGSERIAL                      NOT NULL,
  name         CHARACTER VARYING(255)         NOT NULL,
  code         CHARACTER VARYING(255)         NOT NULL,
  weight       CHARACTER VARYING(255)         NOT NULL DEFAULT 0,
  type         INTEGER                        NOT NULL,
  date_modify  TIMESTAMP(3) WITHOUT TIME ZONE,
  date_created TIMESTAMP(3) WITHOUT TIME ZONE NOT NULL,
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

  unique_code  CHARACTER VARYING(255),
  status       INTEGER                        NOT NULL DEFAULT 0,
  date_modify  TIMESTAMP(3) WITHOUT TIME ZONE,
  date_created TIMESTAMP(3) WITHOUT TIME ZONE NOT NULL,
  CONSTRAINT sys_resource_pkey PRIMARY KEY (id)

);

DROP TABLE IF EXISTS user_role;
CREATE TABLE user_role
(
  id           BIGSERIAL                      NOT NULL,
  user_id      BIGINT                         NOT NULL,
  role_id      BIGINT                         NOT NULL,
  status       INTEGER                        NOT NULL DEFAULT 0,
  date_modify  TIMESTAMP(3) WITHOUT TIME ZONE,
  date_created TIMESTAMP(3) WITHOUT TIME ZONE NOT NULL,
  CONSTRAINT user_role_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS role_resource;
CREATE TABLE role_resource
(
  id           BIGSERIAL                      NOT NULL,
  role_id      BIGINT                         NOT NULL,
  role_code    CHARACTER VARYING(255)         NOT NULL,
  resource_id  BIGINT                         NOT NULL,
  resource_url BIGINT                         NOT NULL,
  date_modify  TIMESTAMP(3) WITHOUT TIME ZONE,
  date_created TIMESTAMP(3) WITHOUT TIME ZONE NOT NULL,
  CONSTRAINT role_resource_pkey PRIMARY KEY (id)
);
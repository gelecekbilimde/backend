create table if not exists "user"
(
  id             varchar(36)       not null primary key,
  avatar_path    varchar(255),
  biography      text,
  birth_date     date,
  degree         varchar(30),
  email          varchar(255) not null,
  gender         varchar(25),
  lastname       varchar(25)  not null,
  name           varchar(25)  not null,
  password       varchar(255) not null,
  status         varchar(25) not null,
  role_id        varchar(36),
  create_user_id varchar(36),
  update_user_id varchar(36),
  created_at     timestamp,
  updated_at     timestamp,
  CONSTRAINT check_user_status CHECK ( status in ('WAIT','VERIFY','BLOCKED'))
  );


create table if not exists comments
(
  id             bigserial  not null primary key,
  message        text    not null,
  like_count     integer null default 0,
  status         varchar(25) not null,
  create_user_id varchar(36),
  update_user_id varchar(36),
  created_at     timestamp,
  updated_at     timestamp,
  CONSTRAINT check_comments_status CHECK ( status in ('DONE','DELETED'))
  );


create table if not exists notification_token
(
  id             bigserial not null primary key,
  create_at      timestamp,
  device_id      varchar(255),
  device_token   text,
  user_id        varchar(36),
  create_user_id varchar(36),
  update_user_id varchar(36),
  created_at     timestamp,
  updated_at     timestamp,
  CONSTRAINT fk_notification_token_user_user_id FOREIGN KEY (user_id) REFERENCES "user" (id)
  );


create table if not exists permission
(
  id             varchar(36)       not null primary key,
  description    varchar(255) not null,
  name           varchar(255) not null,
  is_hidden         boolean,
  status         varchar(25),
  create_user_id varchar(36),
  update_user_id varchar(36),
  created_at     timestamp,
  updated_at     timestamp,
  CONSTRAINT check_permission_status CHECK ( status in ('ACTIVE','PASSIVE'))

  );


create table if not exists role
(
  id             varchar(36)    not null primary key,
  description    varchar(255),
  is_default     boolean default false,
  name           varchar(55) not null,
  is_hidden      boolean,
  status         varchar(25),
  create_user_id varchar(36),
  update_user_id varchar(36),
  created_at     timestamp,
  updated_at     timestamp,
  CONSTRAINT unique_role_name unique (name),
  CONSTRAINT check_role_status CHECK ( status in ('ACTIVE','PASSIVE'))
  );


create table if not exists role_permission
(
  role_id        varchar(36) not null,
  permission_id  varchar(36) not null,
  primary key (role_id, permission_id),
  CONSTRAINT fk_role_permission_role_role_id FOREIGN KEY (role_id) REFERENCES role (id),
  CONSTRAINT fk_role_permission_permission_permission_id FOREIGN KEY (permission_id) REFERENCES permission (id)
  );



create table if not exists user_followers
(
  follower_user_id varchar(36) not null,
  followed_user_id varchar(36) not null,
  created_at       timestamp,
  updated_at       timestamp,
  primary key (follower_user_id, followed_user_id),
  CONSTRAINT fk_user_followers_follewer_user_id_user_id FOREIGN KEY (follower_user_id) REFERENCES "user" (id),
  CONSTRAINT fk_user_followers_follewed_user_id_user_id FOREIGN KEY (followed_user_id) REFERENCES "user" (id)
  );


create table if not exists media_group
(
  id             bigserial not null primary key,
  name           varchar(50),
  parent_id      bigint,
  user_id        varchar(36),
  status         varchar(25),
  create_user_id varchar(36),
  update_user_id varchar(36),
  created_at     timestamp,
  updated_at     timestamp,
  CONSTRAINT check_role_status CHECK ( status in ('ACTIVE','DELETED'))

  );


create table if not exists media
(
  id             bigserial             not null primary key,
  media_type     varchar(25)           not null,
  is_shared      boolean default false not null,
  title          varchar(100)          not null,
  content_type   varchar(25)           not null,
  url            varchar(255)          not null,
  user_id        varchar(36),
  group_id       bigint,
  status         varchar(25),
  create_user_id varchar(36),
  update_user_id varchar(36),
  created_at     timestamp,
  updated_at     timestamp,
  CONSTRAINT fk_media_media_group_id FOREIGN KEY (group_id) REFERENCES media_group (id),
  CONSTRAINT fk_media_user_id FOREIGN KEY (user_id) REFERENCES "user" (id)
  );


create table if not exists post
(
  id                varchar(36)  not null primary key,
  header            varchar(255) not null,
  content           text         not null,
  like_count        integer      null default 0,
  slug              varchar(255) not null,
  is_active         boolean           default false,
  copyright_control boolean           default false,
  typo_control      boolean           default false,
  dangerous_control boolean           default false,
  last_process      varchar(25),
  user_id           varchar(36),
  status            varchar(25),
  created_at        timestamp,
  CONSTRAINT fk_post_user_id FOREIGN KEY (user_id) REFERENCES "user" (id),
  CONSTRAINT check_post_status CHECK ( status in ('ACTIVE','PASSIVE'))

  );


create table if not exists post_comments
(
  post_id        varchar(36) not null,
  comment_id     bigint not null,
  primary key (post_id, comment_id),
  CONSTRAINT fk_post_comment_post_id FOREIGN KEY (post_id) REFERENCES post (id),
  CONSTRAINT fk_post_comment_comments_id FOREIGN KEY (comment_id) REFERENCES comments (id)
  );


create table if not exists posts_media
(
  id             bigserial not null primary key,
  cover          boolean,
  media_id       bigint,
  post_id        varchar(36),
  user_id        varchar(36),
  status         varchar(25),
  create_user_id varchar(36),
  update_user_id varchar(36),
  created_at     timestamp,
  updated_at     timestamp,
  CONSTRAINT fk_posts_media_post_id FOREIGN KEY (post_id) REFERENCES post (id),
  CONSTRAINT fk_posts_media_media_id FOREIGN KEY (media_id) REFERENCES media (id),
  CONSTRAINT fk_posts_media_user_id FOREIGN KEY (user_id) REFERENCES "user" (id),
  CONSTRAINT check_post_status CHECK ( status in ('ACTIVE','PASSIVE'))
  );

create table if not exists posts_process
(
  id                bigserial    not null primary key,
  content           text         not null,
  header            varchar(255) not null,
  message           text,
  post_id           varchar(36),
  process           varchar(25),
  slug              varchar(255) not null,
  user_id           varchar(36),
  copyright_control boolean           default false,
  typo_control      boolean           default false,
  dangerous_control boolean           default false,
  done              boolean,
  create_user_id    varchar(36),
  update_user_id    varchar(36),
  created_at        timestamp,
  updated_at        timestamp,
  CONSTRAINT fk_posts_process_post_id FOREIGN KEY (post_id) REFERENCES post (id),
  CONSTRAINT fk_posts_process_user_id FOREIGN KEY (user_id) REFERENCES "user" (id)
  );

create table if not exists invalid_token
(
  id             bigserial   not null primary key,
  token_id       varchar(36) not null,
  create_user_id varchar(36),
  update_user_id varchar(36),
  created_at     timestamp,
  updated_at     timestamp
  );

create table if not exists settings
(
  id             bigserial not null primary key,
  group_name     varchar(100) not null,
  name           varchar(100) not null,
  definition     text,
  is_hidden      boolean,
  create_user_id varchar(36),
  update_user_id varchar(36),
  created_at     timestamp,
  updated_at     timestamp,
  CONSTRAINT usettings unique (group_name, name)
  );

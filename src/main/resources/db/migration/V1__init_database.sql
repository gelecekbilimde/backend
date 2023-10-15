create table if not exists "user"
(
  id             bigint       not null primary key,
  avatar_path    varchar(255),
  biography      text,
  birth_date     date,
  degree         varchar(30),
  email          varchar(255) not null,
  email_verify   boolean default false,
  gender         varchar(25),
  lastname       varchar(25)  not null,
  name           varchar(25)  not null,
  password       varchar(255) not null,
  user_enable    boolean default true,
  user_lock      boolean default false,
  role_name      varchar(55),
  create_user_id bigint,
  created_at     timestamp
  );

create table if not exists black_list
(
  id             bigint      not null primary key,
  description    varchar(255),
  email          varchar(25) not null,
  create_user_id bigint,
  created_at     timestamp,
  updated_at     timestamp,
  deleted_at     timestamp
  );



create table if not exists comments
(
  id             bigint  not null primary key,
  comment        text    not null,
  like_count     integer null default 0,
  create_user_id bigint,
  created_at     timestamp,
  updated_at     timestamp,
  deleted_at     timestamp
);


create table if not exists notification_token
(
  id             bigint not null primary key,
  create_at      timestamp,
  device_id      varchar(255),
  device_token   text,
  user_id        bigint,
  create_user_id bigint,
  created_at     timestamp,
  updated_at     timestamp,
  deleted_at     timestamp
  );


create table if not exists permission
(
  id             bigint       not null primary key,
  description    varchar(255) not null,
  name           varchar(255) not null,
  create_user_id bigint,
  created_at     timestamp,
  updated_at     timestamp,
  deleted_at     timestamp

  );


create table if not exists role
(
  id             bigint      not null primary key,
  description    varchar(255),
  is_default     boolean default false,
  name           varchar(55) not null,
  create_user_id bigint,
  created_at     timestamp,
  updated_at     timestamp,
  deleted_at     timestamp,
  CONSTRAINT name_unique unique (name)
  );


create table if not exists role_permission
(
  role_id        bigint not null,
  permission_id  bigint not null,
  create_user_id bigint,
  created_at     timestamp,
  updated_at     timestamp,
  deleted_at     timestamp,
  primary key (role_id, permission_id)
  );



create table if not exists follower
(
  follower_user_id bigint not null,
  followed_user_id bigint not null,
  create_user_id   bigint,
  created_at       timestamp,
  updated_at       timestamp,
  deleted_at       timestamp,
  primary key (follower_user_id, followed_user_id)
  );


create table if not exists media_group
(
  id             bigint not null primary key,
  name           varchar(50),
  parent_id      integer,
  user_id        bigint,
  create_user_id bigint,
  created_at     timestamp,
  updated_at     timestamp,
  deleted_at     timestamp
  );


create table if not exists media
(
  id             bigint                not null primary key,
  media_type     varchar(25)           not null,
  is_shared      boolean default false not null,
  title          varchar(100)          not null,
  content_type   varchar(25)           not null,
  url            varchar(255)          not null,
  user_id        bigint,
  group_id       bigint,
  create_user_id bigint,
  created_at     timestamp,
  updated_at     timestamp,
  deleted_at     timestamp
  );


create table if not exists post
(
  id                bigint       not null primary key,
  header            varchar(255) not null,
  content           text         not null,
  like_count        integer      null default 0,
  slug              varchar(255) not null,
  copyright_control boolean           default false,
  typo_control      boolean           default false,
  dangerous_control boolean           default false,
  last_process      varchar(25),
  user_id           bigint,
  is_active         boolean           default false,
  create_at         timestamp
  );


create table if not exists post_comments
(
  post_id        bigint not null,
  comment_id     bigint not null,
  create_user_id bigint,
  created_at     timestamp,
  updated_at     timestamp,
  deleted_at     timestamp,
  primary key (post_id, comment_id)
  );


create table if not exists posts_media
(
  id             bigint not null primary key,
  cover          boolean,
  media_id       bigint,
  post_id        bigint,
  user_id        bigint,
  create_user_id bigint,
  created_at     timestamp,
  updated_at     timestamp,
  deleted_at     timestamp
);

create table if not exists posts_process
(
  id             bigint       not null primary key,
  content        text         not null,
  header         varchar(255) not null,
  message        jsonb,
  post_id        bigint,
  process        varchar(25),
  slug           varchar(255) not null,
  user_id        bigint,
  create_user_id bigint,
  created_at     timestamp,
  updated_at     timestamp,
  deleted_at     timestamp
  );


create table if not exists token
(
  id             bigint not null primary key,
  is_expired     boolean default false,
  is_revoked     boolean default false,
  jwt            text,
  token_type     varchar(255),
  user_id        bigint,
  create_user_id bigint,
  created_at     timestamp,
  updated_at     timestamp,
  deleted_at     timestamp
  );

ALTER TABLE "user"
  ADD CONSTRAINT user_role_fk FOREIGN KEY (role_name) REFERENCES role (name);
ALTER TABLE notification_token
  ADD CONSTRAINT notification_token_user_fk FOREIGN KEY (user_id) REFERENCES "user" (id);

ALTER TABLE role_permission
  ADD CONSTRAINT role_fk FOREIGN KEY (role_id) REFERENCES role (id);
ALTER TABLE role_permission
  ADD CONSTRAINT permission_fk FOREIGN KEY (permission_id) REFERENCES permission (id);

ALTER TABLE follower
  ADD CONSTRAINT follower_fk FOREIGN KEY (follower_user_id) REFERENCES "user" (id);
ALTER TABLE follower
  ADD CONSTRAINT followed_fk FOREIGN KEY (followed_user_id) REFERENCES "user" (id);

ALTER TABLE media
  ADD CONSTRAINT media_group_fk FOREIGN KEY (group_id) REFERENCES media_group (id);
ALTER TABLE media
  ADD CONSTRAINT media_user_fk FOREIGN KEY (user_id) REFERENCES "user" (id);

ALTER TABLE post
  ADD CONSTRAINT post_user_fk FOREIGN KEY (user_id) REFERENCES "user" (id);


ALTER TABLE post_comments
  ADD CONSTRAINT post_comments_post_fk FOREIGN KEY (post_id) REFERENCES post (id);
ALTER TABLE post_comments
  ADD CONSTRAINT post_comments_comment_fk FOREIGN KEY (comment_id) REFERENCES comments (id);


ALTER TABLE posts_media
  ADD CONSTRAINT posts_media_post_fk FOREIGN KEY (post_id) REFERENCES post (id);
ALTER TABLE posts_media
  ADD CONSTRAINT posts_media_media_fk FOREIGN KEY (media_id) REFERENCES media (id);
ALTER TABLE posts_media
  ADD CONSTRAINT posts_media_user_fk FOREIGN KEY (user_id) REFERENCES "user" (id);

ALTER TABLE posts_process
  ADD CONSTRAINT posts_process_post_fk FOREIGN KEY (post_id) REFERENCES post (id);
ALTER TABLE posts_process
  ADD CONSTRAINT posts_process_user_fk FOREIGN KEY (user_id) REFERENCES "user" (id);

create sequence black_list_seq
  increment by 50;

create sequence comments_seq
  increment by 50;

create sequence media_group_seq
  increment by 50;

create sequence media_seq
  increment by 50;

create sequence permission_seq
  increment by 50;

create sequence post_seq
  increment by 50;

create sequence posts_media_seq
  increment by 50;

create sequence posts_process_seq
  increment by 50;

create sequence role_seq
  increment by 50;

create sequence token_seq
  increment by 50;

create sequence user_seq
  increment by 50;

create sequence notification_token_seq
  increment by 50;



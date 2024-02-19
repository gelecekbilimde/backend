create table if not exists user_verification
(
  id             varchar(36) not null primary key,
  user_id        varchar(36) not null
    constraint fk__user_verification__user_id references user (id),
  create_user_id varchar(36) not null,
  update_user_id varchar(36),
  created_at     timestamp   not null,
  updated_at     timestamp
);

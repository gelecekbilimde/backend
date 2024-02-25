create table if not exists user_verification
(
  id             varchar(36) not null primary key,
  user_id        varchar(36) not null,
  status        varchar(25) not null,
  create_user_id varchar(36) not null,
  update_user_id varchar(36),
  created_at     timestamp not null,
  updated_at     timestamp,
  CONSTRAINT fk_user_verification_user_id FOREIGN KEY (user_id) REFERENCES "user" (id)
  );

create table if not exists "confirmation_token"
(
  id             varchar(36)       not null primary key,
  confirmation_token   varchar(255),
  created_date     timestamp,
  user_id         varchar(36),
  CONSTRAINT fk_confirmation_token_user_user_id FOREIGN KEY (user_id) REFERENCES "user" (id)
  );

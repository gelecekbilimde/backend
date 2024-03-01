create table if not exists "post_like"
(
  id         bigserial                                       not null
    constraint pk__post_like__id primary key,
  post_id    varchar(36)
    constraint fk__post_like__post_id references "post" (id) not null,
  user_id    varchar(36)
    constraint fk__post_like__user_id references "user" (id) not null,
  created_at timestamp                                       not null,
  updated_at timestamp,
  constraint unique__post_like__post_id__user_id unique (post_id, user_id)
);

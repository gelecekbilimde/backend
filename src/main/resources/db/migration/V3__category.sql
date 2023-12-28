create table if not exists category
(
  id             bigint generated always as identity primary key,
  parent_id      bigint,
  name           varchar(36) not null,
  create_user_id varchar(36),
  update_user_id varchar(36),
  created_at     timestamp,
  updated_at     timestamp
  );

alter table post add category_id integer;

alter table post add constraint fk_post_category_id foreign key (category_id) references category;

alter table posts_process add category_id integer;

alter table posts_process add constraint fk_posts_process_category_id foreign key (category_id) references category;


create table if not exists category
(
  id             bigint generated always as identity primary key,
  order_number   integer     not null,
  parent_id      bigint,
  name           varchar(36) not null,
  slug           varchar(36) not null,
  icon           varchar(36),
  create_user_id varchar(36) not null,
  update_user_id varchar(36),
  created_at     timestamp   not null,
  updated_at     timestamp
);

alter table post
  add category_id integer;

alter table post
  add constraint fk_post_category_id foreign key (category_id) references category;

alter table posts_process
  add category_id integer;

alter table posts_process
  add constraint fk_posts_process_category_id foreign key (category_id) references category;

insert into category (order_number, parent_id, name, slug, icon, create_user_id, created_at)
values (0, 1, 'Fizik', 'fizik', null, 'gelecekbilimde', current_timestamp);
insert into category (order_number, parent_id, name, slug, icon, create_user_id, created_at)
values (1, 1, 'Biyoloji', 'biyoloji', null, 'gelecekbilimde', current_timestamp);
insert into category (order_number, parent_id, name, slug, icon, create_user_id, created_at)
values (2, 1, 'Kimya', 'kimya', null, 'gelecekbilimde', current_timestamp);
insert into category (order_number, parent_id, name, slug, icon, create_user_id, created_at)
values (1, null, 'Teknoloji', 'teknoloji', 'cpu', 'gelecekbilimde', current_timestamp);
insert into category (order_number, parent_id, name, slug, icon, create_user_id, created_at)
values (2, null, 'Felsefe', 'felsefe', 'book', 'gelecekbilimde', current_timestamp);
insert into category (order_number, parent_id, name, slug, icon, create_user_id, created_at)
values (1, 6, 'Ontoloji', 'ontoloji', null, 'gelecekbilimde', current_timestamp);
insert into category (order_number, parent_id, name, slug, icon, create_user_id, created_at)
values (2, 6, 'Ahlak Felsefesi', 'ahlak-felsefesi', null, 'gelecekbilimde', current_timestamp);
insert into category (order_number, parent_id, name, slug, icon, create_user_id, created_at)
values (0, 6, 'Epistemoloji', 'epistelomoji', null, 'gelecekbilimde', current_timestamp);
insert into category (order_number, parent_id, name, slug, icon, create_user_id, created_at)
values (5, null, 'testCat', 'test-category', 'g', 'gelecekbilimde', current_timestamp);
insert into category (order_number, parent_id, name, slug, icon, create_user_id, created_at)
values (0, null, 'Bilim', 'bilim', 'flask-conical', 'gelecekbilimde', current_timestamp);

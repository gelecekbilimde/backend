create table if not exists category
(
  id             bigint generated always as identity primary key,
  order_number   integer     not null,
  parent_id      bigint,
  name           varchar(36) not null,
  slug           varchar(36) not null,
  icon           varchar(36),
  create_user_id varchar(36),
  update_user_id varchar(36),
  created_at     timestamp,
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

INSERT INTO category (order_number, parent_id, name, slug, icon, create_user_id, update_user_id, created_at, updated_at) VALUES (0, 1, 'Fizik', 'fizik', null, null, null, null, null);
INSERT INTO category (order_number, parent_id, name, slug, icon, create_user_id, update_user_id, created_at, updated_at) VALUES (1, 1, 'Biyoloji', 'biyoloji', null, null, null, null, null);
INSERT INTO category (order_number, parent_id, name, slug, icon, create_user_id, update_user_id, created_at, updated_at) VALUES (2, 1, 'Kimya', 'kimya', null, null, null, null, null);
INSERT INTO category (order_number, parent_id, name, slug, icon, create_user_id, update_user_id, created_at, updated_at) VALUES (1, null, 'Teknoloji', 'teknoloji', 'cpu', null, null, null, null);
INSERT INTO category (order_number, parent_id, name, slug, icon, create_user_id, update_user_id, created_at, updated_at) VALUES (2, null, 'Felsefe', 'felsefe', 'book', null, null, null, null);
INSERT INTO category (order_number, parent_id, name, slug, icon, create_user_id, update_user_id, created_at, updated_at) VALUES (1, 6, 'Ontoloji', 'ontoloji', null, null, null, null, null);
INSERT INTO category (order_number, parent_id, name, slug, icon, create_user_id, update_user_id, created_at, updated_at) VALUES (2, 6, 'Ahlak Felsefesi', 'ahlak-felsefesi', null, null, null, null, null);
INSERT INTO category (order_number, parent_id, name, slug, icon, create_user_id, update_user_id, created_at, updated_at) VALUES (0, 6, 'Epistemoloji', 'epistelomoji', null, null, null, null, null);
INSERT INTO category (order_number, parent_id, name, slug, icon, create_user_id, update_user_id, created_at, updated_at) VALUES (5, null, 'testCat', 'test-category', 'g', null, null, null, null);
INSERT INTO category (order_number, parent_id, name, slug, icon, create_user_id, update_user_id, created_at, updated_at) VALUES (0, null, 'Bilim', 'bilim', 'flask-conical', null, null, null, null);

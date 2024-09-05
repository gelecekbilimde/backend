INSERT INTO role (id, description, name, is_default,is_hidden) VALUES ('4d98a76c-9841-4aea-b296-2f27aa610b6c', 'Yazar', 'AUTHOR', false,false);

INSERT INTO role_permission (role_id, permission_id) VALUES ('4d98a76c-9841-4aea-b296-2f27aa610b6c', '8d365c55-0d60-497b-bb03-8880a7f3c2d8');
INSERT INTO role_permission (role_id, permission_id) VALUES ('4d98a76c-9841-4aea-b296-2f27aa610b6c', '9c032c81-5c0e-4d08-b3a6-e7d524b354cb');
INSERT INTO role_permission (role_id, permission_id) VALUES ('4d98a76c-9841-4aea-b296-2f27aa610b6c', '55e3ef7a-2a1a-4c5b-8d72-3bd1e2c6560b');
INSERT INTO role_permission (role_id, permission_id) VALUES ('4d98a76c-9841-4aea-b296-2f27aa610b6c', '6842cfea-0568-4970-af2e-bc9e3f7ed2cf');
INSERT INTO role_permission (role_id, permission_id) VALUES ('4d98a76c-9841-4aea-b296-2f27aa610b6c', 'd16020f2-1331-4e2f-94aa-7f6d08b6e157');

create table if not exists author_request
(
  id             varchar(36)       not null primary key,
  user_id        varchar(36) unique,
  CONSTRAINT fk_notification_token_user_user_id FOREIGN KEY (user_id) REFERENCES "user" (id)
  );




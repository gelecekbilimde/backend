INSERT INTO gb_role (id, description, name, is_default, is_hidden, created_by, created_at) VALUES ('4d98a76c-9841-4aea-b296-2f27aa610b6c', 'Yazar', 'AUTHOR', false, false, 'gelecekbilimde', current_timestamp);

INSERT INTO gb_role_permission (role_id, permission_id) VALUES ('4d98a76c-9841-4aea-b296-2f27aa610b6c', '8d365c55-0d60-497b-bb03-8880a7f3c2d8'); -- Anasayfa gezer
INSERT INTO gb_role_permission (role_id, permission_id) VALUES ('4d98a76c-9841-4aea-b296-2f27aa610b6c', '9c032c81-5c0e-4d08-b3a6-e7d524b354cb'); -- Gönderi Paylaşır
INSERT INTO gb_role_permission (role_id, permission_id) VALUES ('4d98a76c-9841-4aea-b296-2f27aa610b6c', '55e3ef7a-2a1a-4c5b-8d72-3bd1e2c6560b'); -- Gönderisini Düzenler
INSERT INTO gb_role_permission (role_id, permission_id) VALUES ('4d98a76c-9841-4aea-b296-2f27aa610b6c', '6842cfea-0568-4970-af2e-bc9e3f7ed2cf'); -- Yorum Yapar
INSERT INTO gb_role_permission (role_id, permission_id) VALUES ('4d98a76c-9841-4aea-b296-2f27aa610b6c', 'd16020f2-1331-4e2f-94aa-7f6d08b6e157'); -- Yorum Beğenir

INSERT INTO gb_role (id, description, name, is_default, is_hidden, created_by, created_at) VALUES ('1ed82a25-d348-4576-b4e6-1f2a7c430ca7', 'Moderatör', 'MODERATOR', false, false, 'gelecekbilimde', current_timestamp);

INSERT INTO gb_role_permission (role_id, permission_id) VALUES ('1ed82a25-d348-4576-b4e6-1f2a7c430ca7', '8d365c55-0d60-497b-bb03-8880a7f3c2d8'); -- Anasayfa gezer
INSERT INTO gb_role_permission (role_id, permission_id) VALUES ('1ed82a25-d348-4576-b4e6-1f2a7c430ca7', '9c032c81-5c0e-4d08-b3a6-e7d524b354cb'); -- Gönderi Paylaşır
INSERT INTO gb_role_permission (role_id, permission_id) VALUES ('1ed82a25-d348-4576-b4e6-1f2a7c430ca7', '55e3ef7a-2a1a-4c5b-8d72-3bd1e2c6560b'); -- Gönderisini Düzenler
INSERT INTO gb_role_permission (role_id, permission_id) VALUES ('1ed82a25-d348-4576-b4e6-1f2a7c430ca7', '35bc91b4-3b92-4ff5-b204-439ef21d2d94'); -- Gönderisini Siler
INSERT INTO gb_role_permission (role_id, permission_id) VALUES ('1ed82a25-d348-4576-b4e6-1f2a7c430ca7', '6842cfea-0568-4970-af2e-bc9e3f7ed2cf'); -- Yorum Yapar
INSERT INTO gb_role_permission (role_id, permission_id) VALUES ('1ed82a25-d348-4576-b4e6-1f2a7c430ca7', 'd16020f2-1331-4e2f-94aa-7f6d08b6e157'); -- Yorum Beğenir


create table if not exists gb_role_changes
(
  id                  bigint generated always as identity primary key,
  user_id             varchar(36) unique,
  request_role_name   varchar(36),
  CONSTRAINT fk__gb_author_request__user_id FOREIGN KEY (user_id) REFERENCES gb_user (id)
  );




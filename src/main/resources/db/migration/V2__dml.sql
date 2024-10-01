insert into gb_role (id, description, name, is_default, is_hidden, created_by, created_at)
values ('c147b5c2-87f7-4bb7-a165-368f639d8c3c', 'Admin', 'ADMIN',
        false, true, 'gelecekbilimde', current_timestamp);
insert into gb_role (id, description, name, is_default, is_hidden, created_by, created_at)
values ('e3a1a32d-fcd7-46f0-bb2b-201df6b2b808', 'Sosyal Kullanıcı', 'USER',
        true, false, 'gelecekbilimde', current_timestamp);
insert into gb_role (id, description, name, is_default, is_hidden, created_by, created_at)
values ('4d98a76c-9841-4aea-b296-2f27aa610b6c', 'Yazar', 'AUTHOR',
        false, false, 'gelecekbilimde', current_timestamp);
insert into gb_role (id, description, name, is_default, is_hidden, created_by, created_at)
values ('1ed82a25-d348-4576-b4e6-1f2a7c430ca7', 'Moderatör', 'MODERATOR',
        false, false, 'gelecekbilimde', current_timestamp);



insert into gb_permission (id, description, name, is_hidden, created_by, created_at)
values ('8d365c55-0d60-497b-bb03-8880a7f3c2d8', 'Anasayfa gezer', 'landing:show',
        false, 'gelecekbilimde', current_timestamp);
insert into gb_permission (id, description, name, is_hidden, created_by, created_at)
values ('1a5b4eae-835c-4a8b-95b4-1875b65cf7d9', 'Kullanıcı Ekler', 'user:create',
        false, 'gelecekbilimde', current_timestamp);
insert into gb_permission (id, description, name, is_hidden, created_by, created_at)
values ('3f6f18f3-ba3d-4f3a-b4c3-4681c7e42d6f', 'Kullanıcı Siler', 'user:delete',
        false, 'gelecekbilimde', current_timestamp);
insert into gb_permission (id, description, name, is_hidden, created_by, created_at)
values ('5b27d86c-ae00-49c2-8adc-07ed762920ce', 'Kullanıcı Takip Eder', 'user:follow',
        false, 'gelecekbilimde', current_timestamp);
insert into gb_permission (id, description, name, is_hidden, created_by, created_at)
values ('e7c0ddb6-5371-449f-841e-65b14f53f200', 'Kullanıcı Takip Edeni Görür', 'user:follow:read',
        false, 'gelecekbilimde', current_timestamp);
insert into gb_permission (id, description, name, is_hidden, created_by, created_at)
values ('9c032c81-5c0e-4d08-b3a6-e7d524b354cb', 'Gönderi Paylaşır', 'post:create',
        false, 'gelecekbilimde', current_timestamp);
insert into gb_permission (id, description, name, is_hidden, created_by, created_at)
values ('55e3ef7a-2a1a-4c5b-8d72-3bd1e2c6560b', 'Gönderisini Düzenler', 'post:update',
        false, 'gelecekbilimde', current_timestamp);
insert into gb_permission (id, description, name, is_hidden, created_by, created_at)
values ('35bc91b4-3b92-4ff5-b204-439ef21d2d94', 'Gönderisini Siler', 'post:delete',
        false, 'gelecekbilimde', current_timestamp);
insert into gb_permission (id, description, name, is_hidden, created_by, created_at)
values ('01bc1089-bd27-4b41-bfde-8e63c988fec3', 'Post Like', 'post:like',
        false, 'gelecekbilimde', current_timestamp);
insert into gb_permission (id, description, name, is_hidden, created_by, created_at)
values ('6842cfea-0568-4970-af2e-bc9e3f7ed2cf', 'Yorum Yapar', 'comment:create',
        false, 'gelecekbilimde', current_timestamp);
insert into gb_permission (id, description, name, is_hidden, created_by, created_at)
values ('d16020f2-1331-4e2f-94aa-7f6d08b6e157', 'Yorum Beğenir', 'comment:like',
        false, 'gelecekbilimde', current_timestamp);
insert into gb_permission (id, description, name, is_hidden, created_by, created_at)
values ('b8f1e920-5e4c-4371-aa22-ea1f1f3fcf46', 'Test Versiyonu Görür', 'dev:version',
        false, 'gelecekbilimde', current_timestamp);
insert into gb_permission (id, description, name, is_hidden, created_by, created_at)
values ('836e0a9c-b2bb-4ecf-bf8b-870ea7bb8bc7', 'Admin Paneline erişir', 'admin:access',
        false, 'gelecekbilimde', current_timestamp);
insert into gb_permission (id, description, name, is_hidden, created_by, created_at)
values ('0150a560-8536-427e-8e76-fb1f6d41f5e7', 'Self Ticket Read', 'self:ticket:read',
        false, 'gelecekbilimde', current_timestamp);
insert into gb_permission (id, description, name, is_hidden, created_by, created_at)
values ('b7c4f0d1-3f0e-46aa-9c02-ee7e7cbf49bb', 'All Ticket Read', 'ticket:read',
        false, 'gelecekbilimde', current_timestamp);
insert into gb_permission (id, description, name, is_hidden, created_by, created_at)
values ('1bb69fdf-d470-4c37-8075-58ff0fa0d19e', 'Self Ticket Create', 'self:ticket:create',
        false, 'gelecekbilimde', current_timestamp);
insert into gb_permission (id, description, name, is_hidden, created_by, created_at)
values ('7572d942-b048-40bf-8cf4-c7a87d9be586', 'All Ticket Update', 'ticket:update',
        false, 'gelecekbilimde', current_timestamp);
insert into gb_permission (id, description, name, is_hidden, created_by, created_at)
values ('e6d13971-141e-40d9-be20-43423ac2abce', 'RoleName Change', 'role:change:authority',
        false, 'gelecekbilimde', current_timestamp);
insert into gb_permission (id, description, name, is_hidden, created_by, created_at)
values ('31366d9d-5282-4fe2-9d45-4551e114bc7d', 'Author Request', 'role:request:author',
        false, 'gelecekbilimde', current_timestamp);
insert into gb_permission (id, description, name, is_hidden, created_by, created_at)
values ('251b0a88-d995-4e3c-96b9-97419c78c5af', 'Moderator Request', 'role:request:moderator',
        false, 'gelecekbilimde', current_timestamp);
insert into gb_permission (id, description, name, is_hidden, created_by, created_at)
values ('f8217f8d-8d67-496f-8761-53201e690078', 'Profil Düzenler', 'profile:edit',
        false, 'gelecekbilimde', current_timestamp);


insert into gb_role_permission (role_id, permission_id)
values ('c147b5c2-87f7-4bb7-a165-368f639d8c3c', '1a5b4eae-835c-4a8b-95b4-1875b65cf7d9');
insert into gb_role_permission (role_id, permission_id)
values ('c147b5c2-87f7-4bb7-a165-368f639d8c3c', '8d365c55-0d60-497b-bb03-8880a7f3c2d8');
insert into gb_role_permission (role_id, permission_id)
values ('c147b5c2-87f7-4bb7-a165-368f639d8c3c', '3f6f18f3-ba3d-4f3a-b4c3-4681c7e42d6f');
insert into gb_role_permission (role_id, permission_id)
values ('c147b5c2-87f7-4bb7-a165-368f639d8c3c', '9c032c81-5c0e-4d08-b3a6-e7d524b354cb');
insert into gb_role_permission (role_id, permission_id)
values ('c147b5c2-87f7-4bb7-a165-368f639d8c3c', '55e3ef7a-2a1a-4c5b-8d72-3bd1e2c6560b');
insert into gb_role_permission (role_id, permission_id)
values ('c147b5c2-87f7-4bb7-a165-368f639d8c3c', '35bc91b4-3b92-4ff5-b204-439ef21d2d94');
insert into gb_role_permission (role_id, permission_id)
values ('c147b5c2-87f7-4bb7-a165-368f639d8c3c', 'b8f1e920-5e4c-4371-aa22-ea1f1f3fcf46');
insert into gb_role_permission (role_id, permission_id)
values ('c147b5c2-87f7-4bb7-a165-368f639d8c3c', '836e0a9c-b2bb-4ecf-bf8b-870ea7bb8bc7');
insert into gb_role_permission (role_id, permission_id)
values ('c147b5c2-87f7-4bb7-a165-368f639d8c3c', 'b7c4f0d1-3f0e-46aa-9c02-ee7e7cbf49bb');
insert into gb_role_permission (role_id, permission_id)
values ('c147b5c2-87f7-4bb7-a165-368f639d8c3c', '7572d942-b048-40bf-8cf4-c7a87d9be586');
insert into gb_role_permission (role_id, permission_id)
values ('c147b5c2-87f7-4bb7-a165-368f639d8c3c', 'e6d13971-141e-40d9-be20-43423ac2abce');


INSERT INTO gb_role_permission (role_id, permission_id)
VALUES ('1ed82a25-d348-4576-b4e6-1f2a7c430ca7', '8d365c55-0d60-497b-bb03-8880a7f3c2d8');
INSERT INTO gb_role_permission (role_id, permission_id)
VALUES ('1ed82a25-d348-4576-b4e6-1f2a7c430ca7', '9c032c81-5c0e-4d08-b3a6-e7d524b354cb');
INSERT INTO gb_role_permission (role_id, permission_id)
VALUES ('1ed82a25-d348-4576-b4e6-1f2a7c430ca7', '55e3ef7a-2a1a-4c5b-8d72-3bd1e2c6560b');
INSERT INTO gb_role_permission (role_id, permission_id)
VALUES ('1ed82a25-d348-4576-b4e6-1f2a7c430ca7', '35bc91b4-3b92-4ff5-b204-439ef21d2d94');
INSERT INTO gb_role_permission (role_id, permission_id)
VALUES ('1ed82a25-d348-4576-b4e6-1f2a7c430ca7', '6842cfea-0568-4970-af2e-bc9e3f7ed2cf');
INSERT INTO gb_role_permission (role_id, permission_id)
VALUES ('1ed82a25-d348-4576-b4e6-1f2a7c430ca7', 'd16020f2-1331-4e2f-94aa-7f6d08b6e157');
insert into gb_role_permission (role_id, permission_id)
values ('1ed82a25-d348-4576-b4e6-1f2a7c430ca7', '01bc1089-bd27-4b41-bfde-8e63c988fec3');
insert into gb_role_permission (role_id, permission_id)
values ('1ed82a25-d348-4576-b4e6-1f2a7c430ca7', '0150a560-8536-427e-8e76-fb1f6d41f5e7');
insert into gb_role_permission (role_id, permission_id)
values ('1ed82a25-d348-4576-b4e6-1f2a7c430ca7', '1bb69fdf-d470-4c37-8075-58ff0fa0d19e');
insert into gb_role_permission (role_id, permission_id)
values ('1ed82a25-d348-4576-b4e6-1f2a7c430ca7', '5b27d86c-ae00-49c2-8adc-07ed762920ce');
insert into gb_role_permission (role_id, permission_id)
values ('1ed82a25-d348-4576-b4e6-1f2a7c430ca7', 'e7c0ddb6-5371-449f-841e-65b14f53f200');
insert into gb_role_permission (role_id, permission_id)
values ('1ed82a25-d348-4576-b4e6-1f2a7c430ca7', 'f8217f8d-8d67-496f-8761-53201e690078');


insert into gb_role_permission (role_id, permission_id)
values ('4d98a76c-9841-4aea-b296-2f27aa610b6c', '251b0a88-d995-4e3c-96b9-97419c78c5af');
INSERT INTO gb_role_permission (role_id, permission_id)
VALUES ('4d98a76c-9841-4aea-b296-2f27aa610b6c', '8d365c55-0d60-497b-bb03-8880a7f3c2d8');
INSERT INTO gb_role_permission (role_id, permission_id)
VALUES ('4d98a76c-9841-4aea-b296-2f27aa610b6c', '9c032c81-5c0e-4d08-b3a6-e7d524b354cb');
INSERT INTO gb_role_permission (role_id, permission_id)
VALUES ('4d98a76c-9841-4aea-b296-2f27aa610b6c', '55e3ef7a-2a1a-4c5b-8d72-3bd1e2c6560b');
INSERT INTO gb_role_permission (role_id, permission_id)
VALUES ('4d98a76c-9841-4aea-b296-2f27aa610b6c', '6842cfea-0568-4970-af2e-bc9e3f7ed2cf');
INSERT INTO gb_role_permission (role_id, permission_id)
VALUES ('4d98a76c-9841-4aea-b296-2f27aa610b6c', 'd16020f2-1331-4e2f-94aa-7f6d08b6e157');
insert into gb_role_permission (role_id, permission_id)
values ('4d98a76c-9841-4aea-b296-2f27aa610b6c', '01bc1089-bd27-4b41-bfde-8e63c988fec3');
insert into gb_role_permission (role_id, permission_id)
values ('4d98a76c-9841-4aea-b296-2f27aa610b6c', '0150a560-8536-427e-8e76-fb1f6d41f5e7');
insert into gb_role_permission (role_id, permission_id)
values ('4d98a76c-9841-4aea-b296-2f27aa610b6c', '1bb69fdf-d470-4c37-8075-58ff0fa0d19e');
insert into gb_role_permission (role_id, permission_id)
values ('4d98a76c-9841-4aea-b296-2f27aa610b6c', '5b27d86c-ae00-49c2-8adc-07ed762920ce');
insert into gb_role_permission (role_id, permission_id)
values ('4d98a76c-9841-4aea-b296-2f27aa610b6c', 'e7c0ddb6-5371-449f-841e-65b14f53f200');
insert into gb_role_permission (role_id, permission_id)
values ('4d98a76c-9841-4aea-b296-2f27aa610b6c', 'f8217f8d-8d67-496f-8761-53201e690078');


insert into gb_role_permission (role_id, permission_id)
values ('e3a1a32d-fcd7-46f0-bb2b-201df6b2b808', '8d365c55-0d60-497b-bb03-8880a7f3c2d8');
insert into gb_role_permission (role_id, permission_id)
values ('e3a1a32d-fcd7-46f0-bb2b-201df6b2b808', '01bc1089-bd27-4b41-bfde-8e63c988fec3');
insert into gb_role_permission (role_id, permission_id)
values ('e3a1a32d-fcd7-46f0-bb2b-201df6b2b808', '6842cfea-0568-4970-af2e-bc9e3f7ed2cf');
insert into gb_role_permission (role_id, permission_id)
values ('e3a1a32d-fcd7-46f0-bb2b-201df6b2b808', 'd16020f2-1331-4e2f-94aa-7f6d08b6e157');
insert into gb_role_permission (role_id, permission_id)
values ('e3a1a32d-fcd7-46f0-bb2b-201df6b2b808', '0150a560-8536-427e-8e76-fb1f6d41f5e7');
insert into gb_role_permission (role_id, permission_id)
values ('e3a1a32d-fcd7-46f0-bb2b-201df6b2b808', '1bb69fdf-d470-4c37-8075-58ff0fa0d19e');
insert into gb_role_permission (role_id, permission_id)
values ('e3a1a32d-fcd7-46f0-bb2b-201df6b2b808', '31366d9d-5282-4fe2-9d45-4551e114bc7d');
insert into gb_role_permission (role_id, permission_id)
values ('e3a1a32d-fcd7-46f0-bb2b-201df6b2b808', '5b27d86c-ae00-49c2-8adc-07ed762920ce');
insert into gb_role_permission (role_id, permission_id)
values ('e3a1a32d-fcd7-46f0-bb2b-201df6b2b808', 'e7c0ddb6-5371-449f-841e-65b14f53f200');
insert into gb_role_permission (role_id, permission_id)
values ('e3a1a32d-fcd7-46f0-bb2b-201df6b2b808', 'f8217f8d-8d67-496f-8761-53201e690078');


insert into gb_setting (group_name, name, definition, is_hidden, created_by, created_at)
values ('queues', 'post-queue',
        '{"exchangeName":"post_exchange","type":"direct","durability":true,"queues":[{"type":"master","name":"post_publish","routingKey":"post_publish","maxExec":5,"durability":true},{"type":"success","name":"post_success","routingKey":"post_success","durability":false},{"type":"death","name":"post_death","routingKey":"post_death","durability":true}]}',
        true, 'gelecekbilimde', current_timestamp);


insert into gb_category (order_number, parent_id, name, slug, icon, created_by, created_at)
values (0, 1, 'Fizik', 'fizik', null,
        'gelecekbilimde', current_timestamp);
insert into gb_category (order_number, parent_id, name, slug, icon, created_by, created_at)
values (1, 1, 'Biyoloji', 'biyoloji', null,
        'gelecekbilimde', current_timestamp);
insert into gb_category (order_number, parent_id, name, slug, icon, created_by, created_at)
values (2, 1, 'Kimya', 'kimya', null,
        'gelecekbilimde', current_timestamp);
insert into gb_category (order_number, parent_id, name, slug, icon, created_by, created_at)
values (1, null, 'Teknoloji', 'teknoloji', 'cpu',
        'gelecekbilimde', current_timestamp);
insert into gb_category (order_number, parent_id, name, slug, icon, created_by, created_at)
values (2, null, 'Felsefe', 'felsefe', 'book',
        'gelecekbilimde', current_timestamp);
insert into gb_category (order_number, parent_id, name, slug, icon, created_by, created_at)
values (1, 6, 'Ontoloji', 'ontoloji', null,
        'gelecekbilimde', current_timestamp);
insert into gb_category (order_number, parent_id, name, slug, icon, created_by, created_at)
values (2, 6, 'Ahlak Felsefesi', 'ahlak-felsefesi', null,
        'gelecekbilimde', current_timestamp);
insert into gb_category (order_number, parent_id, name, slug, icon, created_by, created_at)
values (0, 6, 'Epistemoloji', 'epistelomoji', null,
        'gelecekbilimde', current_timestamp);
insert into gb_category (order_number, parent_id, name, slug, icon, created_by, created_at)
values (5, null, 'testCat', 'test-category', 'g',
        'gelecekbilimde', current_timestamp);
insert into gb_category (order_number, parent_id, name, slug, icon, created_by, created_at)
values (0, null, 'Bilim', 'bilim', 'flask-conical',
        'gelecekbilimde', current_timestamp);


insert into gb_user (id, birth_date, email, gender, first_name, last_name, password,
                     status, role_id, created_by, created_at)
values ('22afc9b4-807f-4eb2-b286-788631d1ed55', current_date, 'admin@gelecekbilimde.net',
        'MALE', 'Gelecek', 'Bilimde',
        '$2a$10$atVE.cT5YpEOS7ZLSoVdk.QKVyYBCgvNhvQEuCcXbEMpohYIjbZDG', 'VERIFIED',
        'c147b5c2-87f7-4bb7-a165-368f639d8c3c', 'gelecekbilimde', current_timestamp);

insert into gb_user (id, birth_date, email, gender, first_name, last_name, password,
                     status, role_id, created_by, created_at)
values ('d22e5b1f-d01e-4987-baf8-ed29ec4fa6cf', current_date, 'user@gelecekbilimde.net',
        'MALE', 'Gelecek', 'Bilimde',
        '$2a$10$atVE.cT5YpEOS7ZLSoVdk.QKVyYBCgvNhvQEuCcXbEMpohYIjbZDG', 'VERIFIED',
        'e3a1a32d-fcd7-46f0-bb2b-201df6b2b808', 'gelecekbilimde', current_timestamp);

insert into gb_role (id, description, name, is_default, is_hidden, created_by, created_at)
values ('4dd2d4db-6e4f-4ec6-a1a6-9f94a68c2e24', 'Ziyaretçi', 'GUEST',
        false, false, 'gelecekbilimde', current_timestamp);
insert into gb_role (id, description, name, is_default, is_hidden, created_by, created_at)
values ('c147b5c2-87f7-4bb7-a165-368f639d8c3c', 'Admin', 'ADMIN',
        false, true, 'gelecekbilimde', current_timestamp);
insert into gb_role (id, description, name, is_default, is_hidden, created_by, created_at)
values ('e3a1a32d-fcd7-46f0-bb2b-201df6b2b808', 'Sosyal Kullanıcı', 'USER',
        true, false, 'gelecekbilimde', current_timestamp);

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
values ('9c032c81-5c0e-4d08-b3a6-e7d524b354cb', 'Gönderi Paylaşır', 'post:create',
        false, 'gelecekbilimde', current_timestamp);
insert into gb_permission (id, description, name, is_hidden, created_by, created_at)
values ('55e3ef7a-2a1a-4c5b-8d72-3bd1e2c6560b', 'Gönderisini Düzenler', 'post:update',
        false, 'gelecekbilimde', current_timestamp);
insert into gb_permission (id, description, name, is_hidden, created_by, created_at)
values ('35bc91b4-3b92-4ff5-b204-439ef21d2d94', 'Gönderisini Siler', 'post:delete',
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


insert into gb_role_permission (role_id, permission_id)
values ('4dd2d4db-6e4f-4ec6-a1a6-9f94a68c2e24', '8d365c55-0d60-497b-bb03-8880a7f3c2d8');
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
values ('e3a1a32d-fcd7-46f0-bb2b-201df6b2b808', '8d365c55-0d60-497b-bb03-8880a7f3c2d8');
insert into gb_role_permission (role_id, permission_id)
values ('e3a1a32d-fcd7-46f0-bb2b-201df6b2b808', '9c032c81-5c0e-4d08-b3a6-e7d524b354cb');
insert into gb_role_permission (role_id, permission_id)
values ('e3a1a32d-fcd7-46f0-bb2b-201df6b2b808', '55e3ef7a-2a1a-4c5b-8d72-3bd1e2c6560b');
insert into gb_role_permission (role_id, permission_id)
values ('e3a1a32d-fcd7-46f0-bb2b-201df6b2b808', '35bc91b4-3b92-4ff5-b204-439ef21d2d94');
insert into gb_role_permission (role_id, permission_id)
values ('e3a1a32d-fcd7-46f0-bb2b-201df6b2b808', '6842cfea-0568-4970-af2e-bc9e3f7ed2cf');
insert into gb_role_permission (role_id, permission_id)
values ('e3a1a32d-fcd7-46f0-bb2b-201df6b2b808', 'd16020f2-1331-4e2f-94aa-7f6d08b6e157');
insert into gb_role_permission (role_id, permission_id)
values ('c147b5c2-87f7-4bb7-a165-368f639d8c3c', 'b8f1e920-5e4c-4371-aa22-ea1f1f3fcf46');
insert into gb_role_permission (role_id, permission_id)
values ('c147b5c2-87f7-4bb7-a165-368f639d8c3c', '836e0a9c-b2bb-4ecf-bf8b-870ea7bb8bc7');
insert into gb_role_permission (role_id, permission_id)
values ('e3a1a32d-fcd7-46f0-bb2b-201df6b2b808', '0150a560-8536-427e-8e76-fb1f6d41f5e7');
insert into gb_role_permission (role_id, permission_id)
values ('e3a1a32d-fcd7-46f0-bb2b-201df6b2b808', '1bb69fdf-d470-4c37-8075-58ff0fa0d19e');
insert into gb_role_permission (role_id, permission_id)
values ('c147b5c2-87f7-4bb7-a165-368f639d8c3c', 'b7c4f0d1-3f0e-46aa-9c02-ee7e7cbf49bb');
insert into gb_role_permission (role_id, permission_id)
values ('c147b5c2-87f7-4bb7-a165-368f639d8c3c', '7572d942-b048-40bf-8cf4-c7a87d9be586');

insert into gb_setting (group_name, name, definition, is_hidden, created_by, created_at)
values ('queues', 'post-queue',
        '{"exchangeName":"post_exchange","type":"direct","durability":true,"queues":[{"type":"master","name":"post_publish","routingKey":"post_publish","maxExec":5,"durability":true},{"type":"success","name":"post_success","routingKey":"post_success","durability":false},{"type":"death","name":"post_death","routingKey":"post_death","durability":true}]}',
        true, 'gelecekbilimde', current_timestamp);


insert into gb_category (order_number, parent_id, name, description, slug, icon, created_by, created_at)
values (0, 1, 'Fizik', 'Fizik kategorisidir.', 'fizik', null,
        'gelecekbilimde', current_timestamp);
insert into gb_category (order_number, parent_id, name, description, slug, icon, created_by, created_at)
values (1, 1, 'Biyoloji', 'Biyoloji kategorisidir.', 'biyoloji', null,
        'gelecekbilimde', current_timestamp);
insert into gb_category (order_number, parent_id, name, description, slug, icon, created_by, created_at)
values (2, 1, 'Kimya', 'Kimya kategorisidir.', 'kimya', null,
        'gelecekbilimde', current_timestamp);
insert into gb_category (order_number, parent_id, name, description, slug, icon, created_by, created_at)
values (1, null, 'Teknoloji', 'Teknoloji kategorisidir.', 'teknoloji', 'cpu',
        'gelecekbilimde', current_timestamp);
insert into gb_category (order_number, parent_id, name, description, slug, icon, created_by, created_at)
values (2, null, 'Felsefe', 'Felsefe kategorisidir.', 'felsefe', 'book',
        'gelecekbilimde', current_timestamp);
insert into gb_category (order_number, parent_id, name, description, slug, icon, created_by, created_at)
values (1, 6, 'Ontoloji', 'Ontoloji kategorisidir.', 'ontoloji', null,
        'gelecekbilimde', current_timestamp);
insert into gb_category (order_number, parent_id, name, description, slug, icon, created_by, created_at)
values (2, 6, 'Ahlak Felsefesi', 'Ahlak Felsefesi kategorisidir.', 'ahlak-felsefesi', null,
        'gelecekbilimde', current_timestamp);
insert into gb_category (order_number, parent_id, name, description, slug, icon, created_by, created_at)
values (0, 6, 'Epistemoloji', 'Epistemoloji kategorisidir.', 'epistelomoji', null,
        'gelecekbilimde', current_timestamp);
insert into gb_category (order_number, parent_id, name, description, slug, icon, created_by, created_at)
values (0, null, 'Bilim', 'Bilim kategorisidir.', 'bilim', 'flask-conical',
        'gelecekbilimde', current_timestamp);


insert into gb_user (id, birth_date, email, gender, first_name, last_name, password,
                     status, role_id, created_by, created_at)
values ('22afc9b4-807f-4eb2-b286-788631d1ed55', current_date, 'admin@gelecekbilimde.net',
        'MALE', 'Gelecek', 'Bilimde',
        '$2a$10$atVE.cT5YpEOS7ZLSoVdk.QKVyYBCgvNhvQEuCcXbEMpohYIjbZDG', 'VERIFIED',
        'c147b5c2-87f7-4bb7-a165-368f639d8c3c', 'gelecekbilimde', current_timestamp);

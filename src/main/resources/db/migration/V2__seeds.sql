INSERT INTO role (id, description, name, is_default) VALUES ('4dd2d4db-6e4f-4ec6-a1a6-9f94a68c2e24', 'Ziyaretçi', 'GUEST', false);
INSERT INTO role (id, description, name, is_default) VALUES ('c147b5c2-87f7-4bb7-a165-368f639d8c3c', 'Admin', 'ADMIN', false);
INSERT INTO role (id, description, name, is_default) VALUES ('e3a1a32d-fcd7-46f0-bb2b-201df6b2b808', 'Sosyal Kullanıcı', 'USER', true);


INSERT INTO permission (id, description, name) VALUES ('8d365c55-0d60-497b-bb03-8880a7f3c2d8', 'Anasayfa gezer', 'landing:show');
INSERT INTO permission (id, description, name) VALUES ('1a5b4eae-835c-4a8b-95b4-1875b65cf7d9', 'Kullanıcı Ekler', 'user:create');
INSERT INTO permission (id, description, name) VALUES ('3f6f18f3-ba3d-4f3a-b4c3-4681c7e42d6f', 'Kullanıcı Siler', 'user:delete');
INSERT INTO permission (id, description, name) VALUES ('9c032c81-5c0e-4d08-b3a6-e7d524b354cb', 'Gönderi Paylaşır', 'post:create');
INSERT INTO permission (id, description, name) VALUES ('55e3ef7a-2a1a-4c5b-8d72-3bd1e2c6560b', 'Gönderisini Düzenler', 'post:update');
INSERT INTO permission (id, description, name) VALUES ('35bc91b4-3b92-4ff5-b204-439ef21d2d94', 'Gönderisini Siler', 'post:delete');
INSERT INTO permission (id, description, name) VALUES ('6842cfea-0568-4970-af2e-bc9e3f7ed2cf', 'Yorum Yapar', 'comment:create');
INSERT INTO permission (id, description, name) VALUES ('d16020f2-1331-4e2f-94aa-7f6d08b6e157', 'Yorum Beğenir', 'comment:like');
INSERT INTO permission (id, description, name) VALUES ('b8f1e920-5e4c-4371-aa22-ea1f1f3fcf46', 'Test Versiyonu Görür', 'dev:version');

INSERT INTO permission (id, description, name) VALUES ('836e0a9c-b2bb-4ecf-bf8b-870ea7bb8bc7', 'Admin Paneline erişir', 'admin:access');


INSERT INTO role_permission (role_id, permission_id) VALUES ('4dd2d4db-6e4f-4ec6-a1a6-9f94a68c2e24', '8d365c55-0d60-497b-bb03-8880a7f3c2d8');
INSERT INTO role_permission (role_id, permission_id) VALUES ('c147b5c2-87f7-4bb7-a165-368f639d8c3c', '1a5b4eae-835c-4a8b-95b4-1875b65cf7d9');
INSERT INTO role_permission (role_id, permission_id) VALUES ('c147b5c2-87f7-4bb7-a165-368f639d8c3c', '8d365c55-0d60-497b-bb03-8880a7f3c2d8');
INSERT INTO role_permission (role_id, permission_id) VALUES ('c147b5c2-87f7-4bb7-a165-368f639d8c3c', '3f6f18f3-ba3d-4f3a-b4c3-4681c7e42d6f');
INSERT INTO role_permission (role_id, permission_id) VALUES ('c147b5c2-87f7-4bb7-a165-368f639d8c3c', '9c032c81-5c0e-4d08-b3a6-e7d524b354cb');
INSERT INTO role_permission (role_id, permission_id) VALUES ('c147b5c2-87f7-4bb7-a165-368f639d8c3c', '55e3ef7a-2a1a-4c5b-8d72-3bd1e2c6560b');
INSERT INTO role_permission (role_id, permission_id) VALUES ('c147b5c2-87f7-4bb7-a165-368f639d8c3c', '35bc91b4-3b92-4ff5-b204-439ef21d2d94');
INSERT INTO role_permission (role_id, permission_id) VALUES ('e3a1a32d-fcd7-46f0-bb2b-201df6b2b808', '8d365c55-0d60-497b-bb03-8880a7f3c2d8');
INSERT INTO role_permission (role_id, permission_id) VALUES ('e3a1a32d-fcd7-46f0-bb2b-201df6b2b808', '9c032c81-5c0e-4d08-b3a6-e7d524b354cb');
INSERT INTO role_permission (role_id, permission_id) VALUES ('e3a1a32d-fcd7-46f0-bb2b-201df6b2b808', '55e3ef7a-2a1a-4c5b-8d72-3bd1e2c6560b');
INSERT INTO role_permission (role_id, permission_id) VALUES ('e3a1a32d-fcd7-46f0-bb2b-201df6b2b808', '35bc91b4-3b92-4ff5-b204-439ef21d2d94');
INSERT INTO role_permission (role_id, permission_id) VALUES ('e3a1a32d-fcd7-46f0-bb2b-201df6b2b808', '6842cfea-0568-4970-af2e-bc9e3f7ed2cf');
INSERT INTO role_permission (role_id, permission_id) VALUES ('e3a1a32d-fcd7-46f0-bb2b-201df6b2b808', 'd16020f2-1331-4e2f-94aa-7f6d08b6e157');
INSERT INTO role_permission (role_id, permission_id) VALUES ('c147b5c2-87f7-4bb7-a165-368f639d8c3c', 'b8f1e920-5e4c-4371-aa22-ea1f1f3fcf46');
INSERT INTO role_permission (role_id, permission_id) VALUES ('c147b5c2-87f7-4bb7-a165-368f639d8c3c', '836e0a9c-b2bb-4ecf-bf8b-870ea7bb8bc7');

INSERT INTO settings (id, group_name, name, definition, create_user_id, is_hidden, created_at, updated_at) VALUES (1, 'queues', 'post-queue', '{"exchangeName":"post_exchange","type":"direct","durability":true,"queues":[{"type":"master","name":"post_publish","routingKey":"post.publish","maxExec":5,"durability":true},{"type":"success","name":"post_success","routingKey":"post.success","durability":false},{"type":"death","name":"post_death","routingKey":"post.death","durability":true}]}', 1,true, NOW(), NOW());

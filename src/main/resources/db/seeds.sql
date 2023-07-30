INSERT INTO role (id, description, role, is_default) VALUES (1, 'Ziyaretçi', 'GUEST', null);
INSERT INTO role (id, description, role, is_default) VALUES (2, 'Admin', 'ADMIN', null);
INSERT INTO role (id, description, role, is_default) VALUES (3, 'Sosyal Kullanıcı', 'USER', true);


INSERT INTO permission (id, description, permission) VALUES (1, 'Anasayfa gezer', 'landing:show');
INSERT INTO permission (id, description, permission) VALUES (2, 'Kullanıcı Ekler', 'user:create');
INSERT INTO permission (id, description, permission) VALUES (3, 'Kullanıcı Siler', 'user:delete');
INSERT INTO permission (id, description, permission) VALUES (4, 'Gönderi Paylaşır', 'post:create');
INSERT INTO permission (id, description, permission) VALUES (5, 'Gönderisini Düzenler', 'post:update');
INSERT INTO permission (id, description, permission) VALUES (6, 'Gönderisini Siler', 'post:delete');
INSERT INTO permission (id, description, permission) VALUES (7, 'Yorum Yapar', 'comment:create');
INSERT INTO permission (id, description, permission) VALUES (8, 'Yorum Beğenir', 'comment:like');
INSERT INTO permission (id, description, permission) VALUES (9, 'Test Versiyonu Görür', 'dev:version');


INSERT INTO role_permission (role_id, permission_id) VALUES (1, 1);
INSERT INTO role_permission (role_id, permission_id) VALUES (2, 2);
INSERT INTO role_permission (role_id, permission_id) VALUES (2, 1);
INSERT INTO role_permission (role_id, permission_id) VALUES (2, 3);
INSERT INTO role_permission (role_id, permission_id) VALUES (2, 4);
INSERT INTO role_permission (role_id, permission_id) VALUES (2, 5);
INSERT INTO role_permission (role_id, permission_id) VALUES (2, 6);
INSERT INTO role_permission (role_id, permission_id) VALUES (3, 1);
INSERT INTO role_permission (role_id, permission_id) VALUES (3, 4);
INSERT INTO role_permission (role_id, permission_id) VALUES (3, 5);
INSERT INTO role_permission (role_id, permission_id) VALUES (3, 6);
INSERT INTO role_permission (role_id, permission_id) VALUES (3, 7);
INSERT INTO role_permission (role_id, permission_id) VALUES (3, 8);
INSERT INTO role_permission (role_id, permission_id) VALUES (2, 9);

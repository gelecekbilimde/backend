INSERT INTO permission (id, description, name,is_hidden) VALUES ('0150a560-8536-427e-8e76-fb1f6d41f5e7', 'Self Ticket Read', 'self:ticket:read',false);
INSERT INTO permission (id, description, name,is_hidden) VALUES ('b7c4f0d1-3f0e-46aa-9c02-ee7e7cbf49bb', 'All Ticket Read', 'ticket:read',false);
INSERT INTO permission (id, description, name,is_hidden) VALUES ('1bb69fdf-d470-4c37-8075-58ff0fa0d19e', 'Self Ticket Create', 'self:ticket:create',false);
INSERT INTO permission (id, description, name,is_hidden) VALUES ('7572d942-b048-40bf-8cf4-c7a87d9be586', 'All Ticket Update', 'ticket:update',false);

INSERT INTO role_permission (role_id, permission_id) VALUES ('e3a1a32d-fcd7-46f0-bb2b-201df6b2b808', '0150a560-8536-427e-8e76-fb1f6d41f5e7');
INSERT INTO role_permission (role_id, permission_id) VALUES ('e3a1a32d-fcd7-46f0-bb2b-201df6b2b808', '1bb69fdf-d470-4c37-8075-58ff0fa0d19e');
INSERT INTO role_permission (role_id, permission_id) VALUES ('c147b5c2-87f7-4bb7-a165-368f639d8c3c', 'b7c4f0d1-3f0e-46aa-9c02-ee7e7cbf49bb');
INSERT INTO role_permission (role_id, permission_id) VALUES ('c147b5c2-87f7-4bb7-a165-368f639d8c3c', '7572d942-b048-40bf-8cf4-c7a87d9be586');

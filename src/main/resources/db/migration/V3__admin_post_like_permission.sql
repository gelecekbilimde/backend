-- ADMIN rolü daha önce beğeni işlemini 'post:create' yetkisi üzerinden yapabiliyordu.
-- Beğeni endpoint'i artık 'post:like' yetkisini kontrol ettiği için ADMIN'e de bu yetki veriliyor.
insert into gb_role_permission (role_id, permission_id)
select 'c147b5c2-87f7-4bb7-a165-368f639d8c3c', '01bc1089-bd27-4b41-bfde-8e63c988fec3'
where not exists (select 1
                  from gb_role_permission
                  where role_id = 'c147b5c2-87f7-4bb7-a165-368f639d8c3c'
                    and permission_id = '01bc1089-bd27-4b41-bfde-8e63c988fec3');

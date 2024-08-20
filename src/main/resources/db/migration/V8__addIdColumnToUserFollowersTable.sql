ALTER TABLE user_followers
  ADD COLUMN id SERIAL;

UPDATE user_followers
SET id = nextval('user_followers_id_seq')
WHERE id IS NULL;

ALTER TABLE user_followers
  DROP CONSTRAINT IF EXISTS user_followers_pk;

ALTER TABLE user_followers
  ADD PRIMARY KEY (id, follower_user_id, followed_user_id);

CREATE TABLE IF NOT EXISTS "post_like" (
  id             VARCHAR(36)       NOT NULL PRIMARY KEY,
  post_id       VARCHAR(36)       REFERENCES post(id) NOT NULL,
  user_id      VARCHAR(36)       REFERENCES user(id) NOT NULL,
  created_at     TIMESTAMP        NOT NULL,

  CONSTRAINT unique_post_like UNIQUE (post_id, user_id),
  CONSTRAINT chk_like_status CHECK (created_at IS NOT NULL)
  );

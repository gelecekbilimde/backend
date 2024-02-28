CREATE TABLE IF NOT EXISTS "post_like"
(
  id
  bigserial
  NOT
  NULL
  PRIMARY
  KEY,
  post_id
  VARCHAR
(
  36
) REFERENCES post
(
  id
) NOT NULL,
  user_id VARCHAR
(
  36
) REFERENCES "user"
(
  id
) NOT NULL,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  CONSTRAINT unique__post_like__post_id__user_id UNIQUE
(
  post_id,
  user_id
)
  );

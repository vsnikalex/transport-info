-- fpmoles : password, jdoe : foobar
INSERT INTO user
(username, password) VALUES
('fpmoles', '$2a$11$dp4wMyuqYE3KSwIyQmWZFeUb7jCsHAdk7ZhFc0qGw6i5J124imQBi'),
('jdoe', '$2a$11$3NO32OV1TGjap3xMpAEjmuiizitWuaSwUYz42aMtlxRliwJ8zm4Sm');

INSERT INTO auth_user_group
(username, auth_group) VALUES
('fpmoles', 'USER'),
('fpmoles', 'ADMIN'),
('jdoe', 'USER');

-- admin : password, others : foobar
INSERT INTO user
(username, password) VALUES
('admin', '$2a$11$dp4wMyuqYE3KSwIyQmWZFeUb7jCsHAdk7ZhFc0qGw6i5J124imQBi'),
('Test', '$2a$11$3NO32OV1TGjap3xMpAEjmuiizitWuaSwUYz42aMtlxRliwJ8zm4Sm'),
('Johan', '$2a$11$3NO32OV1TGjap3xMpAEjmuiizitWuaSwUYz42aMtlxRliwJ8zm4Sm'),
('Robert', '$2a$11$3NO32OV1TGjap3xMpAEjmuiizitWuaSwUYz42aMtlxRliwJ8zm4Sm'),
('Richard', '$2a$11$3NO32OV1TGjap3xMpAEjmuiizitWuaSwUYz42aMtlxRliwJ8zm4Sm'),
('James', '$2a$11$3NO32OV1TGjap3xMpAEjmuiizitWuaSwUYz42aMtlxRliwJ8zm4Sm'),
('Ivan', '$2a$11$3NO32OV1TGjap3xMpAEjmuiizitWuaSwUYz42aMtlxRliwJ8zm4Sm'),
('Mikhail', '$2a$11$3NO32OV1TGjap3xMpAEjmuiizitWuaSwUYz42aMtlxRliwJ8zm4Sm'),
('Oleg', '$2a$11$3NO32OV1TGjap3xMpAEjmuiizitWuaSwUYz42aMtlxRliwJ8zm4Sm'),
('Urii', '$2a$11$3NO32OV1TGjap3xMpAEjmuiizitWuaSwUYz42aMtlxRliwJ8zm4Sm'),
('Igor', '$2a$11$3NO32OV1TGjap3xMpAEjmuiizitWuaSwUYz42aMtlxRliwJ8zm4Sm'),
('Vlad', '$2a$11$3NO32OV1TGjap3xMpAEjmuiizitWuaSwUYz42aMtlxRliwJ8zm4Sm'),
('Egor', '$2a$11$3NO32OV1TGjap3xMpAEjmuiizitWuaSwUYz42aMtlxRliwJ8zm4Sm'),
('Emelya', '$2a$11$3NO32OV1TGjap3xMpAEjmuiizitWuaSwUYz42aMtlxRliwJ8zm4Sm'),
('Bob', '$2a$11$3NO32OV1TGjap3xMpAEjmuiizitWuaSwUYz42aMtlxRliwJ8zm4Sm'),
('Rich', '$2a$11$3NO32OV1TGjap3xMpAEjmuiizitWuaSwUYz42aMtlxRliwJ8zm4Sm'),
('Nice', '$2a$11$3NO32OV1TGjap3xMpAEjmuiizitWuaSwUYz42aMtlxRliwJ8zm4Sm');

INSERT INTO auth_user_group
(username, auth_group) VALUES
('admin', 'ADMIN'),
('Test', 'USER'),
('Johan', 'USER'),
('Robert', 'USER'),
('Richard', 'USER'),
('James', 'USER'),
('Ivan', 'USER'),
('Mikhail', 'USER'),
('Oleg', 'USER'),
('Urii', 'USER'),
('Igor', 'USER'),
('Vlad', 'USER'),
('Egor', 'USER'),
('Emelya', 'USER'),
('Bob', 'USER'),
('Rich', 'USER'),
('Nice', 'USER');

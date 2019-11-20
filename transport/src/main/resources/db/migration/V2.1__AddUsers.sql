-- admin : password, driver_jane : foobar
INSERT INTO security_user
(id, username, password) VALUES
(1,  'admin', 'password'),
(2,  'driver_jane', 'foobar'),
(3,  'driver_mark', 'mark'),
(4,  'driver_jdoe', 'jdoe');

INSERT INTO security_role
(id, role_name, description) VALUES
(1, 'ADMIN', 'Administrator'),
(2, 'DRIVER', 'Truck driver');

INSERT INTO user_role
(user_id, role_id) VALUES
 (1, 1), -- give admin ROLE_ADMIN
 (2, 2),  -- give Jane ROLE_DRIVER
 (3, 2),  -- give Mark ROLE_DRIVER
 (4, 1),  -- give Wally ROLE_ADMIN
 (4, 2);  -- give Wally ROLE_DRIVER

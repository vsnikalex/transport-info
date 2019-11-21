CREATE TABLE user (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(128) NOT NULL UNIQUE,
  password VARCHAR(256) NOT NULL
);

CREATE TABLE auth_user_group (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(128) NOT NULL,
  auth_group VARCHAR(128) NOT NULL,
  CONSTRAINT USER_AUTH_USER_GROUP_FK FOREIGN KEY(username) REFERENCES user(username),
  UNIQUE (username, auth_group)
);

CREATE TABLE driver (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(128) NOT NULL,
	firstName VARCHAR(250) NOT NULL,
	lastName VARCHAR(250) NOT NULL,
	location VARCHAR(500) NOT NULL,
	delivery_id BIGINT NULL,
    CONSTRAINT FK_DRIVER_DELIVERY_ID FOREIGN KEY (delivery_id) REFERENCES delivery (id),
    CONSTRAINT FK_RIVER_USER FOREIGN KEY(username) REFERENCES user(username)
);

create table task (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	action VARCHAR(250) NOT NULL,
	start DATETIME NOT NULL,
	end DATETIME NULL,
	driver_id BIGINT NOT NULL,
	truck_id BIGINT NOT NULL,
	constraint FK_TASK_TRUCK_ID foreign key (truck_id) references truck (id),
	constraint FK_TASK_DRIVER_ID foreign key (driver_id) references driver (id)
);

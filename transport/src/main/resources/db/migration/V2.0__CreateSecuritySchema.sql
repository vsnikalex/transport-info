CREATE TABLE security_role (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  description varchar(100) NOT NULL,
  role_name varchar(100) NOT NULL
);

CREATE TABLE security_user (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  CONSTRAINT UK_USERNAME UNIQUE (username)
);

CREATE TABLE user_role (
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  CONSTRAINT FK_SECURITY_USER_ID FOREIGN KEY (user_id) REFERENCES security_user (id),
  CONSTRAINT FK_SECURITY_ROLE_ID FOREIGN KEY (role_id) REFERENCES security_role (id)
);

CREATE TABLE driver (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	firstName VARCHAR(250) NOT NULL,
	lastName VARCHAR(250) NOT NULL,
	location VARCHAR(500) NOT NULL,
	delivery_id BIGINT NULL,
    CONSTRAINT FK_DRIVER_DELIVERY_ID FOREIGN KEY (delivery_id) REFERENCES delivery (id)
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

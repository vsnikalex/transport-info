CREATE TABLE security_role (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  description varchar(100) DEFAULT NULL,
  role_name varchar(100) DEFAULT NULL
);

CREATE TABLE security_user (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL
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
	id bigint auto_increment primary key,
	action varchar(250) not null,
	start datetime not null,
	end datetime not null,
	driver_id bigint not null,
	truck_id bigint not null,
	constraint FK_TASK_TRUCK_ID foreign key (truck_id) references truck (id),
	constraint FK_TASK_DRIVER_ID foreign key (driver_id) references driver (id)
);

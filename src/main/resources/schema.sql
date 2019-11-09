create schema transport_info collate utf8mb4_0900_ai_ci;

create table depot
(
	id bigint not null
		primary key,
	location varchar(512) null,
	type varchar(255) null
);

create table truck
(
	id bigint not null
		primary key,
	capacity int null,
	location varchar(512) null,
	plate varchar(255) null,
	status varchar(255) null,
	constraint UK_doo99d174d4km77mhu6y3yyhg
		unique (plate)
);

create table delivery
(
	id bigint not null
		primary key,
	done bit null,
	route varchar(255) null,
	truck_id bigint not null,
	constraint FKgyyc1ga8bgchktubl1in9sj3c
		foreign key (truck_id) references truck (id)
);

create table cargo
(
	id bigint not null
		primary key,
	description varchar(255) null,
	status varchar(255) null,
	weight int null,
	delivery_id bigint null,
	endDepot_id bigint null,
	startDepot_id bigint null,
	constraint FK1w2b77bjp20yd5j6ea3cehose
		foreign key (delivery_id) references delivery (id),
	constraint FK3pth8yk6803ee9lj58fagyoig
		foreign key (endDepot_id) references depot (id),
	constraint FK5kmoaa6dqf3tjh70lrmluyltc
		foreign key (startDepot_id) references depot (id)
);

create table driver
(
	id bigint not null
		primary key,
	firstName varchar(255) null,
	lastName varchar(255) null,
	location varchar(512) null,
	delivery_id bigint null,
	constraint FKa3jvp802nxgwo7l3l54sbxsvy
		foreign key (delivery_id) references delivery (id)
);

create table task
(
	id bigint not null
		primary key,
	action varchar(255) null,
	end datetime null,
	start datetime null,
	driver_id bigint not null,
	truck_id bigint null,
	constraint FKfswuybpu30ladow8241e0o1k1
		foreign key (truck_id) references truck (id),
	constraint FKg6l716fshnfuilkb95i8rmxca
		foreign key (driver_id) references driver (id)
);


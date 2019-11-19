create table depot (
	id bigint auto_increment primary key,
	location varchar(500) not null,
	type varchar(250) not null
);

create table truck (
	id bigint auto_increment primary key,
	capacity int not null,
	location varchar(500) not null,
	plate varchar(250) not null,
	status varchar(250) not null,
	constraint UK_PLATE unique (plate)
);

create table delivery (
	id bigint auto_increment primary key,
	done bit not null,
	route varchar(250) not null,
	truck_id bigint not null,
	constraint FK_DELIVERY_TRUCK_ID foreign key (truck_id) references truck (id)
);

create table cargo (
	id bigint auto_increment primary key,
	description varchar(250) not null,
	status varchar(250) not null,
	weight int not null,
	startDepot_id bigint not null,
	endDepot_id bigint not null,
	delivery_id bigint nullable,
	constraint FK_CARGO_DELIVERY_ID foreign key (delivery_id) references delivery (id),
	constraint FK_CARGO_START_DEPOT_ID foreign key (startDepot_id) references depot (id),
	constraint FK_CARGO_END_DEPOT_ID foreign key (endDepot_id) references depot (id)
);

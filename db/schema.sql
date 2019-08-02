drop table if exists employee;
drop table if exists request;

create table employee(
	username text primary key,
	password text,
	category text not null,
	firstname text not null,
	lastname text not null,
	phone_number text,
	email text,
	salt bytea,
	hash bytea
);

create table request(
	request_id serial primary key,
	username text not null,
	description text,
	amount numeric(10,2),
	date text not null,
	status text not null,
	manager text not null,
	image text not null
);

insert into employee(username,password,category,firstname,lastname,phone_number,email,salt,hash) 
values('kbui314','password','employee','Kenny','Bui','2252527477','kbui31@yahoo.com',
'{-24, -54, -101, -77, 9, 79, 25, -75, -51, 99, -49, -25, -96, -100, 19, -39}',
'{66, -11, -29, 91, 7, -80, -103, 44, 75, 74, -59, 75, 67, 83, -34, -55, 8, 42, -9, 85, -8, 100, -126, -88, -104, 44, 120, 62, -66, 64, -77, -56, 74, 111, 26, 9, -27, 85, 8, -87, -1, 41, 97, -84, 113, 83, -99, -18, 54, 123, 31, 70, 98, -26, 79, -126, 115, 75, -102, -98, -80, 112, 115, -88}');

insert into employee(username,password,category,firstname,lastname,phone_number,email,salt,hash) 
values('lWalker7','revenge','employee','Luke','Skywalker','7034556578','lwalker79@gmail.com',
'{27, -128, -119, -5, 11, 110, 37, -17, 56, 1, -46, -106, -121, 56, -70, -114}',
'{-68, -84, 32, 88, -36, 62, -61, -59, -42, 113, 40, 76, 121, 20, -38, -70, -102, -72, 124, -102, -93, 115, 95, -84, -8, 28, -90, 70, -76, -3, -4, 30, -74, 40, -82, 104, -4, 73, -70, 97, -96, -98, 102, 66, 61, -123, 47, -34, -108, -60, 1, -39, 106, 116, -121, -122, -107, 20, -93, -31, 53, 12, -23, 15}');

insert into employee(username,password,category,firstname,lastname,phone_number,email,salt,hash) 
values('pdome34','pdome43','manager','Paulina','Dome','9568880909','pdome43@gmail.com',
'{-35, -34, -75, 81, -128, 29, 91, 8, 80, -70, 109, 106, -71, -12, 24, -13}',
'{-84, 28, -105, -77, -95, 32, -115, -48, 51, 41, 31, 83, 125, -31, 32, -111, 30, -76, 69, 120, -51, -6, -13, 41, -48, -40, -65, -84, -91, 17, -119, -58, -103, -117, -2, 56, 35, -12, -68, -53, 79, 111, 107, -122, 124, -94, 113, 9, 4, 16, 94, -54, -57, 59, 47, -53, 71, -115, -94, -35, 86, 102, -107, 87}');

insert into employee(username,password,category,firstname,lastname,phone_number,email,salt,hash) 
values('jbondo321','007bond','manager','James','Bond','0070070007','chunster1@gmail.com',
'{117, -77, -21, 6, -90, 53, 92, 80, -115, 120, 113, -89, 25, -76, 113, -120}',
'{23, -96, -111, 123, -23, -127, 103, -85, 47, 49, 88, -105, -44, -23, 118, -35, -123, 118, -80, 127, -31, -93, 118, 80, -5, -59, -111, -35, 96, -18, 43, -14, -83, -32, 70, -69, 97, 69, -109, -86, 6, -8, 122, 9, -85, -45, -45, 79, 27, -120, -109, -105, -125, -57, 56, 24, -118, 9, -66, -29, -65, 23, 28, 81}');



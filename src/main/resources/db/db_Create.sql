SET GLOBAL time_zone = '+03:00';
SET NAMES 'utf8';
SET CHARACTER SET 'utf8';

DROP SCHEMA IF EXISTS service_centre;

CREATE SCHEMA service_centre;

USE service_centre;


create table Users(
user_id integer  not null primary key AUTO_INCREMENT,
first_name varchar(50) not null,
last_name varchar(50) not null,
login varchar(50) not null,
user_password varchar(50) not null,
email varchar(50) not null,
phone varchar(50) not null,
role enum('client','admin','main_admin', 'master') not null default 'client'
);

ALTER TABLE users ADD CONSTRAINT users_login UNIQUE (login);

create table Product(
product_id integer  not null primary key AUTO_INCREMENT,
product_name varchar(50) not null,
product_type varchar(50) not null);
 
create table Orders(
order_id integer  not null primary key AUTO_INCREMENT,
user_id integer  not null,
product_id integer  not null,
master_id integer,
manager_id integer,
problem_description varchar(200) not null,
rejection_reason varchar(200),
price decimal(10,2),
date_of_placement date not null,
order_status enum('new',
'accepted',
'rejected',
'agreed',
'waiting_for_master',
'performed',
'fulfilled',
'closed') not null default 'new');


alter table orders add constraint fk_user_id foreign key (user_id) references users(user_id);
alter table orders add constraint fk_master_id foreign key (master_id) references users(user_id);
alter table orders add constraint fk_manager_id foreign key (manager_id) references users(user_id);
alter table orders add constraint fk_product_id foreign key (product_id) references product(product_id);

create table Comments(
comment_id integer  not null primary key AUTO_INCREMENT,
order_id integer  not null,
user_id integer  not null,
comment_content varchar(500)  not null,
comment_status enum('valid', 'banned') not null default 'valid');

alter table comments add constraint fk_commenter_id foreign key (user_id, order_id) references orders(user_id, order_id);

create or replace view admin as
Select user_id as admin_id, login as admin_login
From users where role = 'admin' or role='main_admin';

create or replace view masters as
Select user_id as master_id, login as master_login
From users where role = 'master';
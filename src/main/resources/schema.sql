CREATE TABLE CLIENTS(
   id integer not null,
   name varchar(255) not null,
   surname varchar(255) not null,
--   fiscal_code varchar(25) not null,
   fiscal_code varchar(25),
   location varchar(255),
   primary key(id)
);

CREATE TABLE ORDERS(
   id integer not null auto_increment,
   price decimal,
   description varchar(255),
   client_id integer,
   primary key(id),
   foreign key(client_id) references clients(id)
);



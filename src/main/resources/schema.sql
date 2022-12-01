CREATE TABLE CLIENTS(
   id integer not null auto_increment,
   name varchar(255) not null,
   surname varchar(255) not null,
   fiscal_code varchar(25) not null,
   location varchar(255),
   primary key(id)
);

CREATE TABLE PRODUCTS(
   id integer not null auto_increment,
   name varchar(255) not null,
   price decimal not null,
   description varchar(255),
   primary key(id)
);

CREATE TABLE ORDERS(
   id integer not null auto_increment,
   price decimal,
   pieces integer,
   client_id integer,
   product_id integer,
   primary key(id),
   foreign key(client_id) references clients(id),
   foreign key(product_id) references products(id)
);



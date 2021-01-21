create table beer(
  id bigint not null auto_increment,
  alcohol_content tinyint not null,
  category varchar(255) not null,
  ingredients varchar(255) not null,
  name varchar(255) not null,
  price decimal(19,2) not null,
  primary key (id)
) engine=InnoDB default charset=utf8;

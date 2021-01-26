-- MIT License
--
-- Copyright (c) 2021 Bruno Andrade
--
-- Permission is hereby granted, free of charge, to any person obtaining a copy
-- of this software and associated documentation files (the "Software"), to deal
-- in the Software without restriction, including without limitation the rights
-- to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
-- copies of the Software, and to permit persons to whom the Software is
-- furnished to do so, subject to the following conditions:
--
-- The above copyright notice and this permission notice shall be included in all
-- copies or substantial portions of the Software.
--
-- THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
-- IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
-- FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
-- AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
-- LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
-- OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
-- SOFTWARE
create table beer (
  id bigint not null auto_increment,
  alcohol_content tinyint not null,
  name varchar(255) not null,
  price decimal(19,2) not null,
  category_id bigint not null,
  primary key (id)
) engine=InnoDB default charset=utf8;

create table beer_ingredient (
  beer_id bigint not null,
  ingredient_id bigint not null,
  primary key (beer_id, ingredient_id)
) engine=InnoDB default charset=utf8;

create table category (
  id bigint not null auto_increment,
  name varchar(255) not null,
  primary key (id)
) engine=InnoDB default charset=utf8;

create table ingredient (
  id bigint not null auto_increment,
  name varchar(255) not null,
  primary key (id)
) engine=InnoDB default charset=utf8;

alter table category
  add constraint UK_46ccwnsi9409t36lurvtyljak unique (name);

alter table ingredient
    add constraint UK_46ccwnsi9509t36lurvtyljak unique (name);

alter table beer
  add constraint FKcgtf5842l7ptlc1xhdu9fpju0 foreign key (category_id)
  references category (id);

alter table beer_ingredient
  add constraint FKm9jpqusls4cfkp7437va14gmp  foreign key (ingredient_id)
  references ingredient (id);

alter table beer_ingredient  add constraint FKnj0uy3mjydf475spef88x9l9a
  foreign key (beer_id)
  references beer (id)

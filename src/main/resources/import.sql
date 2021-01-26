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
insert into ingredient(name) values('water');
insert into ingredient(name) values('yeast');
insert into ingredient(name) values('hops');
insert into ingredient(name) values('malted_barley');

insert into category(name) values('ale');
insert into category(name) values('lager');

insert into beer(name, alcohol_content, category_id, price) values('Craft Beer Ale', 51, 1, 25.70);
insert into beer_ingredient(beer_id, ingredient_id) values(1, 1);
insert into beer_ingredient(beer_id, ingredient_id) values(1, 2);

insert into beer(name, alcohol_content, category_id, price) values('Craft Beer Lager', 23, 2, 32.20);
insert into beer_ingredient(beer_id, ingredient_id) values(2, 3);
insert into beer_ingredient(beer_id, ingredient_id) values(2, 4);

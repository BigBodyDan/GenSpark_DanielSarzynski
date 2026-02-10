-- Task 1
select * 
from products;
-- Task 2
select ProductName, Unit, Price
from products;
-- Task 3
select ProductName, Unit, Price
from products
where price > 20
and price <= 50;
-- Task 4
select ProductName, Unit, Price
from products
order by price desc, 
ProductName asc;
-- Task 5
select ProductName, Unit, Price
from products
limit 10;
-- Task 6
select ProductName, Unit, Price
from products
where price > 20
and price <= 50
order by price desc,
ProductName asc
limit 10;
-- Task 1 
select *
from products;
-- Task 2 
select *
from products
join categories
on products.CategoryID = categories.CategoryID;
-- Task 3
select p.ProductName, c.CategoryName, p.Price
from products as p
join categories as c
on p.CategoryID = c.CategoryID;
-- Task 4
select e.LastName, o.OrderID
from employees as e 
left join orders as o
on e.EmployeeID = o.EmployeeID;
-- Task 5
select p.ProductName, c.CategoryName, p.Price
from products as p
join categories as c
on p.CategoryID = c.CategoryID
where p.Price >= 40;
-- Task 6
select p.ProductName, c.CategoryName, p.Price
from products as p
join categories as c
on p.CategoryID = c.CategoryID
where p.Price >= 40
order by c.CategoryName asc,
p.Price desc;
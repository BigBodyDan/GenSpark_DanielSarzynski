-- Task 1 
-- Count all order lines and order lines where quantity greater than 25
select 
	count(od.OrderDetailID) as total_order_details,
	count(case 
				when od.Quantity > 25 then 1 
                else null 
			end) as high_quantity
from orderdetails as od;

-- Task 2
-- Total Quantity and Average Quantity
select 
	sum(od.Quantity) as total_quantity,
	avg(od.Quantity) as avg_quantity
from orderdetails as od;

-- Task 3
-- Min Quantity and Max Quantity
select 
	min(od.Quantity) as min_quantity,
	max(od.Quantity) as max_quantity
from orderdetails as od;

-- Task 4
-- Retreive number of order line items and quantity in each order
select 
	od.OrderID,
	count(od.OrderDetailID) as types_of_items,
	sum(od.Quantity) as total_quantity
from orderdetails as od
group by od.OrderID;

-- Task 5 
-- Retreive orders where total order quantity is greater than 50
select 
	od.OrderID,
	sum(od.Quantity) as total_quantity
from orderdetails as od
group by od.OrderID
having total_quantity > 50;

-- Task 6
-- Retreive orders from Q4 1996 where total order quantity is greater than 50
select 
	od.OrderID,
	sum(od.Quantity) as total_quantity,
	o.OrderDate
from orderdetails as od
join orders as o on od.OrderID = o.OrderID
where o.OrderDate between '1996-09-01' and '1996-12-31'
group by od.OrderID
having total_quantity > 50;
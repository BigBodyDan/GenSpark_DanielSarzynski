-- Task 1
/* Business Questions include:
Monthly sales trends
Employees ranked by total sales
Total sales per product category
Top 5 customers by total spending in Q4 1996
Top 10 Best-Selling Products
*/

-- Task 2 
-- Product Details
select 
	p.ProductName,
    c.CategoryName,
    p.Price,
    s.SupplierName,
    s.Country as SupplierCountry
from 
	products as p
join
	categories as c on p.CategoryID = c.CategoryID
join 
	suppliers as s on p.SupplierID = s.SupplierID;
    
-- Order Information
select 
	o.OrderID,
    o.OrderDate,
    c.CustomerName,
	concat(e.FirstName, ' ', e.LastName) as EmployeeName,
    s.ShipperName
from 
	orders as o
join
	customers as c on o.CustomerID = c.CustomerID
join 
	employees as e on o.EmployeeID = e.EmployeeID
join 
	shippers as s on o.ShipperID = s.ShipperID
order by 
	o.OrderDate desc;
    
-- Task 3
-- Total Sales per Product Category
select 
	c.CategoryName,
    count(od.OrderDetailID) as TotalOrdersPlaced,
	sum(od.Quantity) as TotalQuantity,
    round(sum(od.Quantity * p.Price), 2) as TotalRevenue,
    round(avg(p.Price), 2) as AveragePrice
from 
	orderdetails as od
join
	products as p on od.ProductID = p.ProductID
join 
	categories as c on p.CategoryID = c.CategoryID
group by
	c.CategoryName;
	
-- Monthly Sales Trends
select 
	year(o.OrderDate) as OrderYear,
	month(o.OrderDate) as OrderMonth,
    sum(od.Quantity) as TotalQuantity,
    round(sum(od.Quantity * p.Price), 2) as MonthlyRevenue
from 
	orders as o 
join
	orderdetails as od on o.OrderID = od.OrderID
join 
	products as p on od.ProductID = p.ProductID
group by 
	OrderYear, OrderMonth
order by 
	OrderYear, OrderMonth;
    
-- Task 4
-- Top 5 Customers by Total Spending in Q4 1996
select
	c.CustomerName,
    round(sum(od.Quantity * p.Price), 2) as TotalSpent
from 
	customers as c
join
	orders as o on c.CustomerID = o.CustomerID
join 
	orderdetails as od on o.OrderID = od.OrderID
join 
	products as p on od.ProductID = p.ProductID 
where 
	o.OrderDate between '1996-10-01' and '1996-12-31'
group by 
	c.CustomerID
having 
	TotalSpent > 1000
order by 
	TotalSpent desc
limit 5;

-- Task 5
-- Employee Performance Ranking Report
with EmployeePerformance as (
	select 
		concat(e.FirstName, ' ', e.LastName) as EmployeeName,
        count(distinct o.OrderID) as TotalOrders,
        sum(od.Quantity) as TotalItemsSold,
        round(sum(od.Quantity * p.Price), 2) as TotalRevenue
	from 
		employees as e
	left join
		orders as o on e.EmployeeID = o.EmployeeID
	left join
		orderdetails as od on o.OrderID = od.OrderID
	left join 
		products as p on od.ProductID = p.ProductID 
	group by 
		e.EmployeeID
)
select 
	*, -- select all columns from previous query
    rank() over (order by TotalRevenue desc) as RevenueRank,
    rank() over (order by TotalOrders desc) as OrderVolumeRank,
    (case
		when TotalRevenue >= ( -- get AverageRevenue
				select avg(TotalRevenue)
                from EmployeePerformance
                where TotalRevenue > 0) then 'Above Average'
        when TotalRevenue > 0 then 'Below Average'
		else 'No Sales'
	end) as PerformanceCategory
from 
	EmployeePerformance
order by 
	RevenueRank;
    
-- Top 10 Best-Selling Products
with ProductPerformance as (
	select 
		p.ProductName, 
        c.CategoryID,
		c.CategoryName,
		p.Price, 
		sum(od.Quantity) as TotalSold,
		round(sum(od.Quantity) * p.Price, 2) as TotalRevenue
	from 
		products as p
	join 
		categories as c on p.CategoryID = c.CategoryID
	join 
		orderdetails as od on p.ProductID = od.ProductID
	group by
		p.ProductID
)
select 	
	pp.ProductName,
    pp.CategoryName,
    pp.Price,
    pp.TotalSold,
    pp.TotalRevenue,
    rank() over (order by TotalRevenue desc) as SalesRank,
    rank() over (partition by pp.CategoryID order by TotalRevenue desc) as CategoryRank
from 
	ProductPerformance as pp
order by 
	TotalRevenue desc
limit 10;
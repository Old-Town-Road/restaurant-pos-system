drop user if exists 'pizzaposuser'@'localhost';
create user 'pizzaposuser'@'localhost' identified by 'Burnt4Pizzas!';
grant execute on pizzaposdb.* to 'pizzaposuser'@'localhost';
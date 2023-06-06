# hibernate_servlet

Необходимо реализовать CRUD-приложение с использованием hibernate и сервлетов.

Описание задачи:

Приложение будет представлять из себя онлайн магазин, в котором будут покупатели, которые берут товар, и менеджеры, которые его пополняют, в зависимости от пути запроса будет выбираться роль:

Покупатель: http://locahost:8081/shop/user/* (buy, get, donate)
Менеджер: http://localhost:8081/shop/manager/* (get, update, add, delete)

Если покупатель делает покупку, необходимо убирать товар из таблицы products, или же наоборот добавлять, если менеджер делает запрос на пополнение товара.

При совершении покупки пользователь должен передавать свой firstName,lastName и password, например:

POST  http://locahost:8081/shop/user/buy 

{
	“first_name” : “Nikita”,
	“last_name”: “Chepelov”,
	“password” : “lotkasPoggerJopa”,
	“product_id” : 1
}

Структура БД следующая: 
sales (id, userId,  productId, addDate)
managers (id, firstName, lastName, password)
users (id, firstName, lastName, password, balance)
products (id, name, price, available)

Структура сущностей следующая:
User (Long id, String firstName, String lastName, String password, BigDecimal balance)
Manager (Long id, String firstName, String lastName, String password)
Sale (Long id, Long userId, Long productId, LocalDateTime addDate)
Product (Long id, String name, BigDecimal price,  Long available)

Структура проекта следующая: 
model
service
repository
controller
utils - по не обходимости
Подробнее структуру можно глянуть тут (https://github.com/Rentori/CRUD_Hibernate)

Необходимые инструменты разработки: 
Hibernate
Java Servlets 
Flyway
Lombok
Maven
JSON (для запросов по http)
MySql

Полезные ссылки: 

Hibernate 
https://proselyte.net/tutorials/hibernate-tutorial/
https://habr.com/post/271115/
https://habr.com/post/29694/
https://youtu.be/C-wEZjEOhWc
https://youtu.be/YzOTZTt-PR0
Servlets
https://metanit.com/java/javaee/4.1.php
https://habr.com/en/articles/334138/
Flyway
https://habr.com/en/companies/otus/articles/506788/
JSON
https://www.baeldung.com/java-org-json
https://habr.com/en/companies/otus/articles/687004/
HTTP
https://habr.com/en/articles/215117/
https://habr.com/en/articles/441150/


Рекомендации по разработке:
Желательно для работы с коллекциями использовать Stream API
Для связей между таблицами использовать отношения manyToOne, oneToMany, oneToOne (необходимо разобраться)
В контроллерах должна быть вся необходимая валидация входящях запросов, чтобы покупатель не мог совершать покупки, если не авторизировался, товара нет в наличии или у него на балансе нет денег, 

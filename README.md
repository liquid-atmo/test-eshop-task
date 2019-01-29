# test-eshop-task
Тестовое задание для разработчика Java
1.	Спроектировать модель предметной области для интернет-магазина
2.	Описать полученную модель на ER-диаграмме
3.	Применить полученную модель на библиотеке Hibernate, написать простой сценарий работы с моделью.

Комментарии
В данном задании проверяется общий подход к разработке и проектированию приложения. Комментарии, приведённые ниже, являются рекомендательными и необязательными. Кандидат должен предложить обоснованный вариант модели предметной области (определение задействованных типов объектов и их отношения между собой). 
Интернет-магазин продаёт товары. Товар содержит артикул (некий идентификатор), наименование, описание, фотографии товара. Товары сгруппированы по категориям (например, «Электроника», «Одежда»). Категории также могут быть вложенными (например, «Летняя одежда» находится внутри категории «Одежда»).
Товар имеет цену. Пользователь интернет-магазина кладёт определённое количество товаров в корзину и делает заказ. Также он может положить товары в корзину, закрыть браузер и вернуться к оформлению заказа на следующий день. Пользователь может положить только доступное на данный момент времени количество товара на складе. При создании заказа указывается адрес доставки и способ оплаты (наличные, банковская карта). Также обновляется (уменьшается) информация о доступном количестве товара на складе.
Для демонстрации работы модели предметной области нужно с помощью Hibernate написать следующий сценарий работы (достаточно консольной программки в методе main() ):
•	Найти любой доступный (есть в наличии на складе) товар по артикулу, положить его в корзину и сформировать заказ, указав адрес доставки и способ оплаты.
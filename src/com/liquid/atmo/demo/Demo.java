package com.liquid.atmo.demo;

import com.liquid.atmo.actions.*;

public class Demo {

	public static void main(String[] args) {
		
	// первично заполняем базу данных пользователями, продуктами 
	PopulateDbTables.populateDbTables();
	
	// пользователь с id 1
	// добавляем продукт в корзину
	AddProductToCart.addProductToCart(1, 1, 2);
	
	// пробуем добавить в корзину уже имеющийся там продукт
	
	AddProductToCart.addProductToCart(1, 1, 2);
	 
	// добавляем ещё один продукт в корзину
	AddProductToCart.addProductToCart(1, 2, 3);
	
	// и ещё один продукт в корзину
	AddProductToCart.addProductToCart(1, 3, 4);
	
	// пробуем добавить продукт количеством больше, чем в наличии на складе
	AddProductToCart.addProductToCart(1, 3, 15);
	
	
	
	// модифицируем количество первого продукта
	 ModifyProductQuantitytInCart.modifyProductQuantity(1, 1, 6);
	
	 
	 // удаляем продукт из корзины
	 DeleteProductFromCart.deleteProduct(1, 2);
	 
	 // подтверждаем заказ
	 ConfirmOrder.confirmOrder(1, "г. Самара улица Ленина д.2", "наличные");
	 
	// пользователь с id 2
	    // добавляем продукт в корзину
	    AddProductToCart.addProductToCart(2, 1, 2);
	    
	    // пробуем добавить в корзину уже имеющийся там продукт
	    
	    AddProductToCart.addProductToCart(2, 1, 1);
	     
	    // добавляем ещё один продукт в корзину
	    AddProductToCart.addProductToCart(2, 2, 3);
	    
	    // и ещё один продукт в корзину
	    AddProductToCart.addProductToCart(2, 3, 3);
	    
	    
	    
	    // модифицируем количество первого продукта
	     ModifyProductQuantitytInCart.modifyProductQuantity(2, 1, 2);
	    
	     
	     // удаляем продукт из корзины
	     DeleteProductFromCart.deleteProduct(2, 2);
	     
	     // подтверждаем заказ
	     
	     ConfirmOrder.confirmOrder(2, "г. Ульяновск улица Летчиков д.3", "кредитная карта");
	
	
	   
   }
}
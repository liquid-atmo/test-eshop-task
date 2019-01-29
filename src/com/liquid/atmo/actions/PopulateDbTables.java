package com.liquid.atmo.actions;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.liquid.atmo.erd.Category;
import com.liquid.atmo.erd.Customer;
import com.liquid.atmo.erd.Product;
import com.liquid.atmo.erd.WarehouseStock;

public class PopulateDbTables
{

    public static void populateDbTables()
    {
        SessionFactory factory = DbHelper.createSessionFactory();
        Session session = factory.getCurrentSession();

        try
        {
            System.out.println("Creating new customer...");
            Customer tempCustomer = new Customer();
            tempCustomer.setFirstName("Валера0");
            tempCustomer.setLastName("Желейный Конь");
            tempCustomer.setEmail("yyy@medium.com");

            Customer tempCustomer1 = new Customer();
            tempCustomer1.setFirstName("Валера1");
            tempCustomer1.setLastName("Желейный Медведь");
            tempCustomer1.setEmail("ucozz@medium.com");

            Customer tempCustomer2 = new Customer();
            tempCustomer2.setFirstName("Валера2");
            tempCustomer2.setLastName("Желтобрюх");
            tempCustomer2.setEmail("copozz@medium.com");

            System.out.println("Creating new category...");

            Category tempCategory = new Category();
            Category tempCategory1 = new Category();
            Category tempCategory2 = new Category();
            tempCategory.setName("Электроника");
            tempCategory1.setName("Одежда");
            tempCategory2.setName("Спортивные товары");

            System.out.println("Creating new product...");
            Product tempProduct = new Product();
            tempProduct.setName("телевизор");
            tempProduct.setDescription("24-дюймовый ЛСД, чёрный");
            tempProduct.setPrice(102.0d);

            Product tempProduct1 = new Product();
            tempProduct1.setName("куртка");
            tempProduct1.setDescription("зимняя, чёрная, размер XL");
            tempProduct1.setPrice(50.0d);

            Product tempProduct2 = new Product();
            tempProduct2.setName("штанга");
            tempProduct2.setDescription("тяжёлая, с олимпийским грифом");
            tempProduct2.setPrice(70.5d);

            // create warehouse stock object through Product
            tempProduct.setWarehouseStock(new WarehouseStock(10));
            tempProduct1.setWarehouseStock(new WarehouseStock(5));
            tempProduct2.setWarehouseStock(new WarehouseStock(7));

            session.beginTransaction();

            System.out.println("Saving the created objects");
            session.save(tempCustomer);
            session.save(tempCustomer1);
            session.save(tempCustomer2);
            session.save(tempCategory1);
            session.save(tempCategory2);
            session.save(tempCategory);

            tempProduct.setCategory(tempCategory);
            session.save(tempProduct);

            tempProduct1.setCategory(tempCategory1);
            session.save(tempProduct1);

            tempProduct2.setCategory(tempCategory2);
            session.save(tempProduct2);

            //commit transaction
            session.getTransaction().commit();

            System.out.println("Populating DB is Done!");
        }
        finally
        {
            session.close();
            factory.close();
        }
    }
}

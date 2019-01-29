package com.liquid.atmo.actions;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.liquid.atmo.erd.Cart;
import com.liquid.atmo.erd.CartProduct;
import com.liquid.atmo.erd.Customer;
import com.liquid.atmo.erd.Product;

public class AddProductToCart
{

    public static void addProductToCart(int customerId, int productId, int productQuantity)
    {
        SessionFactory factory = DbHelper.createSessionFactory();

        Session session = factory.getCurrentSession();

        try
        {
            session.beginTransaction();

            Customer customer = (Customer) session.get(Customer.class, customerId);

            // user's cart is created or retrieved if exists
            if (customer != null)
            {
                if (DbHelper.extractCartByCustomerId(session, customerId) == null)
                {
                    Cart theCart = new Cart();
                    theCart.setCustomer(customer);
                    session.save(theCart);
                }

                Cart theCart = DbHelper.extractCartByCustomerId(session, customerId);

                Product product = (Product) session.get(Product.class, productId);

                if (!DbHelper.isProductAlreadyInCart(productId, session))
                {

                    if (DbHelper.isEnoughQuantityInStock(productId, productQuantity, session))
                    {

                        // add product to the cart

                        CartProduct cartProduct = new CartProduct();
                        cartProduct.setCart(theCart);
                        cartProduct.setProduct(product);
                        cartProduct.setQuantity(productQuantity);

                        //save all 

                        System.out.println("Saving the created objects");

                        session.save(cartProduct);

                        DbHelper.calculateTheSum(session, theCart);

                        //commit transaction
                        session.getTransaction().commit();

                        System.out.println("Done!");

                    }
                    else
                    {
                        System.out.println("\n" + product.getName() +
                                " Недостаточное количество товара на складе.");
                    }
                }
                else
                {
                    System.out.println("\n" + product.getName() +
                            " Этот продукт уже есть в корзине.");
                }
            }
            else System.out.println("Такого пользователя не существует");

        }
        finally
        {
            session.close();
            factory.close();
        }
    }

}

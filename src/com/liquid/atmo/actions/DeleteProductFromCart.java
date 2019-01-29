package com.liquid.atmo.actions;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.liquid.atmo.erd.Cart;
import com.liquid.atmo.erd.CartProduct;
import com.liquid.atmo.erd.Customer;
import com.liquid.atmo.erd.Product;

public class DeleteProductFromCart
{

    public static void deleteProduct(int customerId, int productId)
    {
        SessionFactory factory = DbHelper.createSessionFactory();
        Session session = factory.getCurrentSession();

        try
        {
            session.beginTransaction();
            Customer customer = (Customer) session.get(Customer.class, customerId);

            if (customer != null)
            {
                Cart theCart = customer.getCart();
                int cartId = theCart.getId();

                Product product = (Product) session.get(Product.class, productId);

                if (DbHelper.isProductAlreadyInCart(productId, session))
                {

                    CartProduct cartProduct = DbHelper.extractCartProduct(productId, session, cartId);

                    System.out.println("Deleting the product");

                    session.remove(cartProduct);

                    // calculate the total sum for this cart after removing a product	                
                    DbHelper.calculateTheSum(session, theCart);

                    //commit transaction
                    session.getTransaction().commit();
                    System.out.println("Done!");

                }
                else
                {
                    System.out.println("\n" + product.getName() +
                            " Этого продукта нет в корзине");
                }
            }
            else System.out.println("Пользователь не найден");
        }
        finally
        {
            session.close();
            factory.close();
        }
    }

}

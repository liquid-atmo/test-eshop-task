package com.liquid.atmo.actions;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.liquid.atmo.erd.Cart;
import com.liquid.atmo.erd.CartProduct;
import com.liquid.atmo.erd.Customer;
import com.liquid.atmo.erd.Product;

public class ModifyProductQuantitytInCart
{

    public static void modifyProductQuantity(int customerId, int productId, int productQuantity)
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
                    if (DbHelper.isEnoughQuantityInStock(productId, productQuantity, session))
                    {
                        // найти cartproduct по cart id and product id
                        CartProduct result = DbHelper.extractCartProduct(productId, session, cartId);
                        // внести в неё изменения
                        result.setQuantity(productQuantity);
                        session.save(result);

                        DbHelper.calculateTheSum(session, theCart);

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

package com.liquid.atmo.actions;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.liquid.atmo.erd.Cart;
import com.liquid.atmo.erd.CartProduct;
import com.liquid.atmo.erd.Category;
import com.liquid.atmo.erd.Customer;
import com.liquid.atmo.erd.Order;
import com.liquid.atmo.erd.OrderProduct;
import com.liquid.atmo.erd.Product;
import com.liquid.atmo.erd.WarehouseStock;

public class DbHelper
{

    protected static SessionFactory createSessionFactory()
    {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(WarehouseStock.class)
                .addAnnotatedClass(Cart.class)
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(CartProduct.class)
                .addAnnotatedClass(OrderProduct.class)
                .buildSessionFactory();
        return factory;
    }

    protected static boolean isEnoughQuantityInStock(int productId, int productQuantity, Session session)
    {
        boolean isEnoughQuantityInStock = false;
        Query q = session
                .createQuery("from WarehouseStock w where w.product.id =:id "
                        + "and w.productQuantity >=:neededQuantity");
        q.setParameter("id", productId);
        q.setParameter("neededQuantity", productQuantity);
        List<WarehouseStock> stockForGivenProduct = q.getResultList();
        if (!stockForGivenProduct.isEmpty())
            isEnoughQuantityInStock = true;
        return isEnoughQuantityInStock;
    }

    protected static boolean isProductAlreadyInCart(int productId, Session session)
    {
        boolean isProductAlreadyInCart = false;
        Query q = session.createQuery("from CartProduct cp where cp.product.id =:id");
        q.setParameter("id", productId);
        List<CartProduct> productExistInCart = q.getResultList();
        if (!productExistInCart.isEmpty())
            isProductAlreadyInCart = true;
        return isProductAlreadyInCart;
    }

    protected static void calculateTheSum(Session session, Cart theCart)
    {
        Query q;
        // calculate the total sum for this cart
        double totalSum = 0;
        int cartId = theCart.getId();
        q = session.createQuery("from CartProduct cp where cp.cart.id =:id");
        q.setParameter("id", cartId);
        List<CartProduct> productsInCart = q.getResultList();
        for (CartProduct cp : productsInCart)
        {
            totalSum += cp.getProduct().getPrice() * cp.getQuantity();
        }
        theCart.setSum(totalSum);
    }

    protected static CartProduct extractCartProduct(int productId, Session session, int cartId)
    {
        Query q = session
                .createQuery("from CartProduct cp where"
                        + " cp.product.id =:id1 and cp.cart.id =:id2");
        q.setParameter("id1", productId);
        q.setParameter("id2", cartId);
        List<CartProduct> result = q.getResultList();
        if (result.isEmpty())
            return null;

        CartProduct cartProduct = result.get(0);
        return cartProduct;
    }

    protected static Cart extractCartByCustomerId(Session session, int customerId)
    {
        Query q = session
                .createQuery("from Cart c where"
                        + " c.customer.id =:id1");
        q.setParameter("id1", customerId);
        List<Cart> result = q.getResultList();
        if (result.isEmpty())
            return null;

        return result.get(0);
    }

    public DbHelper()
    {
        super();
    }

}
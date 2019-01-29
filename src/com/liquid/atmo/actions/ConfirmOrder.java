package com.liquid.atmo.actions;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.liquid.atmo.erd.Cart;
import com.liquid.atmo.erd.CartProduct;
import com.liquid.atmo.erd.Customer;
import com.liquid.atmo.erd.Order;
import com.liquid.atmo.erd.OrderProduct;
import com.liquid.atmo.erd.Product;
import com.liquid.atmo.erd.WarehouseStock;

public class ConfirmOrder
{
    public static void confirmOrder(int customerId, String address, String paymentType)
    {
        SessionFactory factory = DbHelper.createSessionFactory();
        Session session = factory.getCurrentSession();

        try
        {
            session.beginTransaction();

            Customer theCustomer = (Customer) session.get(Customer.class, customerId);
            Cart theCart = theCustomer.getCart();
            if (theCart != null)
            {
                // создать новый заказ
                Order theOrder = createNewOrder(address, paymentType, session, theCustomer);

                // извлечь из корзины все продукты

                List<CartProduct> rawList = theCart.getCartProducts();

                // проверить, достаточно ли остатка товара для каждого продукта
                // и если да, записать его в заказ.
                for (CartProduct cp : rawList)
                {
                    Product theProduct = cp.getProduct();
                    int productId = cp.getProduct().getId();
                    int productQuantity = cp.getQuantity();
                    if (DbHelper.isEnoughQuantityInStock(productId, productQuantity, session))
                    {
                        // add product to the order
                        OrderProduct orderProduct = new OrderProduct();
                        orderProduct.setOrder(theOrder);
                        orderProduct.setProduct(theProduct);
                        orderProduct.setQuantity(productQuantity);
                        session.save(orderProduct);
                        theOrder.addOrderProduct(orderProduct);

                        System.out.println("Saving a product to the order");

                    }
                    else System.out.println("недостаточное количество товара " + theProduct.getName());
                }

                System.out.println("\nЗаказ до сохранения " + theOrder);

                int productListSizeInCart = theCart.getCartProducts().size();
                int productListSizeInOrder = theOrder.getOrderProducts().size();

                // дополнительная проверка на равенство кол-ва товаров в корзине и заказе
                if (productListSizeInCart == productListSizeInOrder)
                {
                    session.save(theOrder);
                    session.delete(theCart);

                    // уменьшить складские остатки
                    for (OrderProduct op : theOrder.getOrderProducts())
                    {
                        WarehouseStock ws = op.getProduct().getWarehouseStock();
                        ws.setProductQuantity(ws.getProductQuantity() - op.getQuantity());
                        session.save(ws);

                    }
                    session.getTransaction().commit();
                    System.out.println("Done!");
                }

                else System.out.println("Недостаточное количество какого-то товара на складе");
            }
            else System.out.println("Отсутствует корзина у пользователя");
        }

        finally
        {
            session.close();
            factory.close();
        }
    }

    private static Order createNewOrder(String address, String paymentType, Session session, Customer theCustomer)
    {
        Order theOrder = new Order();
        theOrder.setCustomer(theCustomer);
        theOrder.setAddress(address);
        theOrder.setPaymentType(paymentType);
        session.save(theOrder);
        return theOrder;
    }

}

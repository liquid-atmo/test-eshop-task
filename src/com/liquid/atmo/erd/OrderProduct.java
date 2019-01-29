
package com.liquid.atmo.erd;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="product_order")
public class OrderProduct
{
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name = "order_id")
    private Order order;
        
    
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name = "product_id")
    private Product product;
  
    @Column(name="quantity")
    private int quantity;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Order getOrder()
    {
        return order;
    }

    public void setOrder(Order order)
    {
        this.order = order;
    }

    public Product getProduct()
    {
        return product;
    }

    public void setProduct(Product product)
    {
        this.product = product;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    
    @Override
    public String toString()
    {
        return "[Заказ Id=" + order.getId() + 
                ", product=" + product.getName() + ", quantity=" + quantity + "]";
    }
  
     
    
}

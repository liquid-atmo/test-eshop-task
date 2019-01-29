
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
@Table(name="product_cart")
public class CartProduct
{
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name = "cart_id")
    private Cart cart;
        
    
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

    public Cart getCart()
    {
        return cart;
    }

    public void setCart(Cart cart)
    {
        this.cart = cart;
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
        return "[Корзина Id=" + cart.getId() + 
                ", product=" + product.getName() + ", quantity=" + quantity + "]";
    }
  
     
    
}

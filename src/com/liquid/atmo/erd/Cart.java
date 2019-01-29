package com.liquid.atmo.erd;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="cart")
public class Cart {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
    @Column(name = "cart_date")
	    @Temporal(TemporalType.DATE)
    private Date cartDate;
    
    @Column(name = "total_sum")
    private Double sum;
    
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name="customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartProduct> cartProducts = new ArrayList<CartProduct>();
	
	
	public Cart() {
	    setCartDate(new Date());
		
	}

    public int getId()
    {
        return id;
    }


    public void setId(int id)
    {
        this.id = id;
    }

    public Date getCartDate()
    {
        return cartDate;
    }

    public void setCartDate(Date cartDate)
    {
        this.cartDate = cartDate;
    }

    
    public Double getSum()
    {
        return sum;
    }

    public void setSum(Double sum)
    {
        this.sum = sum;
    }
    

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public List<CartProduct> getCartProducts()
    {
        return cartProducts;
    }

    public void setCartProducts(List<CartProduct> cartProducts)
    {
        this.cartProducts = cartProducts;
    }

    public void addCartProduct(CartProduct cartProduct) {
        this.cartProducts.add(cartProduct);
    }

    @Override
    public String toString()
    {
        return "Cart [id=" + id + ", cartDate=" + cartDate + ", number of products=" + cartProducts.size() + "]";
    }  




    

	
    
	
	
	}

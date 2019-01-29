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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="online_order")
public class Order {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
    @Column(name = "order_date")
	    @Temporal(TemporalType.DATE)
    private Date orderDate;
    
    @Column(name = "delivery_address")
    private String address;
    
    @Column(name = "payment_type")
    private String paymentType;

    @ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name="customer_id")
    private Customer customer;
    
    @OneToMany(mappedBy = "order")
    private List<OrderProduct> orderProducts = new ArrayList<OrderProduct>();
	
	public Order() {
	    setDate(new Date());
	    
	}

    public int getId()
    {
        return id;
    }


    public void setId(int id)
    {
        this.id = id;
    }

    public Date getDate()
    {
        return orderDate;
    }

    public void setDate(Date orderDate)
    {
        this.orderDate = orderDate;
    }

    public Date getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(Date orderDate)
    {
        this.orderDate = orderDate;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public String getPaymentType()
    {
        return paymentType;
    }

    public void setPaymentType(String paymentType)
    {
        this.paymentType = paymentType;
    }
    
    
    public List<OrderProduct> getOrderProducts()
    {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts)
    {
        this.orderProducts = orderProducts;
    }
    
    public void addOrderProduct(OrderProduct orderProduct) {
        this.orderProducts.add(orderProduct);
        orderProduct.setOrder(this);
    }

    @Override
    public String toString()
    {
        return "Order [id=" + id + ", orderDate=" + orderDate + ", number of products=" + orderProducts.size() + "]";
    }  
    
   	
}

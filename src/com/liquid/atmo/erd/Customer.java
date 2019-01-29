package com.liquid.atmo.erd;

import java.util.ArrayList;
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


@Entity
@Table(name="customer")
public class Customer {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;

	@Column(name="email")
	private String email;
	
	@OneToOne (mappedBy ="customer", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private Cart cart;
	
	@OneToMany (mappedBy = "customer",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private List<Order> orders;
	
		
	public Customer() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
    
    

    public Cart getCart()
    {
        return cart;
    }

    public void setCart(Cart cart)
    {
        this.cart = cart;
    }
    
    

    public List<Order> getOrders()
    {
        return orders;
    }

    public void setOrders(List<Order> orders)
    {
        this.orders = orders;
    }

    @Override
    public String toString()
    {
        return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + 
                lastName + ", email=" + email + ", cart=" + cart + "]";
    }

	
	}

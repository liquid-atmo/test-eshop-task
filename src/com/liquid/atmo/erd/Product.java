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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="product")
public class Product {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	
	
	@Column(name="name")
	private String name;

	@Column(name="description")
	private String description;
	
	@Column(name="price")
    private Double price;
	
	@ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE,
	        CascadeType.DETACH, CascadeType.REFRESH	})
    @JoinColumn(name="product_category_id")
    private Category category;
	
	@OneToOne (cascade = {CascadeType.ALL})
	@JoinColumn(name="warehouse_stock_id")
    private WarehouseStock warehouseStock;
	
	@OneToMany(mappedBy = "product")
    private List<CartProduct> cartProducts = new ArrayList<CartProduct>();
	
	@OneToMany(mappedBy = "product")
    private List<OrderProduct> orderProducts = new ArrayList<OrderProduct>();
		
	public Product() {
		
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public Category getCategory()
    {
        return category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }

   
    public String getName()
    {
        return name;
    }

    public void setName(String productName)
    {
        this.name = productName;
    }



    public void setWarehouseStock(WarehouseStock ws) {
        warehouseStock = ws;
    }
    
    public WarehouseStock getWarehouseStock()
    {
        return warehouseStock;
    }
    
    
    
    
    public String getDescription()
    {
        return description;
    }




    public void setDescription(String description)
    {
        this.description = description;
    }




    public Double getPrice()
    {
        return price;
    }


    public void setPrice(Double price)
    {
        this.price = price;
    }

    public List<CartProduct> getCartProducts()
    {
        return cartProducts;
    }

    public void setCartProducts(List<CartProduct> cartProducts)
    {
        this.cartProducts = cartProducts;
    }

    
    public List<OrderProduct> getOrderProducts()
    {
        return orderProducts;
    }


    public void setOrderProducts(List<OrderProduct> orderProducts)
    {
        this.orderProducts = orderProducts;
    }


    public void addCartProduct(CartProduct cartProduct) {
        this.cartProducts.add(cartProduct);
    }
    
    public void addOrderProduct(OrderProduct orderProduct) {
        this.orderProducts.add(orderProduct);
    }


    @Override
    public String toString()
    {
        return "Product [id=" + id + ", name=" + name + ", description=" + 
    description + ", price=" + price + ", category=" + category + 
    ", warehouseStock=" + warehouseStock  + "]";
    }  




 




   
	

	
    
	
	
	}

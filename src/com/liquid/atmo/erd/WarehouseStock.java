package com.liquid.atmo.erd;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="warehouse_stock")
public class WarehouseStock {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@OneToOne (mappedBy="warehouseStock", cascade = {CascadeType.ALL})
	private Product product;
	
	@Column(name="product_quantity")
	private int productQuantity;

	
		
	public WarehouseStock() {
		
	}

public WarehouseStock(int productQuantity) {
        this.productQuantity = productQuantity;
    }

	
	public Product getProduct()
    {
        return product;
    }



    public void setProduct(Product product)
    {
        this.product = product;
    }



    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public int getProductQuantity()
    {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity)
    {
        this.productQuantity = productQuantity;
    }

    @Override
    public String toString()
    {
        return "WarehouseStock [id=" + id + ", product=" + product.getName() + ", productQuantity=" + productQuantity + "]";
    }
	

	
    
	
	
	}

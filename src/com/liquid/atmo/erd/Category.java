package com.liquid.atmo.erd;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="product_category")
public class Category {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="category_name")
	private String name;
	
	
	
	@Column(name="parent_id")
	private Integer parentId;
	
	@OneToMany (mappedBy = "category",
	        cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private List<Product> products;


	
	public Category() {
		
	}
	
    public Category(Integer parentId, String name) {
        this.parentId = parentId;
        this.name = name;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public String getName()
    {
        return name;
    }

    public void setName(String categoryName)
    {
        this.name = categoryName;
    }

    public int getParentId()
    {
        return parentId;
    }

    public void setParentId(int parentId)
    {
        this.parentId = parentId;
    }
    
    


    public List<Product> getProducts()
    {
        return products;
    }

    public void setProducts(List<Product> products)
    {
        this.products = products;
    }
    
    // convenience method for adding products to a given category
    
    public void add(Product product) {
        if (products == null)
            products = new ArrayList<>();
            
        products.add(product);
        product.setCategory(this);
    }
    
    

    @Override
    public String toString()
    {
        return "Category [id=" + id + ", categoryName=" + name + ", parentId=" + parentId + "]";
    }
	


    
	
	
	}

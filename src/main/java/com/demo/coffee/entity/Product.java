package com.demo.coffee.entity;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Integer productID;
    @Column(name = "id_category")
    private Integer categoryID;

    @Column(name = "product_name")
    @NotNull
    @NotBlank(message = "Product's name cannot be null")
    @Size(min = 1, max = 300)
    private String productName;

    @NotNull
    @Min(0)
    private int price;
    
    @NotNull
    @Min(0)
    private int cost;
    
    @Column(name = "product_desc")
    @NotNull
    @NotBlank(message = "Product's description cannot be null")
    @Size(min = 1, max = 1000)
    private String description;
    
    @Column(name = "photo", nullable = true, length = 64)
    private String photo;
    
    @OneToMany(mappedBy = "product")
    private Set<ReceiptItem> receiptItem;

    public Set<ReceiptItem> getReceiptItem() {
		return receiptItem;
	}
	public void setReceiptItem(Set<ReceiptItem> receiptItem) {
		this.receiptItem = receiptItem;
	}
	public Product(Integer productID, Integer categoryID, String productName, int price, int cost, String description, String photo) {
        this.productID = productID;
        this.categoryID = categoryID;
        this.productName = productName;
        this.price = price;
        this.cost = cost;
        this.description = description;
        this.photo = photo;
    }
    public Product() {}

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getPrice() {
        return price;
    }
    
    public int getCost() {
        return cost;
    }
    
    public String getFormattedPrice() {
        return String.format("VND %.2f", (price * 100.00) / 100.00);
    }
    
    public String getFormattedCost() {
        return String.format("VND %.2f", (cost * 100.00) / 100.00);
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getPhoto() {
        return photo;
    }
    
    @Transient
    public String getPhotosImagePath() {
        if (photo == null || productID == null) return null;
         
        return "/product-photos/" + productID + "/" + photo;
    }
}

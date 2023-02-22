package com.demo.coffee.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author TVD
 */
@Entity(name = "receipt_item")
public class ReceiptItem implements Serializable{
	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long receiptItemId;
    @ManyToOne
    @JoinColumn(name = "receiptId")
    private Receipt receipt;
    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;
    private int receiptItemQuantity;
    private double receiptItemPrice;
    
    public long getReceiptItemId() {
		return receiptItemId;
	}
	public void setReceiptItemId(long receiptItemId) {
		this.receiptItemId = receiptItemId;
	}
	public Receipt getReceipt() {
		return receipt;
	}
	public void setReceipt(Receipt receipt2) {
		this.receipt = receipt2;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product2) {
		this.product = product2;
	}
	public int getReceiptItemQuantity() {
		return receiptItemQuantity;
	}
	public void setReceiptItemQuantity(int receiptItemQuantity) {
		this.receiptItemQuantity = receiptItemQuantity;
	}
	public double getReceiptItemPrice() {
		return receiptItemPrice;
	}
	public void setReceiptItemPrice(double receiptItemPrice) {
		this.receiptItemPrice = receiptItemPrice;
	}
}

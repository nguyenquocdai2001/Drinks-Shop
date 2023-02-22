package com.demo.coffee.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.NoArgsConstructor;


@NoArgsConstructor
@Entity
@Table(name = "receipt")
public class Receipt implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "receiptId")
    private long receiptId;
    
    @Column(name = "receiptName")
    private String receiptName;
    
    @Column(name = "receiptMail")
    private String receiptMail;
    
    @Column(name = "receiptPhone")
    private String receiptPhone;
    
    @Column(name = "receiptAddress")
    private String receiptAddress;
    
    @Column(name = "receiptUsername")
    private String receiptUsername;
    
    @Column(name = "receiptTotal")
    private double receiptTotal;
    
    @OneToMany(mappedBy = "receipt")
    private Set<ReceiptItem> receiptItem;

    
	public long getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(long receiptId) {
		this.receiptId = receiptId;
	}
	public String getReceiptName() {
		return receiptName;
	}
	public void setReceiptName(String receiptName) {
		this.receiptName = receiptName;
	}
	public String getReceiptMail() {
		return receiptMail;
	}
	public void setReceiptMail(String receiptMail) {
		this.receiptMail = receiptMail;
	}
	public String getReceiptAddress() {
		return receiptAddress;
	}
	public void setReceiptAddress(String receiptAddress) {
		this.receiptAddress = receiptAddress;
	}
	public String getReceiptUsername() {
		return receiptUsername;
	}
	public void setReceiptUsername(String receiptUsername) {
		this.receiptUsername = receiptUsername;
	}
	public Set<ReceiptItem> getReceiptItem() {
		return receiptItem;
	}
	public void setReceiptItem(Set<ReceiptItem> receiptItem) {
		this.receiptItem = receiptItem;
	}
	public double getReceiptTotal() {
		return receiptTotal;
	}
	public void setReceiptTotal(double receiptTotal) {
		this.receiptTotal = receiptTotal;
	}
	public String getReceiptPhone() {
		return receiptPhone;
	}
	public void setReceiptPhone(String receiptPhone) {
		this.receiptPhone = receiptPhone;
	}
   
}

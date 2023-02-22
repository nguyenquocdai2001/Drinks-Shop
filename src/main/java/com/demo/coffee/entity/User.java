package com.demo.coffee.entity;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
    
    private String confirmpassword;
    
    @Column(name = "fullname")
    private String fullname;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "gender")
    private int gender;
    
    @Column(name = "address")
    private String address;
    
    
    @Override
	public String toString(){
		return "id = " + id + ", username=" + username + ", fullname=" + fullname + ", email="+email +", phone = "+ phone +", gender = "+ gender +", address" + address;
	}
    
    public User(Integer id, String username, String password, String confirmpassword, String fullname, String email, String phone, int gender,
			String address) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.confirmpassword = confirmpassword;
		this.fullname = fullname;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
		this.address = address;
	}
    
    public User(String username, String password, String confirmpassword, String fullname, String email, String phone, int gender,
			String address) {
		super();
		this.username = username;
		this.password = password;
		this.confirmpassword = confirmpassword;
		this.fullname = fullname;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
		this.address = address;
	}
    public User(String username,  String fullname, String email, String phone,
			String address) {
		super();
		this.username = username;
		this.fullname = fullname;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}
    
    public User(String username,  String password, String confirmpassword) {
		super();
		this.username = username;
		this.confirmpassword = confirmpassword;
		this.password = password;
	}
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
    
    
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return fullname;
	}

	public void setName(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}


}
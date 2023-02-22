package com.demo.coffee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.ui.Model;

import com.demo.coffee.entity.User;


public interface UserService{

	void deleteAll();

	void deleteAll(List<User> entities);

	void deleteAllById(Iterable<? extends Integer> username);

	void delete(User entity);

	void deleteById(Integer id);

	long count();

	List<User> findAllById(List<Integer> ids);

	List<User> findAll();

	boolean existsById(Integer id);

	Optional<User> findById(Integer id);

	List<User> saveAll(List<User> entities);

	User save(User entity);
	

	boolean checkLogin(String username, String password, Model model);
	
	Optional<User> findByUsername(String username);
	

	boolean checkRegister(String username, String password, String confirmpassword, String fullname, String email, String phone,int gender, String addess
			, Model model);
	
	boolean editProfile(String username,
			String email, String phone, String address, Model model);
	
	boolean changePass(String username,
			String OldPassword, String email, String NewPassword,String confirmNewPassword, Model model);
	

}

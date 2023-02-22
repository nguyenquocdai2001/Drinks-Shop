package com.demo.coffee.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


import com.demo.coffee.entity.User;
import com.demo.coffee.repository.UserRepository;
import com.demo.coffee.validator.Message;


@Service("userDetailsService")
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository userRepository;
	
    
	@Override
	public User save(User entity) {
		return userRepository.save(entity);
	}

	@Override
	public List<User> saveAll(List<User> entities) {
		return (List<User>)userRepository.saveAll(entities);
	}

	@Override
	public Optional<User> findById(Integer id) {
		return userRepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return userRepository.existsById(id);
	}

	@Override
	public List<User> findAll() {
		return (List<User>)userRepository.findAll();
	}

	@Override
	public List<User> findAllById(List<Integer> id) {
		return (List<User>)userRepository.findAllById(id);
	}

	@Override
	public long count() {
		return userRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		userRepository.deleteById(id);
	}

	@Override
	public void delete(User entity) {
		userRepository.delete(entity);
	} 

	@Override
	public void deleteAllById(Iterable<? extends Integer> id) {
		userRepository.deleteAllById(id);
	}

	@Override
	public void deleteAll(List<User> entities) {
		userRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		userRepository.deleteAll();
	}
	
	@Override
	public boolean checkLogin(String username, String password, Model model) {
		String encryptedpassword = null;  
		try   
        {  
            /* MessageDigest instance for MD5. */  
            MessageDigest m = MessageDigest.getInstance("MD5");  
              
            /* Add plain-text password bytes to digest using MD5 update() method. */  
            m.update(password.getBytes());  
              
            /* Convert the hash value into bytes */   
            byte[] bytes = m.digest();  
              
            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */  
            StringBuilder s = new StringBuilder();  
            for(int i=0; i< bytes.length ;i++)  
            {  
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
            }  
              
            /* Complete hashed password in hexadecimal format */  
            encryptedpassword = s.toString();  
        }   
        catch (NoSuchAlgorithmException e)   
        {  
            e.printStackTrace();  
        }  
		// TODO Auto-generated method stub
		Optional<User> optionalUser = findByUsername(username);
		if(username.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please enter username"));
		}
		else if(password.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please enter password"));
		}
		else if(optionalUser.isPresent() && !(optionalUser.get().getPassword().equals(encryptedpassword))) {
			model.addAttribute("message", new Message("warning", "Username or Password is not correct"));
		}
		else if(!(optionalUser.isPresent())) {
			model.addAttribute("message", new Message("warning", "Username is not exist"));
		}
		if(optionalUser.isPresent() && optionalUser.get().getPassword().equals(encryptedpassword)) {
			return true;
		}
		return false;
	}
	
	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Override
	public boolean checkRegister(String username, String password, String confirmpassword, String name,
			String email, String phone,int gender, String address, Model model) {
		
		Optional<User> optionalUser = findByUsername(username);
		if(username.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please enter username"));
		}
		else if(password.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please enter password"));
		}
		else if(password.length() < 6) {
			model.addAttribute("message", new Message("warning", "Please enter password at least 6 digits"));
		}
		else if(!(password.equals(confirmpassword))) {
			model.addAttribute("message", new Message("warning", "Password is different from Confirm Password"));
		}
		else if(confirmpassword.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please enter confirm password"));
		}
		else if(name.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please enter fullname"));
		}
		else if(email.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please enter email"));
		}
		else if(phone.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please enter phone number"));
		}
		else if(phone.length() != 10) {
			model.addAttribute("message", new Message("warning", "Wrong type of phone number"));
		}
		else if(address.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please enter address"));
		}
		else if(gender != 1 && gender != 0) {
			model.addAttribute("message", new Message("warning", "Please choose gender"));
		}
		else if(optionalUser.isPresent()) {
			model.addAttribute("message", new Message("warning", "Username is existed"));
		}
		else {
			model.addAttribute("message", new Message("success", "Register successfully"));
			return true;
		}
		return false;
	}
	
	@Override
	public boolean editProfile(String username, String email, String phone, String address, Model model) {
		
		Optional<User> optionalUser = findByUsername(username);
		if(username.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please enter username"));
		}
		else if(email.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please do not empty email"));
		}
		else if(phone.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please do not empty phone number"));
		}
		else if(phone.length() != 10) {
			model.addAttribute("message", new Message("warning", "Wrong type of phone number"));
		}
		else if(address.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please do not empty address"));
		}
		else {
			model.addAttribute("message", new Message("success", "Edit successfully"));
			return true;
		}
		return false;
	}
	
	@Override
	public boolean changePass(String username, String OldPassword, String NewPassword, String confirmNewPassword, String email, Model model) {
		String encryptedpassword = null;  
		try   
        {  
            /* MessageDigest instance for MD5. */  
            MessageDigest m = MessageDigest.getInstance("MD5");  
              
            /* Add plain-text password bytes to digest using MD5 update() method. */  
            m.update(OldPassword.getBytes());  
              
            /* Convert the hash value into bytes */   
            byte[] bytes = m.digest();  
              
            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */  
            StringBuilder s = new StringBuilder();  
            for(int i=0; i< bytes.length ;i++)  
            {  
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
            }  
              
            /* Complete hashed password in hexadecimal format */  
            encryptedpassword = s.toString();  
        }   
        catch (NoSuchAlgorithmException e)   
        {  
            e.printStackTrace();  
        }  

		Optional<User> optionalUser = findByUsername(username);
		
		if(username.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please enter username"));
		}
		else if(OldPassword.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please enter old password"));
		}
		else if(NewPassword.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please enter new password"));
		}
		else if(NewPassword.length() < 6) {
			model.addAttribute("message", new Message("warning", "Please enter new password at least 6 digits"));
		}
		else if(confirmNewPassword.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please enter confirm new password"));
		}
		else if(email.length() < 1) {
			model.addAttribute("message", new Message("warning", "Please enter email"));
		}
		else if(optionalUser.isPresent() && !(optionalUser.get().getEmail().equals(email))) {
			model.addAttribute("message", new Message("warning", "Email is not correct"));
		}
		else if(optionalUser.isPresent() && !(optionalUser.get().getPassword().equals(encryptedpassword))) {
			model.addAttribute("message", new Message("warning", "Username or Old password is not correct"));
		}
		else if(!(NewPassword.equals(confirmNewPassword))) {
			model.addAttribute("message", new Message("warning", "New Password is different from Confirm Password"));
		}
		else if(!(optionalUser.isPresent())) {
			model.addAttribute("message", new Message("warning", "Username is not exist"));
		}
		else {
			model.addAttribute("message", new Message("success", "Change password successfully"));
			return true;
		}
		return false;
		
	}

	

}

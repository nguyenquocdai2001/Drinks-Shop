package com.demo.coffee.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.coffee.entity.Product;
import com.demo.coffee.entity.User;
import com.demo.coffee.repository.CategoryRepository;
import com.demo.coffee.repository.ProductRepository;
import com.demo.coffee.repository.UserRepository;
import com.demo.coffee.service.UserService;

import javax.validation.Valid;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired //Inject "userRepository" - Dependency Injection
    private UserRepository userRepository;
	
	@Autowired //Inject "categoryRepository" - Dependency Injection
	private CategoryRepository categoryRepository;

	@Autowired
    ProductRepository productRepository;
	
	@GetMapping("register")
	public String addOrEdit(Model model) {
		User u = new User();
		model.addAttribute("USER", u);
		//model.addAttribute("ACTION", "saveOrUpdate");
		return "register-user";
	}
//-----------------------------------------------------Register-------------------------------------------------------------	
	@PostMapping("/checkRegister")
	public String checkRegister(@RequestParam(required=false, name = "username") String username, @RequestParam(required=false, name = "password") String password, 
			@RequestParam(required=false, name = "confirmpassword") String confirmpassword, @RequestParam(required=false, name = "name") String name,
			@RequestParam(required=false, name = "email") String email, @RequestParam(required=false, name = "phone") String phone, 
			@RequestParam(required=false, name = "gender") int gender, @RequestParam(required=false, name = "address") String address, Model model) {
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
		if(userService.checkRegister(username, password, confirmpassword, name, email, phone, gender, address, model)) {
			System.out.println("Register thanh cong");
			//session.setAttribute("NEWUSER", username);
			User u = new User(username, encryptedpassword, encryptedpassword, name, email, phone, gender, address);
			userService.save(u);
			model.addAttribute("USER", u);
			return "register-user";
		}
		else {
			System.out.println("Register that bai");
		}
		//model.addAttribute("ACTION", "saveOrUpdate");
		return "register-user";
	}
	
	@PostMapping("/saveOrUpdate")
	public String saveOrUpdate(Model model, @ModelAttribute("USER") User user) {
		userService.save(user);
		return "register-user";
	}
//-------------------------------------------------------list user----------------------------------------------------------------	
	@RequestMapping("list")
	public String list(Model model, HttpSession session) {
		if((session.getAttribute("USERNAME").equals("nguyenquocdai"))) {
			System.out.println(session.getAttribute("USERNAME"));
			model.addAttribute("USER", userService.findAll());
			model.addAttribute("helloUser", session.getAttribute("USERNAME"));
			model.addAttribute("idUser", userService.findByUsername(session.getAttribute("USERNAME").toString()).get());
			return "view-user";
		}
		return "redirect:/products/getAllProducts";
	}
//---------------------------------------------------------edit and delete user--------------------------------------------------------
	@RequestMapping("/edit/{id}")
	public String edit(Model model, @PathVariable(name = "id") Integer id) {
		Optional<User> u = userService.findById(id);
		if(u.isPresent()) {
			model.addAttribute("USER", u.get());
		}
		else {
			model.addAttribute("USER", new User());
		}
		
		model.addAttribute("ACTION", "/saveOrUpdate");
		return "register-user";
	}
	
	@RequestMapping("/delete/{username}")
	public String delete(Model model, @PathVariable(name = "username") Integer id) {
		userService.deleteById(id);
		model.addAttribute("USER", userService.findAll());
		return "view-user";
	}
	
	//--------------------------------------------------------Login--------------------------------------------------
	@RequestMapping("/login")
	public String showLogin(Model model, HttpSession session) {
		if((session.getAttribute("USERNAME")).equals("nguyenquocdai")) {
			model.addAttribute("USER", userService.findAll());
			return "view-user";
		}
		return "login";
	}
	
	@PostMapping("/checklogin")
	public String checkLogin(Model model, @RequestParam("username") String username, 
			@RequestParam("password") String password, HttpSession session){
		if(userService.checkLogin(username, password, model)) {
			System.out.println("login thanh cong");
			session.setAttribute("USERNAME", username);
			if(!((session.getAttribute("USERNAME")).toString().equals("nguyenquocdai"))) {
				System.out.println(session.getAttribute("USERNAME") + " 1");
				System.out.println("login voi tu cach khach hang");
				return "redirect:/products/getAllProducts";
			}
			else {
				System.out.println(session.getAttribute("USERNAME") + " 0");
				model.addAttribute("USER", userService.findAll());
				model.addAttribute("helloUser", session.getAttribute("USERNAME"));
				model.addAttribute("idUser", userService.findByUsername(username).get());
				return "view-user";
			}
		}
		else {
			System.out.println("login that bai");
		}
		return "login";
	}
//----------------------------------------------------------------Logout----------------------------------------------------------------------	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.setAttribute("USERNAME", "shut down session");
		System.out.println("Logout thanh cong");
		System.out.println(session.getAttribute("USERNAME"));
		return "login";
	}
	
//---------------------------------------------------------------Profile----------------------------------------------------------------------
	//return name of "jsp file"
    //http:localhost:8085
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getAllUsers(ModelMap modelMap) {
        //data sent to jsp => ModelMap
        //send data from Controller to View
        //modelMap.addAttribute("name", "Hao");
        //modelMap.addAttribute("age", 21);
        Iterable<User> users = userRepository.findAll();
        modelMap.addAttribute("users", users);
        return "user"; //"user.jsp"
    }

    @RequestMapping(value = "/getUserByUserID/{id}", method = RequestMethod.GET)
    public String getUserByUserID(ModelMap modelMap, @PathVariable Integer id, HttpSession session) {
        Optional<User> users = userService.findById(id);
        if(users.isPresent()) {
        	modelMap.addAttribute("users", users.get());
        }
        
        modelMap.addAttribute("helloUser", session.getAttribute("USERNAME"));
        modelMap.addAttribute("idUser", userService.findByUsername(session.getAttribute("USERNAME").toString()).get());
        return "userView"; //"userShow.jsp"
    }
    
    @RequestMapping(value = "/Profile/{id}", method = RequestMethod.GET)
    public String Profile(ModelMap modelMap, @PathVariable Integer id, HttpSession session) {
        Optional<User> users = userService.findById(id);
        if(users.isPresent()) {
        	modelMap.addAttribute("users", users.get());
        }
        
        modelMap.addAttribute("helloUser", session.getAttribute("USERNAME"));
        modelMap.addAttribute("idUser", userService.findByUsername(session.getAttribute("USERNAME").toString()).get());
        return "Profile"; //"userShow.jsp"
    }

    
    @PostMapping("/editUser")
    public String editUser(@RequestParam(required=false, name = "username") String username, 
			@RequestParam(required=false, name = "email") String email, 
			@RequestParam(required=false, name = "address") String address,
			@RequestParam(required=false, name = "phone") String phone,
			@Valid @ModelAttribute("user") User user, Model model, HttpSession session) {
    	
    	User foundUser = userRepository.findByUsername(user.getUsername()).get();
    	Integer id = foundUser.getId();
    	
		if(userService.editProfile(username, email, phone, address, model)) {
			System.out.println("Edit thanh cong");
			foundUser.setEmail(email);
			foundUser.setAddress(address);
			foundUser.setPhone(phone);
			userService.save(foundUser);
			
			Optional<User> users = userService.findById(id);
	        if(users.isPresent()) {
	        	model.addAttribute("users", users.get());
	        }
	        model.addAttribute("helloUser", session.getAttribute("USERNAME"));
			model.addAttribute("idUser", userService.findByUsername(session.getAttribute("USERNAME").toString()).get());
			
			return "userView";
		}
		else {
			System.out.println("edit that bai");
		}
		Optional<User> users = userService.findById(id);
        if(users.isPresent()) {
        	model.addAttribute("users", users.get());
        }
        
        model.addAttribute("helloUser", session.getAttribute("USERNAME"));
		model.addAttribute("idUser", userService.findByUsername(session.getAttribute("USERNAME").toString()).get());
		return "userView";
	}
    
    @PostMapping("/editUserCustomer")
    public String editUserCustomer(@RequestParam(required=false, name = "username") String username, 
			@RequestParam(required=false, name = "email") String email, 
			@RequestParam(required=false, name = "address") String address,
			@RequestParam(required=false, name = "phone") String phone,
			@Valid @ModelAttribute("user") User user, Model model, HttpSession session) {
    	
    	User foundUser = userRepository.findByUsername(user.getUsername()).get();
    	Integer id = foundUser.getId();
    	
		if(userService.editProfile(username, email, phone, address, model)) {
			System.out.println("Edit thanh cong");
			foundUser.setEmail(email);
			foundUser.setAddress(address);
			foundUser.setPhone(phone);
			userService.save(foundUser);
			
			Optional<User> users = userService.findById(id);
	        if(users.isPresent()) {
	        	model.addAttribute("users", users.get());
	        }
	        model.addAttribute("helloUser", session.getAttribute("USERNAME"));
			model.addAttribute("idUser", userService.findByUsername(session.getAttribute("USERNAME").toString()).get());
			return "Profile";
		}
		else {
			System.out.println("edit that bai");
		}
		Optional<User> users = userService.findById(id);
        if(users.isPresent()) {
        	model.addAttribute("users", users.get());
        }
        
        model.addAttribute("helloUser", session.getAttribute("USERNAME"));
		model.addAttribute("idUser", userService.findByUsername(session.getAttribute("USERNAME").toString()).get());
		return "Profile";
	}
    
  //---------------------------------------------------------------Change Password----------------------------------------------------------------------
    @PostMapping("/checkChangePassword")
	public String checkChangePassword(@RequestParam(required=false, name = "username") String username, 
			@RequestParam(required=false, name = "OldPassword") String OldPassword, 
			@RequestParam(required=false, name = "ConfirmNewPassword") String confirmNewPassword,
			@RequestParam(required=false, name = "NewPassword") String NewPassword,
			@Valid @ModelAttribute("user") User user,
			@RequestParam(required=false, name = "email") String email, Model model, HttpSession session) {
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
		
		String NewEncryptedpassword = null;  
		try   
        {  
            /* MessageDigest instance for MD5. */  
            MessageDigest m = MessageDigest.getInstance("MD5");  
              
            /* Add plain-text password bytes to digest using MD5 update() method. */  
            m.update(NewPassword.getBytes());  
              
            /* Convert the hash value into bytes */   
            byte[] bytes = m.digest();  
              
            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */  
            StringBuilder s = new StringBuilder();  
            for(int i=0; i< bytes.length ;i++)  
            {  
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
            }  
              
            /* Complete hashed password in hexadecimal format */  
            NewEncryptedpassword = s.toString();  
        }   
        catch (NoSuchAlgorithmException e)   
        {  
            e.printStackTrace();  
        }  
		if(userService.changePass(username, OldPassword, NewPassword, confirmNewPassword, email, model)) {
			System.out.println("Register thanh cong");
			User foundUser = userRepository.findByUsername(user.getUsername()).get();
			foundUser.setPassword(NewEncryptedpassword);
			foundUser.setConfirmpassword(NewEncryptedpassword);
			userService.save(foundUser);
			
			model.addAttribute("helloUser", session.getAttribute("USERNAME"));
			model.addAttribute("idUser", userService.findByUsername(session.getAttribute("USERNAME").toString()).get());
			
			return "change-password";
		}
		else {
			System.out.println("change pass that bai");
		}
		return "change-password";
	}
    
    @GetMapping("changepassword")
	public String changepassword(Model model, HttpSession session) {
		User u = new User();
		model.addAttribute("USER", u);

		model.addAttribute("helloUser", session.getAttribute("USERNAME"));
		model.addAttribute("idUser", userService.findByUsername(session.getAttribute("USERNAME").toString()).get());
		return "change-password";
	}
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap modelMap, HttpSession session) {
		    modelMap.addAttribute("AllProducts", productRepository.findAll());
		    modelMap.addAttribute("AllCategoryID", categoryRepository.findAll());
		    
		    return "/indexNoUser"; 
    }
}

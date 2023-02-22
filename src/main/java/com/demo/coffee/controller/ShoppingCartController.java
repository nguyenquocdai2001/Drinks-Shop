package com.demo.coffee.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.coffee.entity.CartItem;
import com.demo.coffee.entity.Product;
import com.demo.coffee.entity.Receipt;
import com.demo.coffee.entity.ReceiptItem;
import com.demo.coffee.entity.User;
import com.demo.coffee.repository.ProductRepository;
import com.demo.coffee.repository.ReceiptItemRepository;
import com.demo.coffee.repository.ReceiptRepository;
import com.demo.coffee.repository.UserRepository;
import com.demo.coffee.service.ShoppingCartService;
import com.demo.coffee.service.UserService;
import com.mysql.cj.Session;

@Controller
@RequestMapping("shopping-cart")
public class ShoppingCartController {
	@Autowired
    ProductRepository productRepository;
	
	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired //Inject "userRepository" - Dependency Injection
    private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
    ReceiptRepository receiptRepository;
	
	@Autowired
    ReceiptItemRepository receiptItemRepository;

//-----------------------------------------------------------View cart---------------------------------------------------------
	@GetMapping("views")
	public String viewCarts(Model model, HttpSession session) {
		model.addAttribute("CART_ITEMS", shoppingCartService.getAllItems());
		model.addAttribute("TOTAL", shoppingCartService.getAmount());
		
		model.addAttribute("helloUser", session.getAttribute("USERNAME"));
		model.addAttribute("idUser", userService.findByUsername(session.getAttribute("USERNAME").toString()).get());
		return "cart-items";
	}
//-----------------------------------------------------------Add cart---------------------------------------------------------	
	@GetMapping("add/{id}")
	public String addCart(@PathVariable("id") Integer id) {
		Product product = productRepository.findById(id).get();
		if(product != null) {
			CartItem item = new CartItem();
			item.setProductID(product.getProductID());
			item.setName(product.getProductName());
			item.setPrice(product.getPrice());
			item.setQty(1);
			shoppingCartService.add(item);
		}
		return "redirect:/products/getAllProducts";
	}
//-----------------------------------------------------------Clear cart---------------------------------------------------------
	@GetMapping("clear")
	public String clearCart() {
		shoppingCartService.clear();
		return "redirect:/shopping-cart/views";
	}
	
//-----------------------------------------------------------Remove cart item---------------------------------------------------------
		@GetMapping("delete/{id}")
		public String removeCart(@PathVariable("id") Integer id) {
			shoppingCartService.remove(id);
			return "redirect:/shopping-cart/views";
		}
//-----------------------------------------------------------Update quantity item---------------------------------------------------------
		@PostMapping("update")
		public String update(@RequestParam("id") Integer id, @RequestParam("qty") Integer qty) {
			shoppingCartService.update(id, qty);
			return "redirect:/shopping-cart/views";
		}
//-----------------------------------------------------------Checkout views----------------------------------------------------------------------		
		@GetMapping("checkout")
		public String checkOut(HttpSession session, Model model) {
		User foundUser = userRepository.findByUsername((session.getAttribute("USERNAME")).toString()).get();
		
		model.addAttribute("helloUser", session.getAttribute("USERNAME"));
		model.addAttribute("idUser", userService.findByUsername(session.getAttribute("USERNAME").toString()).get());
		
		model.addAttribute("users", foundUser);
		return "checkout";
	}
//-----------------------------------------------------------Checkout confirm----------------------------------------------------------------------
		@PostMapping("confirmcheckout")
		public String confirmcheckout(@RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("email") String email
				, @RequestParam("address") String address, @RequestParam("username") String username) {
			Receipt receipt = new Receipt();
			receipt.setReceiptName(name);
			receipt.setReceiptMail(email);
			receipt.setReceiptPhone(phone);
			receipt.setReceiptAddress(address);
			receipt.setReceiptUsername(username);
			receipt.setReceiptTotal(shoppingCartService.getAmount());
			System.out.println(receipt.getReceiptName());
			receiptRepository.save(receipt);
			
			for (CartItem item:shoppingCartService.getAllItems()) {
				ReceiptItem receiptItem = new ReceiptItem();
				receiptItem.setProduct(productRepository.findById(item.getProductID()).get());    
				receiptItem.setReceipt(receipt);
				receiptItem.setReceiptItemPrice(item.getPrice());
				receiptItem.setReceiptItemQuantity(item.getQty());
				receiptItemRepository.save(receiptItem);
			}
			return "redirect:/products/getAllProducts";
		}
//----------------------------------------------------------------------Receipt view (history customer view)---------------------------------------------------------------------
		@RequestMapping(value = "/getAllReceipt", method = RequestMethod.GET)
	    public String getAllReceipt(ModelMap modelMap, HttpSession session) {
	    		modelMap.addAttribute("userhistory", session.getAttribute("USERNAME"));
			    modelMap.addAttribute("AllReceipt", receiptRepository.findAll());
			    
			    modelMap.addAttribute("helloUser", session.getAttribute("USERNAME"));
			    modelMap.addAttribute("idUser", userService.findByUsername(session.getAttribute("USERNAME").toString()).get());
			    return "/history"; 
	    }
//---------------------------------------------------View all Receipt--------------------------------------------------------------------------		
		@RequestMapping(value = "/getAllReceiptAdmin", method = RequestMethod.GET)
	    public String getAllReceiptAdmin(ModelMap modelMap, HttpSession session) {
			if((session.getAttribute("USERNAME").equals("nguyenquocdai"))) {
	    		modelMap.addAttribute("userhistory", session.getAttribute("USERNAME"));
			    modelMap.addAttribute("AllReceipt", receiptRepository.findAll());
			    modelMap.addAttribute("helloUser", session.getAttribute("USERNAME"));
			    modelMap.addAttribute("idUser", userService.findByUsername(session.getAttribute("USERNAME").toString()).get());
			    return "/historyAll"; 
			}
			return "/login";
	    }
//---------------------------------------------------Admin view history of each user--------------------------------------------------------------------------		
		@RequestMapping(value = "/getReceiptAdmin/{id}", method = RequestMethod.GET)
	    public String getReceiptAdmin(ModelMap modelMap, HttpSession session, @PathVariable Integer id) {
			if((session.getAttribute("USERNAME").equals("nguyenquocdai"))) {
	    		modelMap.addAttribute("userhistory", session.getAttribute("USERNAME"));
			    modelMap.addAttribute("AllReceipt", receiptRepository.findAll());
			    
			    modelMap.addAttribute("helloUser", session.getAttribute("USERNAME"));
			    modelMap.addAttribute("idUser", userService.findByUsername(session.getAttribute("USERNAME").toString()).get());
			    
			    Optional<User> users = userService.findById(id);
		        if(users.isPresent()) {
		        	modelMap.addAttribute("users", users.get().getUsername());
		        }
			    return "/historyAdmin"; 
			}
			return "/login";
	    }
//---------------------------------------------------Detail Receipt--------------------------------------------------------------------------		
		@RequestMapping(value = "/getItemReceiptAdmin/{id}/{username}", method = RequestMethod.GET)
	    public String getItemReceiptAdmin(ModelMap modelMap, HttpSession session, @PathVariable Integer id, @PathVariable String username) {

	    		modelMap.addAttribute("userhistory", session.getAttribute("USERNAME"));
	    		modelMap.addAttribute("AllReceipt", receiptRepository.findAll());
			    modelMap.addAttribute("AllItemReceipt", receiptItemRepository.findAll());
			    
			    modelMap.addAttribute("helloUser", session.getAttribute("USERNAME"));
			    modelMap.addAttribute("idUser", userService.findByUsername(session.getAttribute("USERNAME").toString()).get());
			    
			    System.out.println(id);

		        modelMap.addAttribute("users", username);
		        modelMap.addAttribute("idReceipt", id);

			    return "/ItemReceipt"; 
	    }
		
		@RequestMapping(value = "/getItemReceiptCustomer/{id}/{username}", method = RequestMethod.GET)
	    public String getItemReceiptCustomer(ModelMap modelMap, HttpSession session, @PathVariable Integer id, @PathVariable String username) {

	    		modelMap.addAttribute("userhistory", session.getAttribute("USERNAME"));
	    		modelMap.addAttribute("AllReceipt", receiptRepository.findAll());
			    modelMap.addAttribute("AllItemReceipt", receiptItemRepository.findAll());
			    
			    modelMap.addAttribute("helloUser", session.getAttribute("USERNAME"));
			    modelMap.addAttribute("idUser", userService.findByUsername(session.getAttribute("USERNAME").toString()).get());
			    
			    System.out.println(id);

		        modelMap.addAttribute("users", username);
		        modelMap.addAttribute("idReceipt", id);

			    return "/ItemReceiptCustomer"; 
	    }
}

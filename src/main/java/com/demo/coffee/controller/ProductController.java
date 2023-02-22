package com.demo.coffee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.demo.coffee.entity.Category;
import com.demo.coffee.entity.Product;
import com.demo.coffee.repository.CategoryRepository;
import com.demo.coffee.repository.ProductRepository;
import com.demo.coffee.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.io.IOException;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping(path = "products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    
    
    @Autowired //Inject "categoryRepository" - Dependency Injection
    private CategoryRepository categoryRepository;
    
    @Autowired
	private UserService userService;

    //http:localhost:8083/products/getProductsByCategoryID/
    @RequestMapping(value = "/getProductsByCategoryID/{categoryID}", method = RequestMethod.GET)
    public String getProductsByCategoryID(ModelMap modelMap, @PathVariable Integer categoryID, HttpSession session) {
        Iterable<Product> products = productRepository.findByCategoryID(categoryID);
        modelMap.addAttribute("products", products);
        
        modelMap.addAttribute("helloUser", session.getAttribute("USERNAME"));
        modelMap.addAttribute("idUser", userService.findByUsername(session.getAttribute("USERNAME").toString()).get());
        return "productList"; //"productList.jsp"
    }
    @RequestMapping(value = "/changeProduct/{productID}", method = RequestMethod.GET)
    public String changeProduct(ModelMap modelMap,  @PathVariable Integer productID, HttpSession session) {
        modelMap.addAttribute("categories", categoryRepository.findAll());
        modelMap.addAttribute("product", productRepository.findById(productID).get());
        
        modelMap.addAttribute("helloUser", session.getAttribute("USERNAME"));
        modelMap.addAttribute("idUser", userService.findByUsername(session.getAttribute("USERNAME").toString()).get());
        return "updateProduct";//updateProduct.jsp
    }
    @RequestMapping(value = "/insertProduct", method = RequestMethod.GET)
    public String insertProduct(ModelMap modelMap, HttpSession session) {
        modelMap.addAttribute("product", new Product());
        modelMap.addAttribute("categories", categoryRepository.findAll());
        
        modelMap.addAttribute("helloUser", session.getAttribute("USERNAME"));
        modelMap.addAttribute("idUser", userService.findByUsername(session.getAttribute("USERNAME").toString()).get());
        return "insertProduct";//insertProduct.jsp
    }

    @RequestMapping(value = "/insertProduct", method = RequestMethod.POST)
    //method overloading
    public String insertProduct(ModelMap modelMap,
                                @Valid @ModelAttribute("product") Product product, BindingResult bindingResult, 
                                @RequestParam("image") MultipartFile multipartFile
                                ) {
        if(bindingResult.hasErrors()) {
            return "insertProduct";
        }
        try {
        	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        	fileName = product.getProductName() + ".jpeg";
            product.setPhoto(fileName);             
            Product savedPhoto = productRepository.save(product);
            String uploadDir = "product-photos/";           
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            
            productRepository.save(product);
            return "redirect:/categories";
        }catch (Exception e) {
            modelMap.addAttribute("error", e.toString());
            return "insertProduct";
        }
    }
    /*public RedirectView insertProduct(Product product,
            @RequestParam("image") MultipartFile multipartFile) throws IOException {
         
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        product.setPhoto(fileName);
         
        Product savedPhoto = productRepository.save(product);
 
        String uploadDir = "product-photos/" + savedPhoto.getProductID();
 
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
         
        return new RedirectView("/products/getProductsByCategoryID/"+product.getCategoryID(), true);
    }*/

    @RequestMapping(value = "/deleteProduct/{productID}", method = RequestMethod.POST)
    public String deleteProduct(ModelMap modelMap, @PathVariable Integer productID) {
        productRepository.deleteById(productID);
        return "redirect:/categories";
    }
    ///products/updateProduct/${product.getProductID()}
    @RequestMapping(value = "/updateProduct/{productID}", method = RequestMethod.POST)
    public String updateProduct(ModelMap modelMap,
                                @Valid @ModelAttribute("product") Product product,
                                BindingResult bindingResult,
                                @RequestParam("image") MultipartFile multipartFile,
                                @PathVariable Integer productID
    ) {
        Iterable<Category> categories = categoryRepository.findAll();
        if(bindingResult.hasErrors()) {
            modelMap.addAttribute("categories", categories);
            return "updateProduct";//updateProduct.jsp
        }
        try {
            if(productRepository.findById(productID).isPresent()) {
                Product foundProduct = productRepository
                        .findById(product.getProductID()).get();
                if(!product.getProductName().trim().isEmpty()) {
                    foundProduct.setProductName(product.getProductName() );
                }
                if(!product.getCategoryID().toString().isEmpty()) {
                    foundProduct.setCategoryID(product.getCategoryID());
                }
                //is empty => "" OR NULL
                if(!product.getDescription().trim().isEmpty()) {
                    foundProduct.setDescription(product.getDescription());
                }
                if(product.getPrice() > 0) {
                    foundProduct.setPrice(product.getPrice());
                }
                if(product.getCost() > 0) {
                    foundProduct.setCost(product.getCost());
                }
                if(!StringUtils.cleanPath(multipartFile.getOriginalFilename()).trim().isEmpty()) {
                	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
                    foundProduct.setPhoto(fileName);             
                    Product savedPhoto = productRepository.save(foundProduct);
                    String uploadDir = "product-photos/" + savedPhoto.getProductID();            
                    FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
                	
                    //foundProduct.setPhoto(product.getPhoto());
                }
                productRepository.save(foundProduct);
            }
        }catch (Exception e) {
            return "updateProduct";//updateProduct.jsp
        }
        return "redirect:/products/getProductsByCategoryID/"+product.getCategoryID();
    }
    /*@RequestMapping(value = "/insertProduct", method = RequestMethod.POST)
    public RedirectView insertProduct(Product product,
            @RequestParam("image") MultipartFile multipartFile) throws IOException {
         
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        product.setPhoto(fileName);
         
        Product savedPhoto = productRepository.save(product);
 
        String uploadDir = "product-photos/" + savedPhoto.getProductID();
 
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
         
        return new RedirectView("/products/getProductsByCategoryID/"+product.getCategoryID(), true);
    }*/
    @RequestMapping(value = "/getAllProducts", method = RequestMethod.GET)
    public String getAllProducts(ModelMap modelMap, HttpSession session) {
    	if(!(session.getAttribute("USERNAME")).equals("nguyenquocdai")) {
		    modelMap.addAttribute("AllProducts", productRepository.findAll());
		    modelMap.addAttribute("AllCategoryID", categoryRepository.findAll());
		    
		    modelMap.addAttribute("helloUser", session.getAttribute("USERNAME"));
	        modelMap.addAttribute("idUser", userService.findByUsername(session.getAttribute("USERNAME").toString()).get());
		    return "/index"; //"productList.jsp"
    	}
    	return "login";
    }

}

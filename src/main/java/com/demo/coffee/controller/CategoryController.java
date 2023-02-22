package com.demo.coffee.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.coffee.entity.Category;
import com.demo.coffee.entity.Product;
import com.demo.coffee.repository.CategoryRepository;
import com.demo.coffee.service.UserService;

@Controller
@RequestMapping(path = "categories")
//http:localhost:8083/categories
public class CategoryController {
    @Autowired 
    private CategoryRepository categoryRepository;
    
    @Autowired
	private UserService userService;
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getAllCategories(ModelMap modelMap, HttpSession session) {
        //data sent to jsp => ModelMap
        //send data from Controller to View
        Iterable<Category> categories = categoryRepository.findAll();
        modelMap.addAttribute("categories", categories);
        System.out.println("success");
        modelMap.addAttribute("helloUser", session.getAttribute("USERNAME"));
        modelMap.addAttribute("idUser", userService.findByUsername(session.getAttribute("USERNAME").toString()).get());
        return "category";
    }
    @RequestMapping(value = "/changeCategory/{categoryID}", method = RequestMethod.GET)
    public String changeCategory(ModelMap modelMap,  @PathVariable Integer categoryID, HttpSession session) {
        //modelMap.addAttribute("categories", categoryRepository.findAll());
        modelMap.addAttribute("category", categoryRepository.findById(categoryID).get());
        
        modelMap.addAttribute("helloUser", session.getAttribute("USERNAME"));
        modelMap.addAttribute("idUser", userService.findByUsername(session.getAttribute("USERNAME").toString()).get());
        return "updateCategory";//updateCategory.jsp
    }
    
    //Category CRUD starts here
    @RequestMapping(value = "/insertCategory", method = RequestMethod.GET)
    public String insertCategory(ModelMap modelMap, HttpSession session) {
        modelMap.addAttribute("category", new Category());

        modelMap.addAttribute("helloUser", session.getAttribute("USERNAME"));
        modelMap.addAttribute("idUser", userService.findByUsername(session.getAttribute("USERNAME").toString()).get());
        return "insertCategory";//insertProduct.jsp
    }

    @RequestMapping(value = "/insertCategory", method = RequestMethod.POST)
    //method overloading
    public String insertCategory(ModelMap modelMap,
                                @Valid @ModelAttribute("category") Category category,
                                BindingResult bindingResult //validation
                                ) {
        if(bindingResult.hasErrors()) {
            return "insertCategory";
        }
        try {
            categoryRepository.save(category);
            return "redirect:/categories";
        }catch (Exception e) {
            modelMap.addAttribute("error", e.toString());
            return "insertCategory";
        }
    }

    @RequestMapping(value = "/deleteCategory/{categoryID}", method = RequestMethod.POST)
    public String deleteCategory(ModelMap modelMap, @PathVariable Integer categoryID) {
        categoryRepository.deleteById(categoryID);
        return "redirect:/categories";
    }
    ///products/updateProduct/${product.getProductID()}
    @RequestMapping(value = "/updateCategory/{categoryID}", method = RequestMethod.POST)
    public String updateProduct(ModelMap modelMap,
                                @Valid @ModelAttribute("category") Category category,
                                BindingResult bindingResult,
                                @PathVariable Integer categoryID
    ) {
        Iterable<Category> categories = categoryRepository.findAll();
        if(bindingResult.hasErrors()) {
            modelMap.addAttribute("categories", categories);
            return "updateCategory";//updateCategory.jsp
        }
        try {
            if(categoryRepository.findById(categoryID).isPresent()) {
                Category foundCategory = categoryRepository
                        .findById(category.getCategoryID()).get();
                if(!category.getCategoryName().trim().isEmpty()) {
                    foundCategory.setCategoryName(category.getCategoryName() );
                }
                /*if(!category.getCategoryID().isEmpty()) {
                    foundCategory.setCategoryID(product.getCategoryID());
                }*/
                //is empty => "" OR NULL
                if(!category.getDescription().trim().isEmpty()) {
                    foundCategory.setDescription(category.getDescription());
                }
                categoryRepository.save(foundCategory);
            }
        }catch (Exception e) {
            return "updateCategory";//updateProduct.jsp
        }
        return "redirect:/categories/";
    }
    
}

package com.example.testing.Controller;

import com.example.testing.Service.CategoryService;
import com.example.testing.Service.ProductService;
import com.example.testing.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class ClientController {

    private ProductService productService;
    private CategoryService categoryService;

    public ClientController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/av/test")
    public String listProduct(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("departs", categoryService.getAllCategory());
        return "test";
    }
    @GetMapping("/av/test/ap")
    public String listProducts(Model model) {
        model.addAttribute("product", productService.getAllProducts());
        model.addAttribute("departs", categoryService.getAllCategory());
        return "redirect:/av/test";
    }

    @GetMapping("/detail/{id}")
    public String showIndex(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "Details";
    }



}

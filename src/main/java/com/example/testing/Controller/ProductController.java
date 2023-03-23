package com.example.testing.Controller;


import com.example.testing.Controller.files.ProductCsvExporter;
import com.example.testing.Controller.files.ProductPdfExporter;
import com.example.testing.Repository.ProductRepository;
import com.example.testing.Service.CategoryService;
import com.example.testing.Service.ProductService;
import com.example.testing.Util.FileUploadUtil;
import com.example.testing.model.Product;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;


@RequestMapping("/admin")
@Controller
public class ProductController {


    private CategoryService categoryService;

    private ProductService productService;

//    public ProductController(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }

    public ProductController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @RequestMapping({"/dashboard", "/"})
    public String dashboardPage() {
        return ("admin/dashboard");
    }

    @GetMapping("/products/export/csv")
    public void exportToCsv(HttpServletResponse response) throws IOException {
        List<Product> listProduct = productService.getAllProducts();
        ProductCsvExporter exporter = new ProductCsvExporter();
        exporter.export(listProduct, response);
    }

    @GetMapping("/products/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        List<Product> listProduct = productService.getAllProducts();

        ProductCsvExporter exporter = new ProductCsvExporter();

        exporter.export(listProduct, response);
    }

    @GetMapping("/products/export/pdf")
    public void exportToPdf(HttpServletResponse response) throws IOException {
        List<Product> listProduct = productService.getAllProducts();
        ProductPdfExporter exporter = new ProductPdfExporter();
        exporter.export(listProduct, response);
    }






//    @RequestMapping({"/user/list", "/view"})
//    public String listUser() {
//        return "view";
//    }


    @GetMapping("/av")
    public String listProduct(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("departs", categoryService.getAllCategory());
        return "product";
    }

//    @GetMapping("/ap")
//    public String phoneCat(Model model) {
//
////
//        model.addAttribute("products", productService.getAllProducts());
//        model.addAttribute("departs", categoryService.getAllCategory());
//        return ("Details");
//    }

//    @GetMapping("/av/test")
//    public String listProduc(Model model) {
//        model.addAttribute("products", productService.getAllProducts());
//        model.addAttribute("departs", categoryService.getAllCategory());
//        return "test";
//    }
//    @GetMapping("/av/test/ap")
//    public String listProducts(Model model) {
//        model.addAttribute("product", productService.getAllProducts());
//        model.addAttribute("departs", categoryService.getAllCategory());
//        return "redirect:/av/test";
//    }
//
//    @GetMapping("/detail/{id}")
//    public String showIndex(@PathVariable("id") Long id, Model model) {
//        Product product = productService.getProductById(id);
//        model.addAttribute("product", product);
//        return "Details";
//    }
//




//    @GetMapping("/detail/edit/{id}")
//    public String editStudentForm(@PathVariable Long id, Model model) {
//        model.addAttribute("student", studentService.getStudentById(id));
//        return "edit-student";
//    }


    @PostMapping("/product/addNew")
    public String saveProduct(@ModelAttribute("product") Product product, @RequestParam("image") MultipartFile multipartFile, RedirectAttributes sms) throws IOException, IOException {

        if(!multipartFile.isEmpty()){
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        product.setPicture(fileName);

        Product saveproduct = productService.saveProduct(product);

        String uploadDir = "product-photos/" + product.getId();


        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
    }
    else

    {
        if (product.getPicture().isEmpty()) {
            product.setPicture(null);
            productService.saveProduct(product);
        }
    }
       productService.saveProduct(product);

         return"redirect:/admin/av";


}




    @GetMapping("/students/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model ) {
        model.addAttribute("product", productService.getProductById(id));
        return "redirect:/av";
    }

    @PostMapping("/products/{id}")
    public String updateProduct(@PathVariable Long id,@RequestParam("image") MultipartFile multipartFile,
                                @ModelAttribute("product") Product product,
                                Model model,RedirectAttributes sms)  throws IOException, IOException {

        // get student from database by id
        Product existProduct=productService.getProductById(id);

        existProduct.setId(id);
        existProduct.setProductName(product.getProductName());
        existProduct.setCost(product.getCost());

        existProduct.setBrand(product.getBrand());
        existProduct.setCategory(product.getCategory());



        if(!multipartFile.isEmpty()){
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            product.setPicture(fileName);

            Product saveproduct = productService.saveProduct(product);

            String uploadDir = "product-photos/" + product.getId();


            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        }
        else

        {
            if (product.getPicture().isEmpty()) {
                product.setPicture(null);
                productService.updateProduct(existProduct);
            }
        }
        productService.updateProduct(existProduct);





        // save updated student object


        sms.addFlashAttribute("message_update","Product successfully updated");
        return "redirect:/admin/av";
    }





    @GetMapping("/products/del/{id}")
    public String deleteProduct(@PathVariable Long id,RedirectAttributes sms) {
        productService.deleteProductById(id);

        sms.addFlashAttribute("message_delete","Product successfully deleted");
        return "redirect:/admin/av";
    }

}

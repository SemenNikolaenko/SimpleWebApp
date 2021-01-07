package sem.nik.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sem.nik.demo.entiities.Product;
import sem.nik.demo.entiities.User;
import sem.nik.demo.services.ProductService;
import sem.nik.demo.services.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private UserService userService;
    private ProductService productService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showProductsList(Model model, Principal principal) {
        List<Product> products = productService.getAllProducts();
        products = productService.sortListOnId(products);
        model.addAttribute("products", products);

        if (principal != null) {
            User user = userService.findOneByUsername(principal.getName());
            model.addAttribute("name", user.getName());
        } else model.addAttribute("name", "нет данных о пользователе");
        return "products";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute(value = "product") Product product) {
        productService.add(product);
        return "redirect:/products";
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        Product product = new Product();
        model.addAttribute(product);
        return "add-product";
    }

    @GetMapping("/filter")
    public String filterProduct(Model model, @RequestParam(value = "str") String str) {
        if (str.length() == 0) return "redirect:/products";
        Product product = productService.getProductByTitle(str);
        List<Product> list = new ArrayList<>();
        list.add(product);

        model.addAttribute("products", list);
        return "products";
    }

    @GetMapping("/show/{id}")
    public String showOneProduct(@PathVariable(value = "id") Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product-page";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable(value = "id") Long id) {
        productService.deleteProductById(id);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable(value = "id") Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "edit";
    }

    @PostMapping("/edit")
    public String editProduct(@ModelAttribute(value = "product") Product product) {
        productService.replaceById(product.getId(), product.getTitle(), product.getPrice());
        return "redirect:/products";
    }

}

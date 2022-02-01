package ru.geekbrains;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final AppService appService;

    @RequestMapping(path = "/add_product", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "add-product";
    }

    @RequestMapping(path = "/add_product", method = RequestMethod.POST)
    public String fillForm(Product product) {
       appService.saveProduct(product);
       return "redirect:/products";
    }

    @RequestMapping
    public String getAllProducts(Model model) {
        model.addAttribute("products", appService.getAllProducts());
        return "product-list";
    }


}

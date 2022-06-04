package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.payload.ProductDTO;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public Result addProduct(@RequestBody ProductDTO productDTO) {
        return productService.addProduct(productDTO);
    }

    @GetMapping("/get")
    public Result allProductList() {
        return productService.getAllProducts();
    }

    @GetMapping("/get/{id}")
    public Result getProduct(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    @GetMapping("/get/category/{id}")
    public Result getAllProductsByCategory(@PathVariable("/id") Integer categoryId) {
        return productService.getAllProductsByCategory(categoryId);
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteProduct(@PathVariable Integer id) {
        return productService.deleteProduct(id);
    }


    @PutMapping("/edit/{id}")
    public Result editProduct(@PathVariable Integer id, @RequestBody ProductDTO productDTO) {
        return productService.editProduct(id, productDTO);
    }


}

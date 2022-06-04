package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.payload.CategoryDTO;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/add")
    public Result addCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.addCategory(categoryDTO);
    }

    @GetMapping("/get/list")
    public Result getAllCategoryList() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/get/list/{id}")
    public Result getCategoryList(@PathVariable Integer id) {
        return categoryService.getCategoriesByParentCategory(id);
    }

    @GetMapping("/get/{id}")
    public Result getCategory(@PathVariable Integer id) {
        return categoryService.getCategory(id);
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteCategoryById(@PathVariable Integer id) {
        return categoryService.deleteCategoryById(id);
    }

    @PutMapping("/edit/{id}")
    public Result editCategory(@PathVariable Integer id, @RequestBody CategoryDTO categoryDTO) {
        return categoryService.editCategory(id,categoryDTO);
    }

}

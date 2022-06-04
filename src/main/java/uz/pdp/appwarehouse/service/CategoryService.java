package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Category;
import uz.pdp.appwarehouse.payload.CategoryDTO;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;


    public Result addCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());

        if (categoryDTO.getParentId() != null) {
            Optional<Category> optionalCategory = categoryRepository.findById(categoryDTO.getParentId());
            if (!optionalCategory.isPresent())
                return new Result("This category is not found!", false);

            category.setParentCategory(optionalCategory.get());
        }

        categoryRepository.save(category);
        return new Result("Category is added successfully.", true);
    }

    public Result getAllCategories() {
        List<Category> all = categoryRepository.findAll();
        return new Result("All categories ...", true, all);
    }

    public Result getCategoriesByParentCategory(Integer id) {
        List<Category> categories;
        List<Category> byParentCategoryId = categoryRepository.findByParentCategoryId(id);
        return new Result("Categories by parentCategory ...", true, byParentCategoryId);
    }

    public Result getCategory(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if (!optionalCategory.isPresent())
            return new Result("Category is not found!", false);
        Category category = optionalCategory.get();
        return new Result("Parent category ...", true, category);
    }

    public void deleteCategory(Integer id) {
        boolean exists = categoryRepository.existsByParentCategoryId(id);

        if (!exists) {
            if (categoryRepository.existsById(id)) {
                categoryRepository.deleteById(id);
            }
        } else if (exists) {
            List<Category> allCategoriesById = categoryRepository.findByParentCategoryId(id);
            for (Category category : allCategoriesById) {
                Integer currentId = category.getId();
                deleteCategory(currentId);
            }
            categoryRepository.deleteById(id);
        }
    }

    public Result deleteCategoryById(Integer id) {
        deleteCategory(id);
        return new Result("categories are deleted ...", true);
    }

    public Result editCategory(Integer id, CategoryDTO categoryDTO) {
        Optional<Category> categoryRepositoryById = categoryRepository.findById(id);

        if (!categoryRepositoryById.isPresent())
            return new Result("Category not found ...", false);

        Category category = categoryRepositoryById.get();
        category.setName(categoryDTO.getName());
        Optional<Category> optionalCategory = categoryRepository.findById(categoryDTO.getParentId());
        if (!optionalCategory.isPresent())
            return new Result("Category not found ...", false);
        Category categoryParent = optionalCategory.get();
        category.setParentCategory(categoryParent);
        Category savedCategory = categoryRepository.save(category);
       // boolean editCategory = categoryRepository.editNameById(id, categoryDTO.getName());
        return new Result("Category is edited ...", true, savedCategory);
    }

}

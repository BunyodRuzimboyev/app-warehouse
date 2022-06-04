package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appwarehouse.entity.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findOneByParentCategoryId(Integer parentCategory_id);
    List<Category> findByParentCategoryId(Integer parentCategory_id);
    boolean deleteAllByParentCategoryId(Integer parentCategory_id);

    boolean existsByParentCategoryId(Integer parentCategory_id);

    List<Category> findAllById(Integer id);


}

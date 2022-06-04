package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.appwarehouse.entity.*;
import uz.pdp.appwarehouse.payload.ProductDTO;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmentContentRepository attachmentContentRepository;
    @Autowired
    MeasurementRepository measurementRepository;

    public Result addProduct(@RequestBody ProductDTO productDTO) {
        Product product = new Product();
        boolean exists = productRepository.existsByNameAndCategoryId(productDTO.getName(), productDTO.getCategoryId());
        if (exists)
            return new Result("Product is already exists!", false);

        product.setName(productDTO.getName());
        //  CATEGORY NI TEKSHIRISH UCHUN
        Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new Result("This category is not found!", false);
        }

        //PHOTO TEKSHIRISH
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDTO.getPhotoId());
        if (!optionalAttachment.isPresent()) {
            return new Result("This photo is not found!", false);
        }
        //MEASUREMENT TEKSHIRISH
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDTO.getMeasurementId());
        if (!optionalMeasurement.isPresent()) {
            return new Result("This measurement is not found!", false);
        }
        //PRODUCT GA SET QILAMIZ
        product.setCategory(optionalCategory.get());
        product.setPhoto(optionalAttachment.get());
        product.setMeasurement(optionalMeasurement.get());
        product.setCode("1"); // QAYTADAN KOD YOZISH KERAK
        productRepository.save(product);
        return new Result("Product is saved.", true);
    }

    public Result getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        return new Result("All products ...", true, allProducts);
    }

    public Result getProductById(Integer id) {
        Optional<Product> productRepositoryById = productRepository.findById(id);
        if (!productRepositoryById.isPresent())
            return new Result("Product is not found ...", false);
        Product product = productRepositoryById.get();
        return new Result("Product ...", true, product);
    }

    public Result getAllProductsByCategory(Integer categoryId) {
        List<Product> productByCategoryId = productRepository.getProductByCategoryId(categoryId);
        return new Result("All products by category ...", true, productByCategoryId);
    }


    public Result editProduct(Integer id, ProductDTO productDTO) {

        Optional<Product> productRepositoryById = productRepository.findById(id);
        if (!productRepositoryById.isPresent())
            return new Result("Product not found ...", false);

        Product product = productRepositoryById.get();

        boolean exists = productRepository.existsByNameAndCategoryId(productDTO.getName(), productDTO.getCategoryId());
        if (exists)
            return new Result("Product is already exists!", false);

        product.setName(productDTO.getName());
        //  CATEGORY NI TEKSHIRISH UCHUN
        Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new Result("This category is not found!", false);
        }

        //PHOTO TEKSHIRISH
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDTO.getPhotoId());
        if (!optionalAttachment.isPresent()) {
            return new Result("This photo is not found!", false);
        }
        //MEASUREMENT TEKSHIRISH
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDTO.getMeasurementId());
        if (!optionalMeasurement.isPresent()) {
            return new Result("This measurement is not found!", false);
        }
        //PRODUCT GA SET QILAMIZ
        product.setCategory(optionalCategory.get());
        product.setPhoto(optionalAttachment.get());
        product.setMeasurement(optionalMeasurement.get());
        product.setCode("1"); // QAYTADAN KOD YOZISH KERAK
        productRepository.save(product);
        return new Result("Product is saved.", true);
    }

    public Result deleteProduct(Integer id) {

        Optional<Product> productRepositoryById = productRepository.findById(id);
        if (!productRepositoryById.isPresent())
            return new Result("Product not found ...", false);
        Product product = productRepositoryById.get();
        Attachment photo = product.getPhoto();
        Optional<AttachmentContent> attachmentContentRepositoryById = attachmentContentRepository.findById(photo.getId());
        if (attachmentContentRepositoryById.isPresent()) {
            attachmentContentRepository.deleteById(photo.getId());
            attachmentRepository.deleteById(photo.getId());
            productRepository.deleteById(id);
            return new Result("Product deleted...", true);
        }
        return new Result("Error ...", false);

    }
}
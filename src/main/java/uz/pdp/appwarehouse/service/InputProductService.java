package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.entity.InputProduct;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.payload.InputProductDTO;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.InputProductRepository;
import uz.pdp.appwarehouse.repository.InputRepository;
import uz.pdp.appwarehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InputProductService {
    @Autowired
    InputProductRepository inputProductRepository;

    @Autowired
    InputRepository inputRepository;

    @Autowired
    ProductRepository productRepository;

    public Result addInputProduct(InputProductDTO inputProductDTO) {

        InputProduct inputProduct = new InputProduct();

        Optional<Product> productRepositoryById = productRepository.findById(inputProductDTO.getProductId());
        if (!productRepositoryById.isPresent())
            return new Result("Product not found ...", false);
        inputProduct.setProduct(productRepositoryById.get());

        inputProduct.setAmount(inputProductDTO.getAmount());

        inputProduct.setPrice(inputProductDTO.getPrice());

        inputProduct.setExpireDate(inputProductDTO.getExpireDate());

        Optional<Input> inputRepositoryById = inputRepository.findById(inputProductDTO.getInputId());
        if (!inputRepositoryById.isPresent())
            return new Result("Input not found ...", false);
        inputProduct.setInput(inputRepositoryById.get());

        InputProduct savedInputProduct = inputProductRepository.save(inputProduct);

        return new Result("InputProduct added ...", true, savedInputProduct);

    }

    public Result getAllInputProducts() {
        List<InputProduct> all = inputProductRepository.findAll();
        return new Result("InputProduct list ...", true, all);
    }

    public Result getOneInputProduct(Integer id) {

        Optional<InputProduct> byId = inputProductRepository.findById(id);
        if (!byId.isPresent())
            return new Result("InputProduct not found ...", false);

        return new Result("InputProduct ...", true, byId.get());
    }

    public Result deleteInputProduct(Integer id) {

        Optional<InputProduct> byId = inputProductRepository.findById(id);
        if (!byId.isPresent())
            return new Result("InputProduct not found ...", false);

        inputProductRepository.deleteById(id);
        return new Result("InputProduct deleted ...", true);
    }

    public Result editInputProduct(Integer id, InputProductDTO inputProductDTO) {

        Optional<InputProduct> byId = inputProductRepository.findById(id);
        if (!byId.isPresent())
            return new Result("InputProduct not found ...", false);
        InputProduct inputProduct = byId.get();

        Optional<Product> productRepositoryById = productRepository.findById(inputProductDTO.getProductId());
        if (!productRepositoryById.isPresent())
            return new Result("Product not found ...", false);
        inputProduct.setProduct(productRepositoryById.get());

        inputProduct.setAmount(inputProductDTO.getAmount());

        inputProduct.setPrice(inputProductDTO.getPrice());

        inputProduct.setExpireDate(inputProductDTO.getExpireDate());

        Optional<Input> inputRepositoryById = inputRepository.findById(inputProductDTO.getInputId());
        if (!inputRepositoryById.isPresent())
            return new Result("Input not found ...", false);
        inputProduct.setInput(inputRepositoryById.get());

        InputProduct editedInputProduct = inputProductRepository.save(inputProduct);

        return new Result("InputProduct edited ...", true, editedInputProduct);
    }
}

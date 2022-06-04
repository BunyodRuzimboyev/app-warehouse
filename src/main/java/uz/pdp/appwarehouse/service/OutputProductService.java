package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Output;
import uz.pdp.appwarehouse.entity.OutputProduct;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.payload.OutputProductDTO;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.OutputProductRepository;
import uz.pdp.appwarehouse.repository.OutputRepository;
import uz.pdp.appwarehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {
    @Autowired
    OutputProductRepository outputProductRepository;
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    ProductRepository productRepository;

    public Result addOutputProduct(OutputProductDTO outputProductDTO) {

        Optional<Output> byId = outputRepository.findById(outputProductDTO.getOutputId());
        if (!byId.isPresent())
            return new Result("Output not found ...", false);
        Optional<Product> productRepositoryById = productRepository.findById(outputProductDTO.getProductId());
        if (!productRepositoryById.isPresent())
            return new Result("Product not found ...", false);

        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setProduct(productRepositoryById.get());
        outputProduct.setOutput(byId.get());
        outputProduct.setAmount(outputProductDTO.getAmount());
        outputProduct.setPrice(outputProductDTO.getPrice());
        OutputProduct save = outputProductRepository.save(outputProduct);
        return new Result("OutputProduct added ...", true, save);
    }


    public Result getAllOutputProducts() {
        List<OutputProduct> all = outputProductRepository.findAll();
        return new Result("All OutputProducts ...", true, all);
    }

    public Result getOneOutputProducts(Integer id) {

        Optional<OutputProduct> byId = outputProductRepository.findById(id);
        if (!byId.isPresent())
            return new Result("OutputProduct not found ....", false);
        return new Result("OutputProduct ...", true, byId.get());
    }

    public Result deleteOutputProduct(Integer id) {

        Optional<OutputProduct> byId = outputProductRepository.findById(id);
        if (!byId.isPresent())
            return new Result("OutputProduct not found ....", false);
        outputProductRepository.deleteById(id);
        return new Result("OutputProduct deleted ...", false);
    }

    public Result editOutputProduct(Integer id, OutputProductDTO outputProductDTO){

        Optional<OutputProduct> byId = outputProductRepository.findById(id);
        if (!byId.isPresent())
            return new Result("OutputProduct not found ....", false);

        OutputProduct outputProduct = byId.get();

        Optional<Output> outputId = outputRepository.findById(outputProductDTO.getOutputId());
        if (!outputId.isPresent())
            return new Result("Output not found ...", false);
        Optional<Product> productRepositoryById = productRepository.findById(outputProductDTO.getProductId());
        if (!productRepositoryById.isPresent())
            return new Result("Product not found ...", false);

        outputProduct.setProduct(productRepositoryById.get());
        outputProduct.setOutput(outputId.get());
        outputProduct.setAmount(outputProductDTO.getAmount());
        outputProduct.setPrice(outputProductDTO.getPrice());
        OutputProduct edit = outputProductRepository.save(outputProduct);
        return new Result("OutputProduct edited ...", true, edit);
    }


}

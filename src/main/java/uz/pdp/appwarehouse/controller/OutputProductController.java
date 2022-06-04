package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.payload.OutputProductDTO;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.OutputProductService;

@RestController
@RequestMapping("/outputProduct")
public class OutputProductController {
    @Autowired
    OutputProductService outputProductService;

    @PostMapping("/add")
    public Result addOutputProduct(@RequestBody OutputProductDTO outputProductDTO) {
        return outputProductService.addOutputProduct(outputProductDTO);
    }

    @GetMapping("/get/list")
    public Result getAllOutputProducts() {
        return outputProductService.getAllOutputProducts();
    }

    @GetMapping("/get/{id}")
    public Result getOneOutputProduct(@PathVariable Integer id) {
        return outputProductService.getOneOutputProducts(id);
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteOutputProduct(@PathVariable Integer id) {
        return outputProductService.deleteOutputProduct(id);
    }

    @PutMapping("/edit/{id}")
    public Result editOutputProduct(@PathVariable Integer id, @RequestBody OutputProductDTO outputProductDTO) {
        return outputProductService.editOutputProduct(id, outputProductDTO);
    }


}

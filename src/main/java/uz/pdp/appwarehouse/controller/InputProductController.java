package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.payload.InputProductDTO;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.InputProductService;

@RestController
@RequestMapping("/inputProduct")
public class InputProductController {
    @Autowired
    InputProductService inputProductService;

    @PostMapping("/add")
    public Result addInputProduct(@RequestBody InputProductDTO inputProductDTO) {
        return inputProductService.addInputProduct(inputProductDTO);
    }

    @GetMapping("/get/all")
    public Result getAllInputProduct() {
        return inputProductService.getAllInputProducts();
    }

    @GetMapping("/get/{id}")
    public Result getOneInputProduct(@PathVariable Integer id) {
        return inputProductService.getOneInputProduct(id);
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteInputProduct(@PathVariable Integer id) {
        return inputProductService.deleteInputProduct(id);
    }

    @PutMapping("/edit/{id}")
    public Result editInputProduct(@PathVariable Integer id, @RequestBody InputProductDTO inputProductDTO) {
        return inputProductService.editInputProduct(id, inputProductDTO);
    }
}

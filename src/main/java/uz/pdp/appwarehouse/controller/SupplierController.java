package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.SupplierDTO;
import uz.pdp.appwarehouse.service.SupplierService;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    @PostMapping("/add")
    public Result addSupplier(@RequestBody SupplierDTO supplierDTO) {
        return supplierService.addSupplier(supplierDTO);
    }

    @GetMapping("/get")
    public Result getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @GetMapping("/get/{id}")
    public Result getOneSupplier(@PathVariable Integer id) {
        return supplierService.getOneSupplier(id);
    }

    @DeleteMapping("/{id}")
    public Result deleteSupplier(@PathVariable Integer id) {
        return supplierService.deleteSupplier(id);
    }

    @PutMapping("/edit/{id}")
    public Result editSupplier(@PathVariable Integer id, @RequestBody SupplierDTO supplierDTO) {
        return supplierService.editSupplier(id, supplierDTO);
    }

}

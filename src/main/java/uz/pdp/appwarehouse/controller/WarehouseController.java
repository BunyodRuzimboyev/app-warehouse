package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.WarehouseDTO;
import uz.pdp.appwarehouse.service.WarehouseService;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    @PostMapping("/add")
    public Result addWarehouse(@RequestBody WarehouseDTO warehouseDTO) {
        return warehouseService.addWarehouse(warehouseDTO);
    }

    @GetMapping("/get")
    public Result getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }

    @GetMapping("/get/{id}")
    public Result getOneWarehouse(@PathVariable Integer id) {
        return warehouseService.getOneWarehouse(id);
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteWarehouse(@PathVariable Integer id) {
        return warehouseService.deleteWarehouse(id);
    }

    @PutMapping("/edit/{id}")
    public Result editWarehouse(@PathVariable Integer id, @RequestBody WarehouseDTO warehouseDTO){
        return  warehouseService.editWarehouse(id, warehouseDTO);
    }

}

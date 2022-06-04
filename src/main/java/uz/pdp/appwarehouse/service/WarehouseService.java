package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.WarehouseDTO;
import uz.pdp.appwarehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    @Autowired
    WarehouseRepository warehouseRepository;

    public Result addWarehouse(WarehouseDTO warehouseDTO) {
        boolean existsByName = warehouseRepository.existsByName(warehouseDTO.getName());
        if (existsByName)
            return new Result("Warehouse name already added ...", false);

        Warehouse warehouse = new Warehouse();
        warehouse.setName(warehouseDTO.getName());
        warehouse.setActive(warehouse.getActive());
        Warehouse savedWarehouse = warehouseRepository.save(warehouse);
        return new Result("Warehouse added ...", true, savedWarehouse);
    }

    public Result getAllWarehouses() {
        List<Warehouse> all = warehouseRepository.findAll();
        return new Result("All warehouses ...", true, all);
    }

    public Result getOneWarehouse(Integer id) {
        Optional<Warehouse> warehouseRepositoryById = warehouseRepository.findById(id);

        if (!warehouseRepositoryById.isPresent())
            return new Result("Warehouse not found ...", false);

        Warehouse warehouse = warehouseRepositoryById.get();

        return new Result("Warehouse is ...", true, warehouse);
    }

    public Result deleteWarehouse(Integer id) {

        Optional<Warehouse> warehouseRepositoryById = warehouseRepository.findById(id);
        if (!warehouseRepositoryById.isPresent())
            return new Result("Warehouse not found ...", false);

        warehouseRepository.deleteById(id);
        return new Result("Warehouse deleted ...", true);

    }

    public Result editWarehouse(Integer id, WarehouseDTO warehouseDTO) {

        Optional<Warehouse> warehouseRepositoryById = warehouseRepository.findById(id);
        if (!warehouseRepositoryById.isPresent())
            return new Result("Warehouse not found ...", false);

        Warehouse warehouse = new Warehouse();
        warehouse.setName(warehouseDTO.getName());
        warehouse.setActive(warehouseDTO.getActive());
        Warehouse savedWarehouse = warehouseRepository.save(warehouse);
        return new Result("Warehouse is edited ...", true, savedWarehouse);
    }


}

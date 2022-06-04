package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.InputDTO;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.CurrencyRepository;
import uz.pdp.appwarehouse.repository.InputRepository;
import uz.pdp.appwarehouse.repository.SupplierRepository;
import uz.pdp.appwarehouse.repository.WarehouseRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class InputService {
    @Autowired
    InputRepository inputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    public Result addInput(InputDTO inputDTO) {
        if (!supplierRepository.existsById(inputDTO.getSupplierId()))
            return new Result("Supplier not found ...", false);

        if (!warehouseRepository.existsById(inputDTO.getWarehouseId()))
            return new Result("Warehouse not found ...", false);

        if (!currencyRepository.existsById(inputDTO.getCurrencyId()))
            return new Result("Currency not found ...", false);

        if (!inputRepository.existsByCode(inputDTO.getCode()))
            return new Result("Code already exist ...", false);

        Input input = new Input();
        input.setDate(inputDTO.getDate());

        Optional<Supplier> supplierRepositoryById = supplierRepository.findById(inputDTO.getSupplierId());
        input.setSupplier(supplierRepositoryById.get());

        Optional<Warehouse> warehouseRepositoryById = warehouseRepository.findById(inputDTO.getWarehouseId());
        input.setWarehouse(warehouseRepositoryById.get());

        Optional<Currency> currencyRepositoryById = currencyRepository.findById(inputDTO.getCurrencyId());
        input.setCurrency(currencyRepositoryById.get());

        input.setFactureNumber(inputDTO.getFactureNumber());
        input.setCode(inputDTO.getCode());
        Input savedInput = inputRepository.save(input);
        return new Result("Input added ...", true, savedInput);
    }

    public Result getAllInputs() {
        List<Input> all = inputRepository.findAll();
        return new Result("Input list ...", true, all);
    }

    public Result getOneInput(Integer id) {
        Optional<Input> byId = inputRepository.findById(id);
        if (!byId.isPresent())
            return new Result("Input not found ...", false);
        Input input = byId.get();
        return new Result("Input ...", true, input);
    }

    public Result deleteInput(Integer id) {
        Optional<Input> byId = inputRepository.findById(id);
        if (!byId.isPresent())
            return new Result("Input not found ...", false);
        inputRepository.deleteById(id);
        return new Result("Input deleted ...", true);
    }

    public Result editInput(Integer id, InputDTO inputDTO){
        Optional<Input> byId = inputRepository.findById(id);
        if (!byId.isPresent())
            return new Result("Input not found ...", false);
        Input input = byId.get();
        input.setDate(inputDTO.getDate());

        Optional<Supplier> supplierRepositoryById = supplierRepository.findById(inputDTO.getSupplierId());
        input.setSupplier(supplierRepositoryById.get());

        Optional<Warehouse> warehouseRepositoryById = warehouseRepository.findById(inputDTO.getWarehouseId());
        input.setWarehouse(warehouseRepositoryById.get());

        Optional<Currency> currencyRepositoryById = currencyRepository.findById(inputDTO.getCurrencyId());
        input.setCurrency(currencyRepositoryById.get());

        input.setFactureNumber(inputDTO.getFactureNumber());
        input.setCode(inputDTO.getCode());
        Input editedInput = inputRepository.save(input);

        return new Result("Input edited ...", true, editedInput);
    }
}

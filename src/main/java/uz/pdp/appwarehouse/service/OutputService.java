package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.*;
import uz.pdp.appwarehouse.payload.InputDTO;
import uz.pdp.appwarehouse.payload.OutputDTO;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class OutputService {
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    public Result addOutput(OutputDTO outputDTO) {

        Output output = new Output();
        output.setDate(outputDTO.getDate());

        Optional<Client> clientRepositoryById = clientRepository.findById(outputDTO.getClientId());
        if (!clientRepositoryById.isPresent())
            return new Result("Client not found ...", false);
        output.setClient(clientRepositoryById.get());

        Optional<Warehouse> warehouseRepositoryById = warehouseRepository.findById(outputDTO.getWarehouseId());
        if (!warehouseRepositoryById.isPresent())
            return new Result("Warehouse not found ...", false);
        output.setWarehouse(warehouseRepositoryById.get());

        output.setFactureNumber(outputDTO.getFactureNumber());

        if (!outputRepository.existsByCode(outputDTO.getCode()))
            return new Result("Code already exist ...", false);


        output.setCode(outputDTO.getCode());
        Output savedOutput = outputRepository.save(output);
        return new Result("Output added ...", true, savedOutput);
    }

    public Result getAllOutputs() {
        List<Output> all = outputRepository.findAll();
        return new Result("Output list ...", true, all);
    }

    public Result getOneOutput(Integer id) {
        Optional<Output> byId = outputRepository.findById(id);
        if (!byId.isPresent())
            return new Result("Output not found ...", false);
        Output output = byId.get();
        return new Result("Output ...", true, output);
    }

    public Result deleteOutput(Integer id) {
        Optional<Output> byId = outputRepository.findById(id);
        if (!byId.isPresent())
            return new Result("Output not found ...", false);
        outputRepository.deleteById(id);
        return new Result("Output deleted ...", true);
    }

    public Result editOutput(Integer id, OutputDTO outputDTO) {

        Optional<Output> byId = outputRepository.findById(id);
        if (!byId.isPresent())
            return new  Result("Output not found ...", false);
        Output output = byId.get();
        output.setDate(outputDTO.getDate());

        Optional<Client> clientRepositoryById = clientRepository.findById(outputDTO.getClientId());
        if (!clientRepositoryById.isPresent())
            return new Result("Client not found ...", false);
        output.setClient(clientRepositoryById.get());

        Optional<Warehouse> warehouseRepositoryById = warehouseRepository.findById(outputDTO.getWarehouseId());
        if (!warehouseRepositoryById.isPresent())
            return new Result("Warehouse not found ...", false);
        output.setWarehouse(warehouseRepositoryById.get());

        output.setFactureNumber(outputDTO.getFactureNumber());

        output.setCode(outputDTO.getCode());
        Output editedOutput = outputRepository.save(output);
        return new Result("Output edited ...", true, editedOutput);
    }
}

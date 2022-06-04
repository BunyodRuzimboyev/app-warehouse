package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.SupplierDTO;
import uz.pdp.appwarehouse.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    public Result addSupplier(SupplierDTO supplierDTO) {
        if (supplierRepository.existsByPhoneNumber(supplierDTO.getPhoneNumber()))
            return new Result("Supplier's phone number already exist ...", false);

        Supplier supplier = new Supplier();
        supplier.setName(supplierDTO.getName());
        supplier.setActive(supplierDTO.getActive());
        supplier.setPhoneNumber(supplierDTO.getPhoneNumber());
        Supplier savedSupplier = supplierRepository.save(supplier);
        return new Result("Supplier added ...", true, savedSupplier);
    }

    public Result getAllSuppliers() {
        List<Supplier> all = supplierRepository.findAll();
        return new Result("All supplier's list ...", true, all);
    }

    public Result getOneSupplier(Integer id) {

        Optional<Supplier> supplierRepositoryById = supplierRepository.findById(id);
        if (!supplierRepositoryById.isPresent())
            return new Result("Supplier not found ...", false);

        Supplier supplier = supplierRepositoryById.get();
        return new Result("Supplier ...", true, supplier);
    }

    public Result deleteSupplier(Integer id) {

        Optional<Supplier> supplierRepositoryById = supplierRepository.findById(id);
        if (!supplierRepositoryById.isPresent())
            return new Result("Supplier not found ...", false);

        supplierRepository.deleteById(id);
        return new Result("Supplier deleted ...", true);
    }

    public Result editSupplier(Integer id, SupplierDTO supplierDTO) {

        Optional<Supplier> supplierRepositoryById = supplierRepository.findById(id);
        if (!supplierRepositoryById.isPresent())
            return new Result("Supplier not found ...", false);

        Supplier supplier = new Supplier();
        supplier.setName(supplierDTO.getName());
        supplier.setActive(supplierDTO.getActive());
        supplier.setPhoneNumber(supplierDTO.getPhoneNumber());
        Supplier editedSupplier = supplierRepository.save(supplier);
        return new Result("Supplier edited ...", true, editedSupplier);
    }


}

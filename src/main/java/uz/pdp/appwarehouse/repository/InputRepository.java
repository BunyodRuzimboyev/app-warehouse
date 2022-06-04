package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.entity.Input;

public interface InputRepository extends JpaRepository<Input, Integer> {
    boolean existsBySupplierId(Integer supplier_id);
    boolean existsByWarehouseId(Integer warehouse_id);
    boolean existsByCurrencyId(Integer currency_id);
    boolean existsByCode(String code);
}
